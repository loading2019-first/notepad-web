package com.llg.modules.notepad.dao;

import com.llg.modules.notepad.entity.ItemInfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 财务项目信息表
 * 
 * @author llg
 * @email lilonggan1987@163.com
 * @date 2019-04-26 23:06:16
 */
@Mapper
public interface ItemInfoDao extends BaseMapper<ItemInfoEntity> {
	
}
