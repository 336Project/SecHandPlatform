package com.sechand.platform.service;

import com.sechand.platform.base.BaseService;
import com.sechand.platform.model.Account;




public interface AccountService extends BaseService{
	public boolean login(String username,String password,String roleName);
	public long add(Account account);
}
