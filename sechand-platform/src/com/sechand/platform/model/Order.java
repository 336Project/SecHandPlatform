package com.sechand.platform.model;

import java.io.Serializable;

/**
 * 订单实体类
 */
public class Order implements Serializable{
	private static final long serialVersionUID = 8708517204948499216L;
	public static final String STATUS_COM="已完成";
	public static final String STATUS_NEW="新订单";
	public static final String STATUS_CONFIRM="已确认";
	public static final String STATUS_CANCEL="已取消";
	
	private Integer id;
	private Integer userId;//需要维修的用户id
	private Integer companyId;//维修电器公司id
	private String createTime;//创建时间
	private String completeTime;//完成时间
	private String status;//状态
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
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
