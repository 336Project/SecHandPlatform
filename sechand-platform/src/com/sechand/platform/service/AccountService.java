package com.sechand.platform.service;

import com.sechand.platform.model.Account;




public interface AccountService {
	public boolean login(String username,String password);
	public long add(Account account);
}
