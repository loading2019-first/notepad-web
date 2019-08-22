package com.llg.modules.notepad.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.llg.common.utils.PageUtils;
import com.llg.common.utils.Query;
import com.llg.common.utils.RedisUtils;
import com.llg.common.utils.ShiroUtils;
import com.llg.modules.notepad.dao.ItemInfoDao;
import com.llg.modules.notepad.entity.ItemInfoEntity;
import com.llg.modules.notepad.service.ItemInfoService;
import com.llg.modules.notepad.utils.enums.TypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service("itemInfoService")
public class ItemInfoServiceImpl extends ServiceImpl<ItemInfoDao, ItemInfoEntity> implements ItemInfoService {
    @Autowired
    private RedisUtils util;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Object type = params.get("type");
        if (type==null){
            type = TypeEnum.INCOME.getCode();

        }
        IPage<ItemInfoEntity> page = this.page(
                new Query<ItemInfoEntity>().getPage(params),
                new QueryWrapper<ItemInfoEntity>().eq(type!=null,"type",type)
                .eq("user_id",ShiroUtils.getUserId())
        );

        return new PageUtils(page);
    }

    @Override
    public boolean saveEntity(ItemInfoEntity info) throws Exception{
        ItemInfoEntity po = new ItemInfoEntity();
        po.setItemName(info.getItemName());
        po.setUserId(ShiroUtils.getUserId());
        ItemInfoEntity result = this.getOne(new QueryWrapper<ItemInfoEntity>(po));
        if(result!=null){
            throw new Exception("该类别已经存在");
        }
        return this.save(info);
    }

    @Override
    @Cacheable(value = "redis", key = "'item_info_'+#id")
    public ItemInfoEntity getById(Serializable id) {
        return super.getById(id);
    }

    @Override
    @CacheEvict(value = "redis",key = "'item_info_'+#id")
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @Override
    public List<ItemInfoEntity> queryList(ItemInfoEntity info) {
        return this.list(new QueryWrapper<ItemInfoEntity>().setEntity(info));
    }
}