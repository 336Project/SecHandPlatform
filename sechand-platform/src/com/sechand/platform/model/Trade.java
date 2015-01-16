package com.sechand.platform.model;

import java.io.Serializable;
/**
 * 交易记录表
 */
public class Trade implements Serializable{
	private static final long serialVersionUID = -5847741043676565150L;
	public static final String STATUS_SUCCESS="交易成功";
	public static final String STATUS_FAIL="交易失败";
	private Integer id;
	private Integer fromUserId;//资金流出的用户id
	private Integer toUserId;//资金流进的用户id
	private String fromUserName;//资金流出的用户账号
	private String toUserName;//资金流进的用户账号
	private String fromUserNickName;//资金流出的用户昵称
	private String toUserNickName;//资金流进的用户昵称
	private String money;//交易金额
	private String status;//状态
	private String time;//交易时间
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getFromUserId() {
		return fromUserId;
	}
	public void setFromUserId(Integer fromUserId) {
		this.fromUserId = fromUserId;
	}
	public Integer getToUserId() {
		return toUserId;
	}
	public void setToUserId(Integer toUserId) {
		this.toUserId = toUserId;
	}
	public String getFromUserName() {
		return fromUserName;
	}
	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}
	public String getToUserName() {
		return toUserName;
	}
	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
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
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getFromUserNickName() {
		return fromUserNickName;
	}
	public void setFromUserNickName(String fromUserNickName) {
		this.fromUserNickName = fromUserNickName;
	}
	public String getToUserNickName() {
		return toUserNickName;
	}
	public void setToUserNickName(String toUserNickName) {
		this.toUserNickName = toUserNickName;
	}
}
