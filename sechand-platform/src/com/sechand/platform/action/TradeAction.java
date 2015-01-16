package com.sechand.platform.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.sechand.platform.base.BaseAction;
import com.sechand.platform.model.Trade;
import com.sechand.platform.service.TradeService;
import com.sechand.platform.utils.DataTableParams;

public class TradeAction extends BaseAction{
	private TradeService tradeService;
	private String dataTableParams;//表单参数,json格式
	private String ids;
	
	/**
	 * 
	 * 2015-1-16 下午12:32:49
	 * @return 
	 * TODO 获取交易记录列表
	 */
	public String listTradesByParams(){
		DataTableParams params=DataTableParams.getInstance();
		params.parse(dataTableParams);
		Map<String, Object> dataMap=new HashMap<String, Object>();
		List<Trade> Trades=tradeService.listPageRowsByKeyword(params.current_page, params.page_size, params.keyword);
		int count=tradeService.countByKeyword(params.keyword);
		dataMap.put("recordsTotal", count);
		dataMap.put("recordsFiltered", count);
		dataMap.put("draw",params.draw);
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
			tradeService.deleteByIds(idList);
			json.setMsg("删除成功!");
			json.setSuccess(true);
		}else{
			json.setMsg("删除失败!");
			json.setSuccess(false);
		}
		return SUCCESS;
	}
	
	
	public TradeService getTradeService() {
		return tradeService;
	}

	public void setTradeService(TradeService tradeService) {
		this.tradeService = tradeService;
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
}
