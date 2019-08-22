/**
 * Copyright (c) 2016-2019  All rights reserved.
 *
 *
 *
 * 版权所有，侵权必究！
 */

package com.llg.modules.oss.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.llg.common.utils.PageUtils;
import com.llg.modules.oss.entity.SysOssEntity;

import java.util.Map;

/**
 * 文件上传
 *
 * @author llg
 */
public interface SysOssService extends IService<SysOssEntity> {

	PageUtils queryPage(Map<String, Object> params);
}
