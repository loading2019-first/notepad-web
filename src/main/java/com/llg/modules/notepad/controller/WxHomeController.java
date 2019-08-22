package com.llg.modules.notepad.controller;


import com.llg.common.utils.RedisUtils;
import com.llg.common.utils.ResponseUtil;
import com.llg.modules.notepad.entity.*;
import com.llg.modules.notepad.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/wx/home")
public class WxHomeController {
    //private final Log logger = LogFactory.getLog(WxHomeController.class);

    @Autowired
    private AdService adService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private TopicService topicService;
    @Autowired
    private CategoryService categoryService;
    
    @Autowired
    private RedisUtils util;
    

    /**
     * app首页
     *
     * @return app首页相关信息
     *   成功则
     *  {
     *      errno: 0,
     *      errmsg: '成功',
     *      data:
     *          {
     *              banner: xxx,
     *              channel: xxx,
     *              newGoodsList: xxx,
     *              hotGoodsList: xxx,
     *              topicList: xxx,
     *              floorGoodsList: xxx
     *          }
     *  }
     *   失败则 { errno: XXX, errmsg: XXX }
     */
    @GetMapping("/index")
    public Object index() {
        Map<String, Object> data = new HashMap<>();

        List<AdEntity> banner = adService.list();
        data.put("banner", banner);

        List<CategoryEntity> channel = categoryService.list();
        data.put("channel", channel);

        List<GoodsEntity> newGoods = goodsService.list();
        data.put("newGoodsList", newGoods);

        List<GoodsEntity> hotGoods = goodsService.list();
        data.put("hotGoodsList", hotGoods);

        List<BrandEntity> brandList = brandService.list();
        data.put("brandList", brandList);

        List<TopicEntity> topicList = topicService.list();
        data.put("topicList", topicList);

        List<Object> categoryList = new ArrayList<>();
        List<CategoryEntity> catL1List = categoryService.list();
        for (CategoryEntity catL1 : catL1List) {
//            List<CategoryEntity> catL2List = categoryService.getById();
//            List<Integer> l2List = new ArrayList<>();
//            for (CategoryEntity catL2 : catL2List) {
//                l2List.add(catL2.getId());
//            }
//
//            List<GoodsEntity> categoryGoods = goodsService.queryByCategory(l2List, 0, 5);
//            Map<String,Object> catGoods = new HashMap<String,Object>();
//            catGoods.put("id", catL1.getId());
//            catGoods.put("name", catL1.getName());
//            catGoods.put("goodsList", categoryGoods);
//            categoryList.add(catGoods);
        }
        data.put("floorGoodsList", categoryList);

        return ResponseUtil.ok(data);
    }
    
    @GetMapping("/test")
    public Object redis() {
    	//util.set("data", "java");
    	Object val = util.get("hello");
    	String value = "hello:"+val;
    	//int token = manager.getUserId("3239");
    	return ResponseUtil.ok(value);
    }
}