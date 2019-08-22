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

import com.llg.modules.notepad.entity.TopicEntity;
import com.llg.modules.notepad.service.TopicService;
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
@RequestMapping("notepad/topic")
public class TopicController {
    @Autowired
    private TopicService topicService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("notepad:topic:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = topicService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("notepad:topic:info")
    public R info(@PathVariable("id") Integer id){
		TopicEntity topic = topicService.getById(id);

        return R.ok().put("topic", topic);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("notepad:topic:save")
    public R save(@RequestBody TopicEntity topic){
		topicService.save(topic);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("notepad:topic:update")
    public R update(@RequestBody TopicEntity topic){
		topicService.updateById(topic);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("notepad:topic:delete")
    public R delete(@RequestBody Integer[] ids){
		topicService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
