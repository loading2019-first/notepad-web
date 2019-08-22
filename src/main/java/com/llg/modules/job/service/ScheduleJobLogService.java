/**
 * Copyright (c) 2016-2019  All rights reserved.
 *
 *
 *
 * 版权所有，侵权必究！
 */

package com.llg.modules.job.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.llg.common.utils.PageUtils;
import com.llg.modules.job.entity.ScheduleJobLogEntity;

import java.util.Map;

/**
 * 定时任务日志
 *
 * @author llg
 */
public interface ScheduleJobLogService extends IService<ScheduleJobLogEntity> {

	PageUtils queryPage(Map<String, Object> params);
	
}
