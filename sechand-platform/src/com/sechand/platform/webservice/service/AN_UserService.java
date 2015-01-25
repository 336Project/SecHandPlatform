package com.sechand.platform.webservice.service;


import com.sechand.platform.base.BaseService;
import com.sechand.platform.model.User;

public interface AN_UserService extends BaseService {
	public User login(String username,String password);
}
