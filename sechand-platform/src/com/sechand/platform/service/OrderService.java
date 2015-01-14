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
	/**
	 * 
	 * @Author:Helen  
	 * 2015-1-14下午10:19:53
	 * @param ids
	 * @param status
	 * @return
	 * boolean
	 * @TODO 批量更新订单状态
	 */
	public boolean updateStatusByIds(String ids,String status);
	/**
	 * 
	 * @Author:Helen  
	 * 2015-1-14下午9:15:31
	 * @param userId
	 * @param currentPage
	 * @param pageSize
	 * @param keyword
	 * @return
	 * List<Order>
	 * @TODO 获取一个普通用户的所有订单信息
	 */
	public List<Order> listCustomerOrdersByPageRows(int userId,int currentPage, int pageSize, String keyword);
	/**
	 * 
	 * 2015-1-14 下午5:08:50
	 * @param keyword
	 * @return 
	 * TODO 根据关键字统计用户订单记录个数
	 */
	public int countCustomerByKeyword(Integer userId,String keyword);
}
