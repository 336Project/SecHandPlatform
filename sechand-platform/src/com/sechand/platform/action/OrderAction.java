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
	private String ids;
	private String dataTableParams;//表单参数,json格式
	
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
}
