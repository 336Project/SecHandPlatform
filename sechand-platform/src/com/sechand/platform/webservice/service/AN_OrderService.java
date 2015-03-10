package com.sechand.platform.webservice.service;

import java.util.List;

import com.sechand.platform.base.BaseService;
import com.sechand.platform.model.Order;

public interface AN_OrderService extends BaseService{
	/**
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
	 * @author lixiaowei
	 * 2015-1-16 下午3:32:51
	 * @param order
	 * @return 
	 * TODO 用户更新报修信息
	 */
	public String updateByCustomer(Order order);
	/**
	 * 
	 * @author lixiaowei
	 * 2015-1-16 下午4:29:59
	 * @param id
	 * @return 
	 * TODO 根据订单id取消订单
	 */
	public String cancelById(String id);
	/**
	 * 
	 * @author lixiaowei
	 * 2015-1-16 下午4:29:59
	 * @param id
	 * @return 
	 * TODO 根据订单id确认订单
	 */
	public String confirmById(String id);
	/**
	 * 
	 * @Author:Helen  
	 * 2015-1-16下午9:44:56
	 * @param id
	 * @return
	 * String
	 * @TODO 完成订单
	 */
	public String completeById(String id);
	public String updateByCustomer(String orderId,String companyId,String contactTelUser,String repairContent);
	public String repairByCustomer(String companyId,String customerUser,String contactTelUser, String repairContent,String userId);
	public List<Order> listCustomerOrdersByPageRows(String roleCode,String ids,int currentPage,String keyword);
	public int countCustomerByKeyword(String roleCode,String ids,String keyword);
	public String quoteByOrder(String id,String price,String contactTelCompany);
}
