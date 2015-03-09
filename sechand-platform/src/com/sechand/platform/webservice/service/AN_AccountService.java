package com.sechand.platform.webservice.service;

import java.util.List;

import com.sechand.platform.base.BaseService;
import com.sechand.platform.model.Account;
import com.sechand.platform.model.User;

public interface AN_AccountService extends BaseService{
	public User login(String username,String password);
	
	/**
	 * 
	 * @param currentPage
	 * @param pageSize
	 * @param keyword
	 * @param isAdmin 是否是管理员
	 * @return 
	 * TODO 根据搜索关键字分页获取数据
	 */
	public List<Account> listPageRowsAccountsByKeyword(int currentPage,int pageSize,String keyword,boolean isAdmin);
	public List<Account> listPageRowsAccountsByKeywordAndId(String ids,int currentPage,int pageSize,String keyword,boolean isAdmin);
	/**
	 * @param keyword
	 * @return 
	 * TODO 根据搜索关键字统计记录条数
	 */
	public int countByKeyword(String keyword,boolean isAdmin);
	/**
	 * 
	 * @param keyword
	 * @return 
	 * TODO 充值
	 */
	public long add(int id,String money,String source,String remark);
	public String pickup(int id,String getmoney);
	/**
	 * @param id
	 * @return 
	 * TODO 确认充值
	 */
	public String confirmById(String id);
	/**
	 * @param ids 
	 * TODO 批量删除
	 */
	public void deleteByIds(String[] ids);
	public String cancelById(String id);
}
