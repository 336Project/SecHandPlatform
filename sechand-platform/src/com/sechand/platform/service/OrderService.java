package com.sechand.platform.service;

import java.util.List;

import com.sechand.platform.base.BaseService;
import com.sechand.platform.model.Order;

public interface OrderService extends BaseService{
	/**
	 * 
	 * 2015-1-14 下午5:09:11
	 * @param currentPage
	 * @param pageSize
	 * @param keyword
	 * @return 
	 * TODO 根据关键字分页获取所有订单信息
	 */
	public List<Order> listOrdersByPageRows(int currentPage, int pageSize, String keyword);
	/**
	 * 
	 * 2015-1-14 下午5:08:50
	 * @param keyword
	 * @return 
	 * TODO 根据关键字统计订单记录个数
	 */
	public int countByKeyword(String keyword);
	/**
	 * 
	 * 2015-1-14 下午5:11:11
	 * @return 
	 * TODO 批量作废订单
	 */
	public boolean disableByIds(String ids);
	
}
