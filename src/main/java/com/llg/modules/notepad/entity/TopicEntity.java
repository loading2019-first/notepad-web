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
@TableName("wx_topic")
public class TopicEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 
	 */
	private String title;
	/**
	 * 
	 */
	private String content;
	/**
	 * 
	 */
	private String avatar;
	/**
	 * 
	 */
	private String itemPicUrl;
	/**
	 * 
	 */
	private String subtitle;
	/**
	 * 
	 */
	private BigDecimal priceInfo;
	/**
	 * 
	 */
	private String readCount;
	/**
	 * 
	 */
	private String scenePicUrl;
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
	private Date addTime;
	/**
	 * 
	 */
	private Integer deleted;

}
