package com.sechand.platform.webservice.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.sechand.platform.base.BaseAction;
import com.sechand.platform.model.Role;
import com.sechand.platform.model.User;
import com.sechand.platform.utils.DataTableParams;
import com.sechand.platform.utils.SysUtils;
import com.sechand.platform.webservice.service.AN_UserService;

public class AN_UserAction extends BaseAction {
	private AN_UserService appUserService;
	private String username;
	private String password;
	private String id;
	private String email;
	private String realName;
	private String userName;
	
	private String tel;
	private String introduction;
	private String nickName;
	private int currentPage;
	private String keyword;
	private String ids;
	private String roleCode;
	private User user;
	
	public String login(){
		user=appUserService.login(username, password);
		if(user==null){
			json.setMsg(user);
			json.setSuccess(false);
		}
		else{
			json.setMsg(user);
			json.setSuccess(true);
		}
		return SUCCESS;
	}
	// 注册
	public String register(){
		//注册完跳到登录页
		user=new User();
		user.setSource(User.SOURCE_PLATFORM);
		user.setIntroduction(introduction);
		user.setRoleCode(roleCode);
		user.setEmail(email);
		user.setNickName(nickName);
		user.setPassword(password);
		user.setRealName(realName);
		user.setTel(tel);
		user.setUserName(userName);
		
		String msg=appUserService.add(user);
		if(StringUtils.isNotBlank(msg)){
			json.setMsg(msg);
			json.setSuccess(true);
		}else{
			json.setMsg("注册失败!");
			json.setSuccess(false);
		}
		return SUCCESS;
	}
	// 用户更新个人信息
	public String updateUser(){
		if(appUserService.updateUser(id,email,realName,tel,introduction)){
			json.setMsg("修改成功!");
			json.setSuccess(true);
		}else{
			json.setMsg("修改失败!");
			json.setSuccess(false);
		}
		return SUCCESS;
	}
	// 用户更新个人信息
	public String updateUserFromAdministrator(){
		if(appUserService.updateUserFromAdministrator(id,email,realName,tel,nickName)){
			json.setMsg("修改成功!");
			json.setSuccess(true);
		}else{
			json.setMsg("修改失败!");
			json.setSuccess(false);
		}
		return SUCCESS;
	}
	
	public String listCompany(){
		List<User> users=appUserService.listCompany();
		json.setMsg(users);
		json.setSuccess(true);
		for (int i = 0; i < users.size(); i++) {
			User user2 = users.get(i);
			String nickName = user2.getNickName();
			System.out.println(nickName);
		}
		return SUCCESS;
	}
	
	 //获取所有用户
	public String listAllUsers(){
		List<User> users=appUserService.listUsers();
		if(users!=null){
			json.setMsg(users);
			json.setSuccess(true);
		}else{
			json.setSuccess(false);
		}
		return SUCCESS;
	}
	
	//获取用户信息列表
	public String listUsersByParams(){
		Map<String, Object> dataMap=new HashMap<String, Object>();
		List<User> users=appUserService.listPageRowsUsersByKeyword(currentPage,keyword);
		int count=appUserService.countByKeyword(keyword);
		
		dataMap.put("recordsTotal", count);
		dataMap.put("recordsFiltered", count);
		dataMap.put("data", users);
		json.setMsg(dataMap);
		json.setSuccess(true);
		return SUCCESS;
	}
	// 删除用户
	public String deleteUserByIds(){
		if(!StringUtils.isBlank(ids)){
			String[] idList=ids.split(",");
			appUserService.deleteByIds(idList);
			json.setMsg("删除成功!");
			json.setSuccess(true);
		}else{
			json.setMsg("删除失败!");
			json.setSuccess(false);
		}
		return SUCCESS;
	}
	// 重置密码
	public String resetPassword(){
		appUserService.updateColumnById(User.class, "password", SysUtils.encrypt("123456"), ids);
		json.setMsg("重置成功！密码为:123456");
		json.setSuccess(true);
		return SUCCESS;
	}
	// 管理员添加
	public String addByManual(){
		
		user=new User();
		user.setSource(User.SOURCE_MANUAL);
		user.setIntroduction("");
		user.setRoleCode(roleCode);
		user.setEmail(email);
		user.setNickName(nickName);
		user.setPassword(password);
		user.setRealName(realName);
		user.setTel(tel);
		user.setUserName(userName);
		
		
		String msg=appUserService.add(user);
		if(StringUtils.isNotBlank(msg)){
			json.setMsg(msg);
			json.setSuccess(true);
		}else{
			json.setMsg("注册失败!");
			json.setSuccess(false);
		}
		return SUCCESS;
	}
	
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
