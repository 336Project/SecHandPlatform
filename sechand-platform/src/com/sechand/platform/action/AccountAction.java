package com.sechand.platform.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sechand.platform.base.BaseAction;
import com.sechand.platform.model.Account;
import com.sechand.platform.service.AccountService;
import com.sechand.platform.utils.DataTableParams;

public class AccountAction extends BaseAction {
	private AccountService accountService;
	private String dataTableParams;//表单参数,json格式
	private Account account;
	private String ids;
	
	/**
	 * 
	 * @author lixiaowei
	 * 2015-1-15 下午4:29:47
	 * @return 
	 * TODO 获取账户列表信息
	 */
	public String listAccountsByParams(){
		DataTableParams params=DataTableParams.getInstance();
		params.parse(dataTableParams);
		Map<String, Object> dataMap=new HashMap<String, Object>();
		List<Account> accounts=accountService.listPageRowsAccountsByKeyword(params.current_page, params.page_size, params.keyword);
		int count=accountService.countByKeyword(params.keyword);
		dataMap.put("recordsTotal", count);
		dataMap.put("recordsFiltered", count);
		dataMap.put("draw",params.draw);
		dataMap.put("data", accounts);
		json.setMsg(dataMap);
		json.setSuccess(true);
		return SUCCESS;
	}
	/**
	 * 
	 * @author lixiaowei
	 * 2015-1-15 下午5:25:59
	 * @return 
	 * TODO 账户充值
	 */
	public String recharge(){
		if(accountService.add(account)>0){
			json.setMsg("充值成功!");
			json.setSuccess(true);
		}else{
			json.setMsg("充值失败!");
			json.setSuccess(false);
		}
		return SUCCESS;
	}
	/**
	 * 
	 * @author lixiaowei
	 * 2015-1-15 下午6:05:58
	 * @return 
	 * TODO 确认充值
	 */
	public String confirmAccount(){
		if(accountService.confirmById(ids)){
			json.setMsg("确认成功!");
			json.setSuccess(true);
		}else{
			json.setMsg("确认失败!");
			json.setSuccess(false);
		}
		return SUCCESS;
	}
	
	
	public AccountService getAccountService() {
		return accountService;
	}

	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}
	public String getDataTableParams() {
		return dataTableParams;
	}
	public void setDataTableParams(String dataTableParams) {
		this.dataTableParams = dataTableParams;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	
}
