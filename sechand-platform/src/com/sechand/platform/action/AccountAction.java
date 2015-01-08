package com.sechand.platform.action;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.DocFlavor.STRING;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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
	private String dataTableParams;
	
	
	private int start;
	private int length;
	private int draw;
	private String sSearch;

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
			/*json.setSuccess(false);
			json.setMsg("用户名或密码错误!");*/
			json.setSuccess(true);
			json.setMsg("/index.jsp");
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
		//int draw=0;
		String iDisplayStart="";
		String iDisplayLength="";
		String sSearch="";
		//int start= 0;
		//int length=10;
		JSONArray jsonArray=JSON.parseArray(dataTableParams);
		
		if(jsonArray!=null){
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject params=jsonArray.getJSONObject(i);
				if(params.getString("name").equals("draw")){
					//draw=params.getIntValue("value");
				}else if(params.getString("name").equals("start")){
					iDisplayStart=params.getString("value");
				}else if(params.getString("name").equals("length")){
					iDisplayLength=params.getString("value");
				}else if(params.getString("name").equals("sSearch")){
					sSearch=params.getString("value");
				}
			}
		}
		
		try {
			start=Integer.parseInt(iDisplayStart);
		} catch (Exception e) {
		}
		try {
			length=Integer.parseInt(iDisplayLength);
		} catch (Exception e) {
		}
		int curr=start/length+1;
		Map<String, Object> dataMap=new HashMap<String, Object>();
		List<Account> users=accountService.listPageRowsUsersByKeyword(curr, length, sSearch);//accountService.listUsers();
		int count=accountService.countByKeyword(sSearch);
		dataMap.put("recordsTotal", count);
		/*dataMap.put("sEcho",sEcho);*/
		dataMap.put("recordsFiltered", count);
		dataMap.put("draw",draw);
		dataMap.put("data", users);
		if(users!=null){
			//json=(JsonResult) dataMap;
			json.setMsg(dataMap);
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
	public String getDataTableParams() {
		return dataTableParams;
	}
	public void setDataTableParams(String dataTableParams) {
		this.dataTableParams = dataTableParams;
	}
	public String getsSearch() {
		return sSearch;
	}
	public void setsSearch(String sSearch) {
		this.sSearch = sSearch;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public int getDraw() {
		return draw;
	}
	public void setDraw(int draw) {
		this.draw = draw;
	}
}
