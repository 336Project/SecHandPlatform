package com.sechand.platform.webservice.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sechand.platform.base.BaseAction;
import com.sechand.platform.model.Account;
import com.sechand.platform.model.User;
import com.sechand.platform.utils.DataTableParams;
import com.sechand.platform.webservice.service.AN_AccountService;

public class AN_AccountAction extends BaseAction {
	private AN_AccountService accountService;
	private User account;
	private String params;// 请求参数的json字符串
	private String username;// 用户名
	private String password;// 密码
	private String currentPage;
	private String keyword;
	private int id;
	private String ids;
	private String money;
	private String source;
	private String remark;

	public String getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @TODO 登录
	 */
	public String login() {
		// 将请求参数封装成json字符串
		// 例如:app/accountAction!login.action?params={"username":"admin","password":"123456"}
		if (params == null) {
			json.setSuccess(false);
			json.setMsg("请求参数错误!");
		} else {
			JSONObject object = JSON.parseObject(params);
			username = object.getString("username");
			password = object.getString("password");
			if (username == null || password == null) {
				json.setSuccess(false);
				json.setMsg("请求参数错误!");
			} else {
				account = accountService.login(username, password);
				if (account != null) {
					Map<String, String> map = new HashMap<String, String>();
					map.put("name", account.getNickName());
					map.put("username", account.getUserName());
					json.setSuccess(true);
					json.setMsg(map);
					// json.setMsg(account);第二种
				} else {
					json.setSuccess(false);
					json.setMsg("用户名或密码错误!");
				}
			}
		}
		// 每个参数独立一个字符串
		// 例如:app/accountAction!login.action?username=admin&password=123456
		/*
		 * if(username==null||password==null){ json.setSuccess(false);
		 * json.setMsg("请求参数错误!"); }else{ account=accountService.login(username,
		 * password); if(account!=null){ Map<String, String> map=new
		 * HashMap<String, String>(); map.put("name", account.getNickName());
		 * map.put("age", account.getAge()); map.put("username",
		 * account.getUserName()); json.setSuccess(true); json.setMsg(map);
		 * //json.setMsg(account);第二种 }else{ json.setSuccess(false);
		 * json.setMsg("用户名或密码错误!"); } }
		 */
		return SUCCESS;
	}

	/**
	 * @return TODO 获取账户列表信息
	 */
	public String listAccountsByParams() {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		int currentPageInt = Integer.parseInt(currentPage);
		// List<Account>
		// accounts=accountService.listPageRowsAccountsByKeyword(params.current_page,
		// params.page_size, params.keyword,false);
		List<Account> accounts = accountService
				.listPageRowsAccountsByKeywordAndId(ids, currentPageInt, 15,
						keyword, false);
		int count = accountService.countByKeyword(keyword, false);
		dataMap.put("recordsTotal", count);
		dataMap.put("recordsFiltered", count);
		// dataMap.put("draw",params.draw);
		dataMap.put("data", accounts);
		json.setMsg(dataMap);
		json.setSuccess(true);
		return SUCCESS;
	}

	public String listAccountsByParamsFromAdmin() {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		int currentPageInt = Integer.parseInt(currentPage);
		List<Account> accounts = accountService.listPageRowsAccountsByKeyword(
				currentPageInt, 15, keyword, true);
		int count = accountService.countByKeyword(keyword, true);
		dataMap.put("recordsTotal", count);
		dataMap.put("data", accounts);
		json.setMsg(dataMap);
		json.setSuccess(true);
		return SUCCESS;
	}
	/**
	 * TODO 用户取消充值记录
	 */
	public String cancelAccountById(){
		String msg=accountService.cancelById(ids);
		if(StringUtils.isNotBlank(msg)){
			json.setMsg(msg);
			json.setSuccess(true);
		}else{
			json.setMsg("取消失败!");
			json.setSuccess(false);
		}
		return SUCCESS;
	}
	
	//确认
	public String confirmAccount() {
		String msg = accountService.confirmById(ids);
		if (StringUtils.isNotBlank(msg)) {
			json.setMsg(msg);
			json.setSuccess(true);
		} else {
			json.setMsg("确认失败!");
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

	public AN_AccountService getAccountService() {
		return accountService;
	}

	public void setAccountService(AN_AccountService accountService) {
		this.accountService = accountService;
	}

	public User getAccount() {
		return account;
	}

	public void setAccount(User account) {
		this.account = account;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	// 用户申请充值
	public String applyRecharge() {
		if (accountService.add(id, money, source, remark) > 0) {
			json.setMsg("充值成功，等待确认!");
			json.setSuccess(true);
		} else {
			json.setMsg("充值失败!");
			json.setSuccess(false);
		}
		return SUCCESS;
	}

	// 管理员申请充值
	public String applyRechargeByAdmin() {
		if (accountService.add(id, money, Account.SOURCE_PLATFORM, remark) > 0) {
			json.setMsg("充值成功，等待确认!");
			json.setSuccess(true);
		} else {
			json.setMsg("充值失败!");
			json.setSuccess(false);
		}
		return SUCCESS;
	}

	// 申请提现
	public String applyPickup() {
		String msg = accountService.pickup(id, money);
		if (StringUtils.isNotBlank(msg)) {
			json.setMsg(msg);
			json.setSuccess(true);
		} else {
			json.setMsg("提现申请失败!");
			json.setSuccess(false);
		}
		return SUCCESS;
	}

	public String deleteAccountByIds() {
		if (StringUtils.isNotBlank(ids)) {
			accountService.deleteByIds(ids.split(","));
			json.setMsg("删除成功!");
			json.setSuccess(true);
		} else {
			json.setMsg("删除失败!");
			json.setSuccess(false);
		}
		return SUCCESS;
	}

}
