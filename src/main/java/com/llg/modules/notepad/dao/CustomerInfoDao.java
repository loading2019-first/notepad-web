package com.llg.modules.notepad.dao;

import com.llg.modules.notepad.entity.CustomerInfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 小程序用户信息表
 * 
 * @author llg
 * @email lilonggan1987@163.com
 * @date 2019-04-27 08:35:40
 */
@Mapper
public interface CustomerInfoDao extends BaseMapper<CustomerInfoEntity> {
	
}
