package com.sechand.platform.action;

import com.sechand.platform.base.BaseAction;
import com.sechand.platform.service.AccountService;

public class AccountAction extends BaseAction {
	private AccountService accountService;

	public AccountService getAccountService() {
		return accountService;
	}

	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}
	
}
