package com.sechand.platform.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.sechand.platform.base.BaseAction;
import com.sechand.platform.model.Order;
import com.sechand.platform.service.OrderService;
import com.sechand.platform.utils.DataTableParams;


public class OrderAction extends BaseAction{
	private OrderService orderService;
	private String ids;
	private String dataTableParams;//表单参数,json格式
	private String status;//订单状态
	private Order order;
	
	/**
	 * 
	 * 2015-1-13 下午5:48:57
	 * @return 
	 * TODO 获取所有订单列表信息
	 */
	public String listOrdersByParams(){
		DataTableParams params=DataTableParams.getInstance();
		params.parse(dataTableParams);
		Map<String, Object> dataMap=new HashMap<String, Object>();
		List<Order> orders=orderService.listOrdersByPageRows(params.current_page, params.page_size, params.keyword);
		int count=orderService.countByKeyword(params.keyword);
		dataMap.put("recordsTotal", count);
		dataMap.put("recordsFiltered", count);
		dataMap.put("draw",params.draw);
		dataMap.put("data", orders);
		json.setMsg(dataMap);
		json.setSuccess(true);
		return SUCCESS;
	}
	/**
	 * 
	 * 2015-1-14 下午5:28:40
	 * @return 
	 * TODO 根据ids批量作废订单
	 */
	public String disableOrderByIds(){
		if(orderService.disableByIds(ids)){
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
		if(orderService.updateStatusByIds(ids, status)){
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
			orderService.deleteByClassNameAndIds(Order.class, ids.split(","));
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
	 * @Author:Helen  
	 * 2015-1-14下午9:12:57
	 * @return
	 * String
	 * @TODO 获取用户的订单列表信息
	 */
	public String listCustomerOrdersByParams(){
		DataTableParams params=DataTableParams.getInstance();
		params.parse(dataTableParams);
		Map<String, Object> dataMap=new HashMap<String, Object>();
		List<Order> orders=orderService.listCustomerOrdersByPageRows(params.current_page, params.page_size, params.keyword);
		int count=orderService.countCustomerByKeyword(params.keyword);
		dataMap.put("recordsTotal", count);
		dataMap.put("recordsFiltered", count);
		dataMap.put("draw",params.draw);
		dataMap.put("data", orders);
		json.setMsg(dataMap);
		json.setSuccess(true);
		return SUCCESS;
	}
	/**
	 * 
	 * 2015-1-16 下午3:47:57
	 * @return 
	 * TODO 用户报修
	 */
	public String repairByCustomer(){
		String msg=orderService.repairByCustomer(order);
		if(StringUtils.isNotBlank(msg)){
			json.setMsg(msg);
			json.setSuccess(true);
		}else{
			json.setMsg("报修失败!");
			json.setSuccess(false);
		}
		return SUCCESS;
	}
	/**
	 * 
	 * @author lixiaowei
	 * 2015-1-16 下午3:31:46
	 * @return 
	 * TODO 用户更新报修
	 */
	public String updateByCustomer(){
		String msg=orderService.updateByCustomer(order);
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
	 * @author lixiaowei
	 * 2015-1-16 下午4:14:53
	 * @return 
	 * TODO 批量取消订单
	 */
	public String cancelOrderById(){
		String msg=orderService.cancelById(ids);
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
	 * 
	 * @author lixiaowei
	 * 2015-1-16 下午4:32:10
	 * @return  
	 * TODO 订单确认
	 */
	public String confirmOrderById(){
		String msg=orderService.confirmById(ids);
		if(StringUtils.isNotBlank(msg)){
			json.setMsg(msg);
			json.setSuccess(true);
		}else{
			json.setMsg("确认失败!");
			json.setSuccess(false);
		}
		return SUCCESS;
	}
	
	public OrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
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
}
