package com.llg.modules.notepad.controller;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.llg.common.utils.*;
import com.llg.modules.notepad.entity.ItemInfoEntity;
import com.llg.modules.sys.service.UserTokenManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.llg.modules.notepad.entity.FinanceIncomeEntity;
import com.llg.modules.notepad.service.FinanceIncomeService;

import javax.servlet.http.HttpServletRequest;


/**
 * 收入支出明细表
 *
 * @author llg
 * @email lilonggan1987@163.com
 * @date 2019-04-26 23:06:16
 */
@RestController
@RequestMapping("/wx/financeincome")
@Slf4j
public class FinanceIncomeController {
    @Autowired
    private FinanceIncomeService financeIncomeService;
    @Autowired
    private RedisUtils util;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("generator:financeincome:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = financeIncomeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("generator:financeincome:info")
    public R info(@PathVariable("id") Integer id){
		FinanceIncomeEntity financeIncome = financeIncomeService.getById(id);

        return R.ok().put("financeIncome", financeIncome);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("generator:financeincome:save")
    public R save(@RequestBody FinanceIncomeEntity financeIncome, HttpServletRequest request){
        String token = request.getHeader("token");
        Long userId = UserTokenManager.getUserId(token,util);
        financeIncome.setUserId(userId);
        financeIncome.setGmtCreate(DateUtil.getCurrentDateTime(DateUtil.fullDate));
        financeIncome.setGmtModified(DateUtil.getCurrentDateTime(DateUtil.fullDate));
		financeIncomeService.save(financeIncome);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("generator:financeincome:update")
    public R update(@RequestBody FinanceIncomeEntity financeIncome){
		financeIncomeService.updateById(financeIncome);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("generator:financeincome:delete")
    public R delete(@RequestBody FinanceIncomeEntity info){
		//financeIncomeService.removeByIds(Arrays.asList(ids));
        financeIncomeService.removeById(info.getId());
        return R.ok();
    }

    /**
     * 列表
     */
    @RequestMapping("/statisticFinance")
    //@RequiresPermissions("generator:iteminfo:list")
    public R statisticFinance(@RequestBody FinanceIncomeEntity info){
        info.setUserId(ShiroUtils.getUserId());
        log.info("request params="+JSON.toJSONString(info));
        List<FinanceIncomeEntity> list = financeIncomeService.statisticFinance(info);
        return R.ok().put("data", list);
    }

}
