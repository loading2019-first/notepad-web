package com.llg.modules.notepad.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.llg.common.utils.PageUtils;
import com.llg.modules.notepad.entity.FinanceIncomeEntity;

import java.util.List;
import java.util.Map;

/**
 * 收入支出明细表
 *
 * @author llg
 * @email lilonggan1987@163.com
 * @date 2019-04-26 23:06:16
 */
public interface FinanceIncomeService extends IService<FinanceIncomeEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<FinanceIncomeEntity> statisticFinance(FinanceIncomeEntity info);
}

