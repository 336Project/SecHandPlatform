package com.sechand.platform.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.sechand.platform.base.BaseServiceImpl;
import com.sechand.platform.model.Order;
import com.sechand.platform.service.OrderService;

public class OrderServiceImpl extends BaseServiceImpl implements OrderService{


	@Override
	public List<Order> listOrdersByPageRows(int currentPage, int pageSize,
			String keyword) {
		Map<String, Object> whereParams=new HashMap<String, Object>();
		if(!StringUtils.isEmpty(keyword)){
			whereParams.put("or_customerUser_like", keyword);
			whereParams.put("or_customerCompany_like", keyword);
			whereParams.put("or_repairContent_like", keyword);
			whereParams.put("or_status_like",keyword);
		}
		return baseDao.listPageRowsByClassNameAndParams(Order.class, whereParams, currentPage, pageSize);
	}

	@Override
	public int countByKeyword(String keyword) {
		Map<String, Object> whereParams=new HashMap<String, Object>();
		if(!StringUtils.isEmpty(keyword)){
			whereParams.put("or_customerUser_like", keyword);
			whereParams.put("or_customerCompany_like", keyword);
			whereParams.put("or_repairContent_like", keyword);
			whereParams.put("or_status_like",keyword);
		}
		return baseDao.countByClassNameAndParams(Order.class, whereParams);
	}

	@Override
	public boolean disableByIds(String ids) {
		try{
			if(StringUtils.isNotBlank(ids)){
				baseDao.updateColumnByIds(Order.class, "status", Order.STATUS_INVALID, ids.split(","));
				return true;
			}
		}catch (Exception e) {
		}
		return false;
	}

	@Override
	public List<Order> listCustomerOrdersByPageRows(int userId,
			int currentPage, int pageSize, String keyword) {
		Map<String, Object> whereParams=new HashMap<String, Object>();
		whereParams.put("userId", userId);
		if(!StringUtils.isEmpty(keyword)){
			whereParams.put("or_customerCompany_like", keyword);
			whereParams.put("or_repairContent_like", keyword);
			whereParams.put("or_status_like",keyword);
		}
		return baseDao.listPageRowsByClassNameAndParams(Order.class, whereParams, currentPage, pageSize);
	}

	@Override
	public int countCustomerByKeyword(Integer userId, String keyword) {
		Map<String, Object> whereParams=new HashMap<String, Object>();
		whereParams.put("userId", userId);
		if(!StringUtils.isEmpty(keyword)){
			whereParams.put("or_customerCompany_like", keyword);
			whereParams.put("or_repairContent_like", keyword);
			whereParams.put("or_status_like",keyword);
		}
		return baseDao.countByClassNameAndParams(Order.class, whereParams);
	}

	@Override
	public boolean updateStatusByIds(String ids, String status) {
		try{
			if(StringUtils.isNotBlank(ids)&&StringUtils.isNotBlank(status)){
				if(Order.STATUS_CANCEL.equals(status)||
						Order.STATUS_COM.equals(status)||
						Order.STATUS_CONFIRM.equals(status)){
					baseDao.updateColumnByIds(Order.class, "status", status, ids.split(","));
					return true;
				}
			}
		}catch (Exception e) {
		}
		return false;
	}
}
