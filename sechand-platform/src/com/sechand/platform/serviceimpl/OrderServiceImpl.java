package com.sechand.platform.serviceimpl;

import com.sechand.platform.base.BaseServiceImpl;
import com.sechand.platform.model.Order;
import com.sechand.platform.service.OrderService;

public class OrderServiceImpl extends BaseServiceImpl implements OrderService{

	@Override
	public void delete(String id) {
		baseDao.deleteByClassNameAndId(Order.class, id);
		/*Order order=baseDao.getByClassAndId(Order.class, Integer.valueOf(id));
		if(order!=null){
			baseDao.deleteByHQL("from OrderItem where orderId="+order.getId());
			baseDao.delete(order);
		}*/
	}
}
