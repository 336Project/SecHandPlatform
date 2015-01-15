package com.sechand.platform.action;

import com.sechand.platform.base.BaseAction;
import com.sechand.platform.service.TradeService;

public class TradeAction extends BaseAction{
	private TradeService tradeService;

	public TradeService getTradeService() {
		return tradeService;
	}

	public void setTradeService(TradeService tradeService) {
		this.tradeService = tradeService;
	}
}
