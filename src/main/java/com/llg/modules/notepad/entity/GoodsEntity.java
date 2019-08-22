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
@TableName("wx_goods")
public class GoodsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 
	 */
	private String goodsSn;
	/**
	 * 
	 */
	private String name;
	/**
	 * 
	 */
	private Integer categoryId;
	/**
	 * 
	 */
	private Integer brandId;
	/**
	 * 
	 */
	private String gallery;
	/**
	 * 
	 */
	private String keywords;
	/**
	 * 
	 */
	private String goodsBrief;
	/**
	 * 
	 */
	private Integer isOnSale;
	/**
	 * 
	 */
	private Integer sortOrder;
	/**
	 * 专柜价格
	 */
	private BigDecimal counterPrice;
	/**
	 * 
	 */
	private Integer isNew;
	/**
	 * 商品主图
	 */
	private String primaryPicUrl;
	/**
	 * 商品列表图
	 */
	private String listPicUrl;
	/**
	 * 
	 */
	private Integer isHot;
	/**
	 * 商品单位
	 */
	private String goodsUnit;
	/**
	 * 零售价格
	 */
	private BigDecimal retailPrice;
	/**
	 * 
	 */
	private String goodsDesc;
	/**
	 * 
	 */
	private Date addTime;
	/**
	 * 
	 */
	private Integer deleted;

}
