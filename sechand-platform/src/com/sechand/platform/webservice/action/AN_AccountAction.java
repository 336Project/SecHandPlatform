package com.sechand.platform.webservice.action;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sechand.platform.base.BaseAction;
import com.sechand.platform.model.Account;
import com.sechand.platform.webservice.service.AN_AccountService;

public class AN_AccountAction extends BaseAction{
	private AN_AccountService accountService;
	private Account account;
	private String params;//请求参数的json字符串
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
		//将请求参数封装成json字符串
		//例如:app/accountAction!login.action?params={"username":"admin","password":"123456"}
		if(params==null){
			json.setSuccess(false);
			json.setMsg("请求参数错误!");
		}else{
			JSONObject object=JSON.parseObject(params);
			username=object.getString("username");
			password=object.getString("password");
			if(username==null||password==null){
				json.setSuccess(false);
				json.setMsg("请求参数错误!");
			}else{
				account=accountService.login(username, password);
				if(account!=null){
					Map<String, String> map=new HashMap<String, String>();
					map.put("name", account.getNickName());
					map.put("username", account.getUserName());
					json.setSuccess(true);
					json.setMsg(map);
					//json.setMsg(account);第二种
				}else{
					json.setSuccess(false);
					json.setMsg("用户名或密码错误!");
				}
			}
		}
		//每个参数独立一个字符串
		//例如:app/accountAction!login.action?username=admin&password=123456
		/*if(username==null||password==null){
			json.setSuccess(false);
			json.setMsg("请求参数错误!");
		}else{
			account=accountService.login(username, password);
			if(account!=null){
				Map<String, String> map=new HashMap<String, String>();
				map.put("name", account.getNickName());
				map.put("age", account.getAge());
				map.put("username", account.getUserName());
				json.setSuccess(true);
				json.setMsg(map);
				//json.setMsg(account);第二种
			}else{
				json.setSuccess(false);
				json.setMsg("用户名或密码错误!");
			}
		}*/
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
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}

}
