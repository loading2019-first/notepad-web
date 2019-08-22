/**
 * Copyright (c) 2016-2019  All rights reserved.
 *
 *
 *
 * 版权所有，侵权必究！
 */

package com.llg.modules.app.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.llg.modules.app.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户
 *
 * @author llg
 */
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {

}
