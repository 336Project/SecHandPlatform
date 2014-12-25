package com.sechand.platform.webservice.action;

import com.sechand.platform.base.BaseAction;
import com.sechand.platform.model.Account;
import com.sechand.platform.webservice.service.AN_AccountService;

public class AN_AccountAction extends BaseAction{
	private AN_AccountService accountService;
	private Account account;
	private String username;//用户名
	private String password;//密码
	/**
	 * 
	 * @Author:Helen  
	 * 2014-12-24下午8:48:14
	 * @return
	 * String
	 * @TODO 登录
	 */
	public String login(){
		account=accountService.login(username, password);
		if(account!=null){
			json.setSuccess(true);
			json.setMsg(account);
		}else{
			json.setSuccess(false);
			json.setMsg("用户名或密码错误!");
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

	public AN_AccountService getAccountService() {
		return accountService;
	}

	public void setAccountService(AN_AccountService accountService) {
		this.accountService = accountService;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}
