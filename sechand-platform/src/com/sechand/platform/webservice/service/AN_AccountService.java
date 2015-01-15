package com.sechand.platform.webservice.service;

import com.sechand.platform.base.BaseService;
import com.sechand.platform.model.User;

public interface AN_AccountService extends BaseService{
	public User login(String username,String password);
}
