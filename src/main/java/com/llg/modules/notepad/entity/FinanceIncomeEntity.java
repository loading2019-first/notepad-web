package com.llg.modules.notepad.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 收入支出明细表
 * 
 * @author llg
 * @email lilonggan1987@163.com
 * @date 2019-04-26 23:06:16
 */
@Data
@TableName("lg_finance_income")
public class FinanceIncomeEntity implements Serializable {
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
	 * 金额
	 */
	private BigDecimal fee;
	/**
	 * 用户ID
	 */
	private Long userId;
	/**
	 * 具体收入/支出项目
	 */
	private Integer itemId;
	/**
	 * 经度
	 */
	private BigDecimal lng;
	/**
	 * 纬度
	 */
	private BigDecimal lat;
	/**
	 * 发生日期
	 */
	private String dateTime;
	/**
	 * 创建时间
	 */
	private Date gmtCreate;
	/**
	 * 修改时间
	 */
	private Date gmtModified;
	/**
	 * 类别名称
	 */
	@TableField(exist = false)
	private String name;

	@TableField(exist = false)
	private BigDecimal value;

	@TableField(exist = false)
	private String startDate;

	@TableField(exist = false)
	private String endDate;

	@TableField(exist = false)
	private BigDecimal data;

}
