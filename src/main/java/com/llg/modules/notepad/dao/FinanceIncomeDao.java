package com.llg.modules.notepad.dao;

import com.llg.modules.notepad.entity.FinanceIncomeEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 收入支出明细表
 * 
 * @author llg
 * @email lilonggan1987@163.com
 * @date 2019-04-26 23:06:16
 */
@Mapper
public interface FinanceIncomeDao extends BaseMapper<FinanceIncomeEntity> {
    public List<FinanceIncomeEntity> statisticFinance(FinanceIncomeEntity info);
}
