package com.sechand.platform.model;

import java.io.Serializable;
/**
 * 用户账户记录表
 */
public class Account implements Serializable{
	private static final long serialVersionUID = -3761987714840801074L;
	public static final String STATUS_CONFIRM="已确认";
	public static final String STATUS_TO_CONFIRM="待确认";
	
	public static final String TYPE_RECHARGE="充值";
	public static final String TYPE_CONSUME="消费";
	public static final String TYPE_TO_ACCOUNT="到账";
	
	private Integer id;
	private Integer userId;//关联用户id
	private String userName;//关联用户账号
	private String createTime;//创建时间
	private String completeTime;//完成时间
	private String money;//交易金额
	private String type;//交易类型
	private String status;//状态
	private String remark;//备注
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getCompleteTime() {
		return completeTime;
	}
	public void setCompleteTime(String completeTime) {
		this.completeTime = completeTime;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
