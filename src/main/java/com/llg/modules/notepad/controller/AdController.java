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

import com.llg.modules.notepad.entity.AdEntity;
import com.llg.modules.notepad.service.AdService;
import com.llg.common.utils.PageUtils;
import com.llg.common.utils.R;



/**
 * 
 *
 * @author llg
 * @email lilonggan1987@163.com
 * @date 2019-04-27 09:45:13
 */
@RestController
@RequestMapping("notepad/ad")
public class AdController {
    @Autowired
    private AdService adService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("notepad:ad:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = adService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("notepad:ad:info")
    public R info(@PathVariable("id") Integer id){
		AdEntity ad = adService.getById(id);

        return R.ok().put("ad", ad);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("notepad:ad:save")
    public R save(@RequestBody AdEntity ad){
		adService.save(ad);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("notepad:ad:update")
    public R update(@RequestBody AdEntity ad){
		adService.updateById(ad);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("notepad:ad:delete")
    public R delete(@RequestBody Integer[] ids){
		adService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
