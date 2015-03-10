package com.sechand.platform.webservice.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.sechand.platform.base.BaseAction;
import com.sechand.platform.model.Trade;
import com.sechand.platform.service.TradeService;
import com.sechand.platform.utils.DataTableParams;
import com.sechand.platform.webservice.service.AN_TradeService;

public class AN_TradeAction extends BaseAction{
	private AN_TradeService appTradeService;
	private String dataTableParams;//表单参数,json格式
	private String ids;
	private int currentPage;
	private String keyword;
	
	/**
	 * TODO 获取交易记录列表
	 */
	public String listTradesByParams(){
		Map<String, Object> dataMap=new HashMap<String, Object>();
		List<Trade> Trades=appTradeService.listPageRowsByKeyword(currentPage,keyword);
		int count=appTradeService.countByKeyword(keyword);
		dataMap.put("recordsTotal", count);
		dataMap.put("data", Trades);
		json.setMsg(dataMap);
		json.setSuccess(true);
		return SUCCESS;
	}
	/**
	 * 
	 * 2015-1-16 下午12:33:42
	 * @return 
	 * TODO 批量删除
	 */
	public String deleteTradeByIds(){
		if(!StringUtils.isBlank(ids)){
			String[] idList=ids.split(",");
			appTradeService.deleteByIds(idList);
			json.setMsg("删除成功!");
			json.setSuccess(true);
		}else{
			json.setMsg("删除失败!");
			json.setSuccess(false);
		}
		return SUCCESS;
	}
	
	public String getDataTableParams() {
		return dataTableParams;
	}

	public void setDataTableParams(String dataTableParams) {
		this.dataTableParams = dataTableParams;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
	public AN_TradeService getAppTradeService() {
		return appTradeService;
	}
	public void setAppTradeService(AN_TradeService appTradeService) {
		this.appTradeService = appTradeService;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
}
