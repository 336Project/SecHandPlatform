package com.sechand.platform.action;

import com.sechand.platform.base.BaseAction;
import com.sechand.platform.service.OrderService;


public class OrderAction extends BaseAction{
	private OrderService orderService;
	private String id;
	
	public String deleteById(){
		orderService.delete(id);
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
}
