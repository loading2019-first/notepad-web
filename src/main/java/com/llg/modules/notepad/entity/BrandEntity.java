package com.llg.modules.notepad.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
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
@TableName("wx_brand")
public class BrandEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 
	 */
	private String name;
	/**
	 * 
	 */
	private String listPicUrl;
	/**
	 * 
	 */
	private String simpleDesc;
	/**
	 * 
	 */
	private String picUrl;
	/**
	 * 
	 */
	private Integer sortOrder;
	/**
	 * 
	 */
	private Integer isShow;
	/**
	 * 
	 */
	private BigDecimal floorPrice;
	/**
	 * 
	 */
	private String appListPicUrl;
	/**
	 * 
	 */
	private Integer isNew;
	/**
	 * 
	 */
	private String newPicUrl;
	/**
	 * 
	 */
	private Integer newSortOrder;
	/**
	 * 
	 */
	private Date addTime;
	/**
	 * 
	 */
	private Integer deleted;

}
