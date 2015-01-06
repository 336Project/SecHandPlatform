package com.sechand.platform.service;

import java.util.List;

import com.sechand.platform.base.BaseService;
import com.sechand.platform.model.Account;




public interface AccountService extends BaseService{
	public boolean login(String username,String password,String roleName);
	public long add(Account account);
	public List<Account> listUsers();
	/**
	 * 
	 * @author lixiaowei
	 * 2015-1-6 下午12:43:16
	 * @param currentPage
	 * @param pageSize
	 * @param keyword
	 * @return 
	 * TODO 根据搜索关键字分页获取数据
	 */
	public List<Account> listPageRowsUsersByKeyword(int currentPage,int pageSize,String keyword);
	/**
	 * 
	 * @author lixiaowei
	 * 2015-1-6 下午12:54:15
	 * @param keyword
	 * @return 
	 * TODO 根据搜索关键字统计记录条数
	 */
	public int countByKeyword(String keyword);
}
