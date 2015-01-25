package com.sechand.platform.webservice.action;

import com.sechand.platform.base.BaseAction;
import com.sechand.platform.model.User;
import com.sechand.platform.webservice.service.AN_UserService;

public class AN_UserAction extends BaseAction {
	private AN_UserService appUserService;
	private String username;
	private String password;
	private User user;
	
	public String login(){
		user=appUserService.login(username, password);
		return SUCCESS;
	}

	public AN_UserService getAppUserService() {
		return appUserService;
	}

	public void setAppUserService(AN_UserService appUserService) {
		this.appUserService = appUserService;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
