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
 * @date 2019-04-27 09:45:13
 */
@Data
@TableName("wx_ad")
public class AdEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 
	 */
	private Integer position;
	/**
	 * 
	 */
	private String name;
	/**
	 * 
	 */
	private String link;
	/**
	 * 
	 */
	private String url;
	/**
	 * 
	 */
	private String content;
	/**
	 * 
	 */
	private Date startTime;
	/**
	 * 
	 */
	private Date endTime;
	/**
	 * 
	 */
	private Integer enabled;
	/**
	 * 
	 */
	private Date addTime;
	/**
	 * 
	 */
	private Integer deleted;

}
