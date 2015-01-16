package com.sechand.platform.service;

import java.util.List;

import com.sechand.platform.base.BaseService;
import com.sechand.platform.model.Account;


public interface AccountService extends BaseService{
	public long add(Account account);
	/**
	 * 
	 * 2015-1-6 下午12:43:16
	 * @param currentPage
	 * @param pageSize
	 * @param keyword
	 * @return 
	 * TODO 根据搜索关键字分页获取数据
	 */
	public List<Account> listPageRowsAccountsByKeyword(int currentPage,int pageSize,String keyword);
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
	/**
	 * 
	 * 2015-1-13 上午11:21:33
	 * @param account
	 * @return 
	 * TODO 更新账户信息
	 */
	public boolean updateAccount(Account account);
	/**
	 * 
	 * @author lixiaowei
	 * 2015-1-15 下午6:07:45
	 * @param id
	 * @return 
	 * TODO 确认充值
	 */
	public String confirmById(String id);
}
