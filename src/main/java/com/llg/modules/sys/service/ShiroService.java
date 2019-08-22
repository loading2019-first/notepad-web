/**
 * Copyright (c) 2016-2019  All rights reserved.
 *
 *
 *
 * 版权所有，侵权必究！
 */

package com.llg.modules.sys.service;

import com.llg.modules.sys.entity.SysUserEntity;
import com.llg.modules.sys.entity.SysUserTokenEntity;

import java.util.Set;

/**
 * shiro相关接口
 *
 * @author llg
 */
public interface ShiroService {
    /**
     * 获取用户权限列表
     */
    Set<String> getUserPermissions(long userId);

    SysUserTokenEntity queryByToken(String token);

    /**
     * 根据用户ID，查询用户
     * @param userId
     */
    SysUserEntity queryUser(Long userId);
}
