package com.llg.modules.notepad.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.llg.common.utils.PageUtils;
import com.llg.modules.notepad.entity.ItemInfoEntity;

import java.util.List;
import java.util.Map;

/**
 * 财务项目信息表
 *
 * @author llg
 * @email lilonggan1987@163.com
 * @date 2019-04-26 23:06:16
 */
public interface ItemInfoService extends IService<ItemInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    boolean saveEntity(ItemInfoEntity info) throws Exception;

    List<ItemInfoEntity> queryList(ItemInfoEntity info);
}

