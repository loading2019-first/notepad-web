package com.llg.modules.notepad.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author llg
 * @email lilonggan1987@163.com
 * @date 2019-04-27 08:35:41
 */
@Data
@TableName("wx_exhibition_apply")
public class ExhibitionApplyEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Integer id;
	/**
	 * 
	 */
	private String customerName;
	/**
	 * 
	 */
	private String companyName;
	/**
	 * 
	 */
	private String mobile;
	/**
	 * 
	 */
	private String email;
	/**
	 * 
	 */
	private String province;
	/**
	 * 
	 */
	private String address;
	/**
	 * 
	 */
	private String userId;
	/**
	 * 
	 */
	private String activeId;
	/**
	 * 
	 */
	private String gmtCreate;
	/**
	 * 
	 */
	private String gmtModified;

}
