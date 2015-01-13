package com.sechand.platform.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sechand.platform.base.BaseAction;
import com.sechand.platform.model.Order;
import com.sechand.platform.service.OrderService;
import com.sechand.platform.utils.DataTableParams;


public class OrderAction extends BaseAction{
	private OrderService orderService;
	private String id;
	private String dataTableParams;//表单参数,json格式
	
	public String deleteById(){
		orderService.delete(id);
		return SUCCESS;
	}
	/**
	 * 
	 * @author lixiaowei
	 * 2015-1-13 下午5:48:57
	 * @return 
	 * TODO 获取所有订单列表信息
	 */
	public String listOrdersByParams(){
		DataTableParams params=DataTableParams.getInstance();
		params.parse(dataTableParams);
		Map<String, Object> dataMap=new HashMap<String, Object>();
		List<Order> orders=orderService.listOrdersByPageRows(params.current_page, params.page_size, params.keyword);//accountService.listUsers();
		int count=orderService.countByKeyword(params.keyword);
		dataMap.put("recordsTotal", count);
		dataMap.put("recordsFiltered", count);
		dataMap.put("draw",params.draw);
		dataMap.put("data", orders);
		json.setMsg(dataMap);
		json.setSuccess(true);
		return SUCCESS;
	}
	public OrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public String getDataTableParams() {
		return dataTableParams;
	}
	public void setDataTableParams(String dataTableParams) {
		this.dataTableParams = dataTableParams;
	}
}
