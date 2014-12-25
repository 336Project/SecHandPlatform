package com.sechand.platform.webservice.service;

import com.sechand.platform.base.BaseService;
import com.sechand.platform.model.Account;

public interface AN_AccountService extends BaseService{
	public Account login(String username,String password);
}
