package com.llg.modules.notepad.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.llg.common.utils.PageUtils;
import com.llg.modules.notepad.entity.GoodsEntity;

import java.util.Map;

/**
 * 
 *
 * @author llg
 * @email lilonggan1987@163.com
 * @date 2019-04-27 08:35:41
 */
public interface GoodsService extends IService<GoodsEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

