/**
 * Copyright (c) 2016-2019  All rights reserved.
 *
 *
 *
 * 版权所有，侵权必究！
 */

package com.llg.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.llg.common.utils.R;
import com.llg.modules.sys.entity.SysUserTokenEntity;

/**
 * 用户Token
 *
 * @author llg
 */
public interface SysUserTokenService extends IService<SysUserTokenEntity> {

	/**
	 * 生成token
	 * @param userId  用户ID
	 */
	R createToken(long userId);

	/**
	 * 退出，修改token值
	 * @param userId  用户ID
	 */
	void logout(long userId);

}
