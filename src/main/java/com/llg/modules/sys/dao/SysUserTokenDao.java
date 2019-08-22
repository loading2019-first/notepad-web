/**
 * Copyright (c) 2016-2019  All rights reserved.
 *
 *
 *
 * 版权所有，侵权必究！
 */

package com.llg.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.llg.modules.sys.entity.SysUserTokenEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统用户Token
 *
 * @author llg
 */
@Mapper
public interface SysUserTokenDao extends BaseMapper<SysUserTokenEntity> {

    SysUserTokenEntity queryByToken(String token);
	
}
