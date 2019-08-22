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
@TableName("wx_category")
public class CategoryEntity implements Serializable {
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
	private String keywords;
	/**
	 * 
	 */
	private String frontDesc;
	/**
	 * 
	 */
	private Integer parentId;
	/**
	 * 
	 */
	private Integer sortOrder;
	/**
	 * 
	 */
	private Integer showIndex;
	/**
	 * 
	 */
	private Integer isShow;
	/**
	 * 
	 */
	private String bannerUrl;
	/**
	 * 
	 */
	private String iconUrl;
	/**
	 * 
	 */
	private String imgUrl;
	/**
	 * 
	 */
	private String wapBannerUrl;
	/**
	 * 
	 */
	private String level;
	/**
	 * 
	 */
	private Integer type;
	/**
	 * 
	 */
	private String frontName;
	/**
	 * 
	 */
	private Date addTime;
	/**
	 * 
	 */
	private Integer deleted;

}
