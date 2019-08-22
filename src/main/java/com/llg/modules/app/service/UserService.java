/**
 * Copyright (c) 2016-2019  All rights reserved.
 *
 *
 *
 * 版权所有，侵权必究！
 */

package com.llg.modules.app.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.llg.modules.app.entity.UserEntity;
import com.llg.modules.app.form.LoginForm;

/**
 * 用户
 *
 * @author llg
 */
public interface UserService extends IService<UserEntity> {

	UserEntity queryByMobile(String mobile);

	/**
	 * 用户登录
	 * @param form    登录表单
	 * @return        返回用户ID
	 */
	long login(LoginForm form);
}
