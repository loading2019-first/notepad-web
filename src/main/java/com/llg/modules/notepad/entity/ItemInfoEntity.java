package com.llg.modules.notepad.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 财务项目信息表
 * 
 * @author llg
 * @email lilonggan1987@163.com
 * @date 2019-04-26 23:06:16
 */
@Data
@TableName("lg_item_info")
public class ItemInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Integer id;
	/**
	 * 类型1-支出，2-收入
	 */
	private Integer type;
	/**
	 * 项目名称
	 */
	private String itemName;
	/**
	 * 用户ID
	 */
	private Long userId;
	/**
	 * 创建时间
	 */
	private Date gmtCreate;
	/**
	 * 修改时间
	 */
	private Date gmtModified;

}
