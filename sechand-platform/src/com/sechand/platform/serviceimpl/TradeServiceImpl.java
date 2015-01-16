package com.sechand.platform.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.sechand.platform.base.BaseServiceImpl;
import com.sechand.platform.model.Trade;
import com.sechand.platform.service.TradeService;

public class TradeServiceImpl extends BaseServiceImpl implements
		TradeService {

	@Override
	public long add(Trade trade) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public void deleteByIds(String[] ids) {
		baseDao.deleteByClassNameAndIds(Trade.class, ids);
	}


	@Override
	public List<Trade> listPageRowsByKeyword(int currentPage, int pageSize,
			String keyword) {
		Map<String, Object> whereParams=new HashMap<String, Object>();
		if(!StringUtils.isEmpty(keyword)){
			whereParams.put("or_fromUserName_like", keyword);
			whereParams.put("or_toUserName_like", keyword);
			whereParams.put("or_fromUserNickName_like", keyword);
			whereParams.put("or_toUserNickName_like", keyword);
			whereParams.put("or_money_like", keyword);
			whereParams.put("or_status_like",keyword);
			whereParams.put("or_time_like",keyword);
		}
		return baseDao.listPageRowsByClassNameAndParams(Trade.class, whereParams, currentPage, pageSize);
	}
	
	@Override
	public int countByKeyword(String keyword) {
		Map<String, Object> whereParams=new HashMap<String, Object>();
		if(!StringUtils.isEmpty(keyword)){
			whereParams.put("or_fromUserName_like", keyword);
			whereParams.put("or_toUserName_like", keyword);
			whereParams.put("or_fromUserNickName_like", keyword);
			whereParams.put("or_toUserNickName_like", keyword);
			whereParams.put("or_money_like", keyword);
			whereParams.put("or_status_like",keyword);
			whereParams.put("or_time_like",keyword);
		}
		return baseDao.countByClassNameAndParams(Trade.class, whereParams);
	}
}
