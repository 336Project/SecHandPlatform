package com.sechand.platform.action;


import java.util.List;

import com.sechand.platform.base.BaseAction;
import com.sechand.platform.model.Account;
import com.sechand.platform.service.AccountService;
import com.sechand.platform.utils.WebUtil;



public class AccountAction extends BaseAction{
	private AccountService accountService;
	private Account account;
	private String username;//用户名
	private String password;//密码
	private String type;//角色类型
	/**
	 * 
	 * @Author:Helen  
	 * 2014-12-24下午8:48:14
	 * @return
	 * String
	 * @TODO 登录
	 */
	public String login(){
		if(accountService.login(username, password,type)){
			json.setSuccess(true);
			json.setMsg("/index.jsp");
		}else{
			json.setSuccess(false);
			json.setMsg("用户名或密码错误!");
		}
		return SUCCESS;
	}
	/**
	 * 
	 * @Author:Helen  
	 * 2014-12-24下午8:48:30
	 * @return
	 * String
	 * @TODO 退出
	 */
	public String logout(){
		//将用户信息从session中移除
		WebUtil.remove4Session(WebUtil.KEY_LOGIN_USER_SESSION);
		return LOGIN;
	}
	/**
	 * 
	 * @Author:Helen  
	 * 2014-12-24下午8:48:39
	 * @return
	 * String
	 * @TODO 注册
	 */
	public String register(){
		//注册完跳到登录页
		long id=accountService.add(account);
		if(id<0){
			return ERROR;
		}
		return LOGIN;
	}
	
	public String listUsers(){
		List<Account> users=accountService.listUsers();
		if(users!=null){
			json.setMsg(users);
			json.setSuccess(true);
		}else{
			json.setSuccess(false);
		}
		return SUCCESS;
	}
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public AccountService getAccountService() {
		return accountService;
	}

	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
