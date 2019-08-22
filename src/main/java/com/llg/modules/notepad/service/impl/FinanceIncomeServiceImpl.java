package com.llg.modules.notepad.service.impl;

import com.llg.common.utils.DateUtil;
import com.llg.common.utils.PageUtils;
import com.llg.common.utils.Query;
import com.llg.common.utils.ShiroUtils;
import com.llg.modules.notepad.entity.ItemInfoEntity;
import com.llg.modules.notepad.service.ItemInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.llg.modules.notepad.dao.FinanceIncomeDao;
import com.llg.modules.notepad.entity.FinanceIncomeEntity;
import com.llg.modules.notepad.service.FinanceIncomeService;


@Service("financeIncomeService")
public class FinanceIncomeServiceImpl extends ServiceImpl<FinanceIncomeDao, FinanceIncomeEntity> implements FinanceIncomeService {
    @Autowired
    private FinanceIncomeDao financeIncomeDao;
    @Autowired
    private ItemInfoService itemInfoService;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<FinanceIncomeEntity> page = this.page(
                new Query<FinanceIncomeEntity>().getPage(params,"gmt_create",false),
                new QueryWrapper<FinanceIncomeEntity>().eq("user_id",ShiroUtils.getUserId())
        );
        page.getRecords().forEach( po ->{
            ItemInfoEntity info = itemInfoService.getById(po.getItemId());
            if (info!=null){
                po.setName(info.getItemName());
            }else{
                po.setName("未知类型");
            }
        });

        return new PageUtils(page);
    }

    @Override
    public List<FinanceIncomeEntity> statisticFinance(FinanceIncomeEntity info) {
        //this.baseMapper
        return financeIncomeDao.statisticFinance(info);
    }
}