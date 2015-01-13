package com.sechand.platform.service;

import java.util.List;

import com.sechand.platform.base.BaseService;
import com.sechand.platform.model.Order;

public interface OrderService extends BaseService{
	public void delete(String id);
	public List<Order> listOrdersByPageRows(int currentPage, int pageSize, String keyword);
	public int countByKeyword(String keyword);
}
