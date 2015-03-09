package com.sechand.platform.webservice.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.sechand.platform.base.BaseAction;
import com.sechand.platform.model.Order;
import com.sechand.platform.utils.DataTableParams;
import com.sechand.platform.webservice.service.AN_OrderService;


public class AN_OrderAction extends BaseAction{
	private AN_OrderService appOrderService;
	private String orderId;
	private String companyId;
	private String contactTelUser;
	private String customerUser;
	private String repairContent;
	private String ids;
	private String dataTableParams;//表单参数,json格式
	private String status;//订单状态
	private Order order;
	private int currentPage;
	private String roleCode;
	private String price;
	private String contactTelCompany;

	// 获取所有订单列表信息
	public String listOrdersByParams(){
//		DataTableParams params=DataTableParams.getInstance();
//		params.parse(dataTableParams);
		Map<String, Object> dataMap=new HashMap<String, Object>();
		List<Order> orders=appOrderService.listOrdersByPageRows(currentPage, 15, keyword);
		int count=appOrderService.countByKeyword(keyword);
		dataMap.put("recordsTotal", count);
		dataMap.put("recordsFiltered", count);
//		dataMap.put("draw",params.draw);
		dataMap.put("data", orders);
		json.setMsg(dataMap);
		json.setSuccess(true);
		return SUCCESS;
	}
	
	//用户更新报修
	public String updateByCustomer(){
		String msg=appOrderService.updateByCustomer(orderId,companyId,contactTelUser,repairContent);
		if(StringUtils.isNotBlank(msg)){
			json.setMsg(msg);
			json.setSuccess(true);
		}else{
			json.setMsg("修改失败!");
			json.setSuccess(false);
		}
		return SUCCESS;
	}
	
	/**
	 * 
	 * 2015-1-14 下午5:28:40
	 * @return 
	 * TODO 根据ids批量作废订单
	 */
	public String disableOrderByIds(){
		if(appOrderService.disableByIds(ids)){
			json.setMsg("作废成功!");
			json.setSuccess(true);
		}else{
			json.setMsg("作废失败!");
			json.setSuccess(false);
		}
		return SUCCESS;
	}
	/**
	 * 
	 * @Author:Helen  
	 * 2015-1-14下午10:28:01
	 * @return
	 * String
	 * @TODO 批量更新订单状态
	 */
	public String updateStatusByIds(){
		if(appOrderService.updateStatusByIds(ids, status)){
			json.setMsg("操作成功!");
			json.setSuccess(true);
		}else {
			json.setMsg("操作失败!");
			json.setSuccess(false);
		}
		return SUCCESS;
	}
	/**
	 * 
	 * @Author:Helen  
	 * 2015-1-14下午10:40:44
	 * @return
	 * String
	 * @TODO 批量删除订单
	 */
	public String deleteOrderByIds(){
		if(StringUtils.isNotBlank(ids)){
			appOrderService.deleteByClassNameAndIds(Order.class, ids.split(","));
			json.setMsg("删除成功!");
			json.setSuccess(true);
		}else{
			json.setMsg("删除失败!");
			json.setSuccess(false);
		}
		return SUCCESS;
	}
	
	//用户操作
	/**
	 * 
	 * String
	 * @TODO 获取用户的订单列表信息
	 */
	public String listCustomerOrdersByParams(){
		DataTableParams params=DataTableParams.getInstance();
		params.parse(dataTableParams);
		Map<String, Object> dataMap=new HashMap<String, Object>();
		List<Order> orders=appOrderService.listCustomerOrdersByPageRows(roleCode,ids,currentPage,keyword);
		int count=appOrderService.countCustomerByKeyword(roleCode,ids,keyword);
		dataMap.put("recordsTotal",count);
		dataMap.put("data", orders);
		json.setMsg(dataMap);
		json.setSuccess(true);
		return SUCCESS;
	}
	
	// 用户报修
	public String repairByCustomer(){
		String msg=appOrderService.repairByCustomer(companyId,getCustomerUser(),contactTelUser,repairContent,ids);
		if(StringUtils.isNotBlank(msg)){
			json.setMsg(msg);
			json.setSuccess(true);
		}else{
			json.setMsg("报修失败!");
			json.setSuccess(false);
		}
		return SUCCESS;
	}
	//批量取消订单
	public String cancelOrderById(){
		String msg=appOrderService.cancelById(ids);
		if(StringUtils.isNotBlank(msg)){
			json.setMsg(msg);
			json.setSuccess(true);
		}else{
			json.setMsg("取消失败!");
			json.setSuccess(false);
		}
		return SUCCESS;
	}
	/**
	 * TODO 订单确认
	 */
	public String confirmOrderById(){
		String msg=appOrderService.confirmById(ids);
		if(StringUtils.isNotBlank(msg)){
			json.setMsg(msg);
			json.setSuccess(true);
		}else{
			json.setMsg("确认失败!");
			json.setSuccess(false);
		}
		return SUCCESS;
	}
	
	/**
	 * @TODO 公司对新订单进行报价
	 */
	public String quoteOrderByCompany(){
		String msg=appOrderService.quoteByOrder(ids,price,contactTelCompany);
		if(StringUtils.isNotBlank(msg)){
			json.setMsg(msg);
			json.setSuccess(true);
		}else{
			json.setMsg("报价失败!");
			json.setSuccess(false);
		}
		return SUCCESS;
	}
	/**
	 * 
	 * String
	 * @TODO 完成维修
	 */
	public String completeOrderById(){
		String msg=appOrderService.completeById(ids);
		if(StringUtils.isNotBlank(msg)){
			json.setMsg(msg);
			json.setSuccess(true);
		}else{
			json.setMsg("操作失败!");
			json.setSuccess(false);
		}
		return SUCCESS;
	}
	
	public String getDataTableParams() {
		return dataTableParams;
	}
	public void setDataTableParams(String dataTableParams) {
		this.dataTableParams = dataTableParams;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getContactTelUser() {
		return contactTelUser;
	}
	public void setContactTelUser(String contactTelUser) {
		this.contactTelUser = contactTelUser;
	}
	public String getRepairContent() {
		return repairContent;
	}
	public void setRepairContent(String repairContent) {
		this.repairContent = repairContent;
	}
	public AN_OrderService getAppOrderService() {
		return appOrderService;
	}
	public void setAppOrderService(AN_OrderService appOrderService) {
		this.appOrderService = appOrderService;
	}
	public String getCustomerUser() {
		return customerUser;
	}
	public void setCustomerUser(String customerUser) {
		this.customerUser = customerUser;
	}
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	private String keyword;
	
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getContactTelCompany() {
		return contactTelCompany;
	}

	public void setContactTelCompany(String contactTelCompany) {
		this.contactTelCompany = contactTelCompany;
	}
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
}
