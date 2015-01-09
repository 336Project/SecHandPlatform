package com.sechand.platform.action;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sechand.platform.base.BaseAction;
import com.sechand.platform.model.Account;
import com.sechand.platform.service.AccountService;
import com.sechand.platform.utils.SysUtils;
import com.sechand.platform.utils.WebUtil;



public class AccountAction extends BaseAction{
	private AccountService accountService;
	private Account account;
	private String username;//用户名
	private String password;//密码
	private String type;//角色类型
	private String dataTableParams;
	private String ids;
	

	/**
	 * 
	 * @Author:Helen  
	 * 2014-12-24下午8:48:14
	 * @return
	 * String
	 * @TODO 登录
	 */
	public String login(){
		Account a=accountService.login(username, password,type);
		if(a!=null){
			if(!Account.STATUS_NORMAL.equals(a.getStatus())){
				json.setSuccess(false);
				json.setMsg("该账户出现异常，请联系管理员!");
			}else{
				WebUtil.add2Session(WebUtil.KEY_LOGIN_USER_SESSION, a);
				accountService.updateColumnById("Account", "lastLoginTime", SysUtils.getDateFormat(new Date()), a.getId());
				json.setSuccess(true);
				json.setMsg("/index.jsp");
			}
		}else {
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
	 * @TODO 自行注册
	 */
	public String register(){
		//注册完跳到登录页
		account.setSource(Account.SOURCE_PLATFORM);
		long id=accountService.add(account);
		if(id<0){
			return ERROR;
		}
		return LOGIN;
	}
	/**
	 * 
	 * 2015-1-8 下午5:59:41
	 * @return 
	 * TODO 后台管理员添加
	 */
	public String addManual(){
		account.setSource(Account.SOURCE_MANUAL);
		long id=accountService.add(account);
		if(id>0){
			json.setMsg("添加成功!");
			json.setSuccess(true);
		}else{
			json.setMsg("添加失败!");
			json.setSuccess(false);
		}
		return SUCCESS;
	}
	/**
	 * 
	 * 2015-1-8 下午4:42:38
	 * @return 
	 * TODO 获取用户信息列表
	 */
	public String listUsers(){
		int draw=0;
		String iDisplayStart="";//记录开始位置
		String iDisplayLength="";//每页大小
		String sSearch="";//搜索关键字
		int start= 0;
		int length=10;
		//json解析
		JSONArray jsonArray=JSON.parseArray(dataTableParams);
		if(jsonArray!=null){
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject params=jsonArray.getJSONObject(i);
				if(params.getString("name").equals("draw")){
					draw=params.getIntValue("value")+1;
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
		int curr=start/length+1;//计算当前页
		Map<String, Object> dataMap=new HashMap<String, Object>();
		List<Account> users=accountService.listPageRowsUsersByKeyword(curr, length, sSearch);//accountService.listUsers();
		int count=accountService.countByKeyword(sSearch);
		dataMap.put("recordsTotal", count);
		dataMap.put("recordsFiltered", count);
		dataMap.put("draw",draw);
		dataMap.put("data", users);
		if(users!=null){
			json.setMsg(dataMap);
			json.setSuccess(true);
		}else{
			json.setSuccess(false);
		}
		return SUCCESS;
	}
	/**
	 * 
	 * 2015-1-8 下午4:47:48
	 * @return 
	 * TODO 批量删除用户
	 */
	public String deleteUsers(){
		if(!StringUtils.isBlank(ids)){
			String[] idList=ids.split(",");
			accountService.deleteByIds(idList);
			json.setMsg("删除成功!");
			json.setSuccess(true);
		}else{
			json.setMsg("删除失败!");
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
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
}
