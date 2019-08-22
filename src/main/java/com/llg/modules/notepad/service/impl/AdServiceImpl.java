package com.llg.modules.notepad.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.llg.common.utils.PageUtils;
import com.llg.common.utils.Query;

import com.llg.modules.notepad.dao.AdDao;
import com.llg.modules.notepad.entity.AdEntity;
import com.llg.modules.notepad.service.AdService;


@Service("adService")
public class AdServiceImpl extends ServiceImpl<AdDao, AdEntity> implements AdService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AdEntity> page = this.page(
                new Query<AdEntity>().getPage(params),
                new QueryWrapper<AdEntity>()
        );

        return new PageUtils(page);
    }

}