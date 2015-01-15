package com.sechand.platform.serviceimpl;

import java.util.List;

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
	public List<Trade> listPageRowsAccountsByKeyword(int currentPage,
			int pageSize, String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countByKeyword(String keyword) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteByIds(String[] ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean updateTrade(Trade trade) {
		// TODO Auto-generated method stub
		return false;
	}

}
