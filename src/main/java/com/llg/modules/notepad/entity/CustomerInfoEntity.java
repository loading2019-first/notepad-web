package com.llg.modules.notepad.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 小程序用户信息表
 * 
 * @author llg
 * @email lilonggan1987@163.com
 * @date 2019-04-27 08:35:40
 */
@Data
@TableName("wx_customer_info")
public class CustomerInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 用户名称
	 */
	private String username;
	/**
	 * 
	 */
	private String password;
	/**
	 * 0 男， 1 女， 2 未知
	 */
	private String gender;
	/**
	 * 
	 */
	private Date birthday;
	/**
	 * 
	 */
	private Date lastLoginTime;
	/**
	 * 
	 */
	private String lastLoginIp;
	/**
	 * 0 普通用户，1 VIP用户，2 高级VIP用户
	 */
	private String userLevel;
	/**
	 * 用户昵称或网络名称
	 */
	private String nickname;
	/**
	 * 用户手机号码
	 */
	private String mobile;
	/**
	 * 
	 */
	private String registerIp;
	/**
	 * 
	 */
	private String avatar;
	/**
	 * 
	 */
	private String weixinOpenid;
	/**
	 * 0 可用, 1 禁用, 2 删除
	 */
	private String status;
	/**
	 * 
	 */
	private Date addTime;
	/**
	 * 
	 */
	private Integer deleted;
	/**
	 * 创建时间
	 */
	private Date gmtCreate;
	/**
	 * 地址
	 */
	private String address;
	/**
	 * 所属省份
	 */
	private String province;
	/**
	 * 所属城市
	 */
	private String city;

}
