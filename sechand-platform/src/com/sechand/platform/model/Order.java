package com.sechand.platform.model;

import java.io.Serializable;

/**
 * 订单实体类
 */
public class Order implements Serializable{
	private static final long serialVersionUID = 8708517204948499216L;
	public static final String STATUS_NEW="新订单";
	public static final String STATUS_QUOTE="已报价";
	public static final String STATUS_COM="已完成";
	public static final String STATUS_CONFIRM="已确认";
	public static final String STATUS_CANCEL="已取消";
	public static final String STATUS_INVALID="已作废";
	
	private Integer id;
	private Integer userId;//需要维修的用户id
	private Integer companyId;//维修电器公司id
	private String customerUser;//用户账户名称
	private String customerCompany;//维修公司账户名称
	private String createTime;//创建时间
	private String completeTime;//完成时间
	private String quoteTime;//报价时间
	private String repairContent;//报修内容
	private String contactTelUser;//客户联系电话
	private String contactTelCompany;//公司联系电话
	private String price;//维修报价
	//private String receivables;//应收款
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
	public String getRepairContent() {
		return repairContent;
	}
	public void setRepairContent(String repairContent) {
		this.repairContent = repairContent;
	}
	public String getCustomerUser() {
		return customerUser;
	}
	public void setCustomerUser(String customerUser) {
		this.customerUser = customerUser;
	}
	public String getCustomerCompany() {
		return customerCompany;
	}
	public void setCustomerCompany(String customerCompany) {
		this.customerCompany = customerCompany;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getContactTelUser() {
		return contactTelUser;
	}
	public void setContactTelUser(String contactTelUser) {
		this.contactTelUser = contactTelUser;
	}
	public String getContactTelCompany() {
		return contactTelCompany;
	}
	public void setContactTelCompany(String contactTelCompany) {
		this.contactTelCompany = contactTelCompany;
	}
	public String getQuoteTime() {
		return quoteTime;
	}
	public void setQuoteTime(String quoteTime) {
		this.quoteTime = quoteTime;
	}
	
}
