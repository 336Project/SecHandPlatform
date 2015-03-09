package com.sechand.platform.webservice.service;

import java.util.List;

import com.sechand.platform.base.BaseService;
import com.sechand.platform.model.Trade;


public interface AN_TradeService extends BaseService{
	public long add(Trade trade);
	/**
	 * 
	 * 2015-1-6 下午12:43:16
	 * @param currentPage
	 * @param pageSize
	 * @param keyword
	 * @return 
	 * TODO 根据搜索关键字分页获取数据
	 */
	public List<Trade> listPageRowsByKeyword(int currentPage,String keyword);
	/**
	 * 
	 * 2015-1-6 下午12:54:15
	 * @param keyword
	 * @return 
	 * TODO 根据搜索关键字统计记录条数
	 */
	public int countByKeyword(String keyword);
	/**
	 * 
	 * 2015-1-8 下午4:50:25
	 * @param ids 
	 * TODO 批量删除
	 */
	public void deleteByIds(String[] ids);
}
