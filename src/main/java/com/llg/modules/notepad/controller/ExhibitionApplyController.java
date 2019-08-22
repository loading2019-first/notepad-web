package com.llg.modules.notepad.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.llg.modules.notepad.entity.ExhibitionApplyEntity;
import com.llg.modules.notepad.service.ExhibitionApplyService;
import com.llg.common.utils.PageUtils;
import com.llg.common.utils.R;



/**
 * 
 *
 * @author llg
 * @email lilonggan1987@163.com
 * @date 2019-04-27 08:35:41
 */
@RestController
@RequestMapping("notepad/exhibitionapply")
public class ExhibitionApplyController {
    @Autowired
    private ExhibitionApplyService exhibitionApplyService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("notepad:exhibitionapply:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = exhibitionApplyService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("notepad:exhibitionapply:info")
    public R info(@PathVariable("id") Integer id){
		ExhibitionApplyEntity exhibitionApply = exhibitionApplyService.getById(id);

        return R.ok().put("exhibitionApply", exhibitionApply);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("notepad:exhibitionapply:save")
    public R save(@RequestBody ExhibitionApplyEntity exhibitionApply){
		exhibitionApplyService.save(exhibitionApply);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("notepad:exhibitionapply:update")
    public R update(@RequestBody ExhibitionApplyEntity exhibitionApply){
		exhibitionApplyService.updateById(exhibitionApply);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("notepad:exhibitionapply:delete")
    public R delete(@RequestBody Integer[] ids){
		exhibitionApplyService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
