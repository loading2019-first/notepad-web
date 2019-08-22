package com.llg.modules.notepad.controller;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.llg.common.utils.DateUtil;
import com.llg.common.utils.PageUtils;
import com.llg.common.utils.R;
import com.llg.common.utils.ShiroUtils;
import com.llg.modules.sys.entity.SysUserEntity;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.llg.modules.notepad.entity.ItemInfoEntity;
import com.llg.modules.notepad.service.ItemInfoService;



/**
 * 财务项目信息表
 *
 * @author llg
 * @email lilonggan1987@163.com
 * @date 2019-04-26 23:06:16
 */
@RestController
@RequestMapping("/wx/iteminfo")
public class ItemInfoController {
    @Autowired
    private ItemInfoService itemInfoService;

    /**
     * 列表
     */
    @RequestMapping("/listByPage")
    //@RequiresPermissions("generator:iteminfo:list")
    public R listByPage(@RequestBody ItemInfoEntity item){
        Map<String,Object> params = new Hashtable<>();
        params.put("type",item.getType());
        PageUtils page = itemInfoService.queryPage(params);
        return R.ok().put("page", page);
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("generator:iteminfo:list")
    public R list(@RequestParam Map<String, Object> params){
//        Map<String,Object> params = new Hashtable<>();
//        params.put("type",item.getType());
        params.put("userId",ShiroUtils.getUserId());
        System.out.println(JSON.toJSONString("请求参数"+params));
        PageUtils page = itemInfoService.queryPage(params);
        return R.ok().put("data", page.getList());
    }


    /**
     * 列表
     */
    @RequestMapping("/listNoPage")
    public R listNoPage(@RequestBody ItemInfoEntity itemInfo){
        itemInfo.setUserId(ShiroUtils.getUserId());
        System.out.println(JSON.toJSONString("请求参数"+itemInfo));
        List<ItemInfoEntity> list = itemInfoService.queryList(itemInfo);
        return R.ok().put("data", list);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("generator:iteminfo:info")
    public R info(@PathVariable("id") Integer id){
		ItemInfoEntity itemInfo = itemInfoService.getById(id);

        return R.ok().put("itemInfo", itemInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("generator:iteminfo:save")
    public R save(@RequestBody ItemInfoEntity itemInfo){
        itemInfo.setGmtCreate(DateUtil.getCurrentDateTime());
        itemInfo.setGmtModified(DateUtil.getCurrentDateTime());
        itemInfo.setUserId(ShiroUtils.getUserId());
        try {
            itemInfoService.saveEntity(itemInfo);
            return R.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(500,e.getMessage());
        }


    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("generator:iteminfo:update")
    public R update(@RequestBody ItemInfoEntity itemInfo){
        itemInfo.setGmtModified(DateUtil.getCurrentDateTime());
		itemInfoService.updateById(itemInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:iteminfo:delete")
    public R delete(@RequestBody Integer[] ids){
		itemInfoService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
