package com.sechand.platform.action;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;


import com.sechand.platform.base.BaseAction;
import com.sechand.platform.base.BaseUtil;
import com.sechand.platform.model.Role;
import com.sechand.platform.model.User;
import com.sechand.platform.service.UserService;
import com.sechand.platform.utils.DataTableParams;
import com.sechand.platform.utils.SysUtils;



public class UserAction extends BaseAction{
	private static final long serialVersionUID = -2585533016964889691L;
	private UserService userService;
	private User user;
	private String username;//用户名
	private String password;//密码
	private String type;//角色类型
	private String dataTableParams;//表单参数,json格式
	private String ids;
	

	/**
	 * 
	 * @Author:Helen  
	 * 2014-12-24下午8:48:14
	 * @return
	 * String
	 * @TODO 登录
	 */
	public String login(){
		User a=userService.login(username, password,type);
		if(a!=null){
			if(!User.STATUS_NORMAL.equals(a.getStatus())){
				json.setSuccess(false);
				json.setMsg("该账户被禁用，请联系管理员!");
			}else{
				//将用户添加到session
				BaseUtil.add2Session(BaseUtil.KEY_LOGIN_USER_SESSION, a);
				//更新最后一次登录时间
				userService.updateColumnById(User.class, "lastLoginTime", SysUtils.getDateFormat(new Date()), a.getId());
				json.setSuccess(true);
				json.setMsg("/index.jsp");
			}
		}else {
			json.setSuccess(false);
			json.setMsg("用户名或密码错误!");
		}
		return SUCCESS;
	}
	/**
	 * 
	 * @Author:Helen  
	 * 2014-12-24下午8:48:30
	 * @return
	 * String
	 * @TODO 退出
	 */
	public String logout(){
		//将用户信息从session中移除
		BaseUtil.remove4Session(BaseUtil.KEY_LOGIN_USER_SESSION);
		return LOGIN;
	}
	/**
	 * 
	 * @Author:Helen  
	 * 2014-12-24下午8:48:39
	 * @return
	 * String
	 * @TODO 自行注册
	 */
	public String register(){
		//注册完跳到登录页
		user.setSource(User.SOURCE_PLATFORM);
		String msg=userService.add(user);
		if(StringUtils.isNotBlank(msg)){
			json.setMsg(msg);
			json.setSuccess(true);
		}else{
			json.setMsg("注册失败!");
			json.setSuccess(false);
		}
		return SUCCESS;
	}
	/**
	 * 
	 * 2015-1-8 下午5:59:41
	 * @return 
	 * TODO 后台管理员手动添加
	 */
	public String addByManual(){
		user.setSource(User.SOURCE_MANUAL);
		String msg=userService.add(user);
		if(StringUtils.isNotBlank(msg)){
			json.setMsg(msg);
			json.setSuccess(true);
		}else{
			json.setMsg("注册失败!");
			json.setSuccess(false);
		}
		return SUCCESS;
	}
	/**
	 * 
	 * 2015-1-8 下午4:42:38
	 * @return 
	 * TODO 获取用户信息列表
	 */
	public String listUsersByParams(){
		DataTableParams params=DataTableParams.getInstance();
		params.parse(dataTableParams);
		Map<String, Object> dataMap=new HashMap<String, Object>();
		List<User> users=userService.listPageRowsUsersByKeyword(params.current_page, params.page_size, params.keyword);
		int count=userService.countByKeyword(params.keyword);
		dataMap.put("recordsTotal", count);
		dataMap.put("recordsFiltered", count);
		dataMap.put("draw",params.draw);
		dataMap.put("data", users);
		json.setMsg(dataMap);
		json.setSuccess(true);
		return SUCCESS;
	}
	/**
	 * 
	 * 2015-1-8 下午4:42:38
	 * @return 
	 * TODO 获取维修人员信息列表
	 */
	public String listRepairByParams(){
		DataTableParams params=DataTableParams.getInstance();
		params.parse(dataTableParams);
		String keyword=params.keyword;
		Map<String, Object> whereParams=new HashMap<String, Object>();
		whereParams.put("roleCode", Role.CODE_REPAIR);
		whereParams.put("parentId", ((User)BaseUtil.getSession(BaseUtil.KEY_LOGIN_USER_SESSION)).getId());
		if(!StringUtils.isEmpty(keyword)){
			whereParams.put("or_userName_like", keyword);
			whereParams.put("or_realName_like", keyword);
			whereParams.put("or_email_like", keyword);
			whereParams.put("or_tel_like", keyword);
			whereParams.put("or_status_like",keyword);
		}
		Map<String, Object> dataMap=new HashMap<String, Object>();
		List<User> users=userService.listPageRowsByClassNameAndParams(User.class, whereParams, params.current_page, params.page_size);//userService.listPageRowsUsersByKeyword(params.current_page, params.page_size, params.keyword);
		int count=userService.countByClassNameAndParams(User.class,whereParams);
		dataMap.put("recordsTotal", count);
		dataMap.put("recordsFiltered", count);
		dataMap.put("draw",params.draw);
		dataMap.put("data", users);
		json.setMsg(dataMap);
		json.setSuccess(true);
		return SUCCESS;
	}
	/**
	 * 
	 * 2015-1-8 下午4:47:48
	 * @return 
	 * TODO 批量删除用户
	 */
	public String deleteUserByIds(){
		if(!StringUtils.isBlank(ids)){
			String[] idList=ids.split(",");
			userService.deleteByIds(idList);
			json.setMsg("删除成功!");
			json.setSuccess(true);
		}else{
			json.setMsg("删除失败!");
			json.setSuccess(false);
		}
		return SUCCESS;
	}
	/**
	 * 
	 * 2015-1-9 下午2:48:14
	 * @return 
	 * TODO 重置用户密码(123456)
	 */
	public String resetPassword(){
		userService.updateColumnById(User.class, "password", SysUtils.encrypt("123456"), ids);
		json.setMsg("重置成功！密码为:123456");
		json.setSuccess(true);
		return SUCCESS;
	}
	/**
	 * 
	 * 2015-1-13 上午11:14:03
	 * @return 
	 * TODO 修改用户信息
	 */
	public String updateUser(){
		if(userService.updateUser(user)){
			json.setMsg("修改成功!");
			json.setSuccess(true);
		}else{
			json.setMsg("修改失败!");
			json.setSuccess(false);
		}
		return SUCCESS;
	}
	/**
	 * 
	 * 2015-1-14 上午11:13:28
	 * @return 
	 * TODO 修改用户并更新session
	 */
	public String updateUserAndSession(){
		if(userService.updateUser(user)){
			User a=userService.getByClassAndId(User.class, user.getId());
			BaseUtil.add2Session(BaseUtil.KEY_LOGIN_USER_SESSION, a);
			json.setMsg("修改成功!");
			json.setSuccess(true);
		}else{
			json.setMsg("修改失败!");
			json.setSuccess(false);
		}
		return SUCCESS;
	}
	/**
	 * 
	 * 2015-1-13 上午11:52:17
	 * @return 
	 * TODO 修改用户状态：启用or禁用
	 */
	public String updateStatus(){
		String status=user.getStatus();
		if(user!=null&&user.getId()!=null&&(User.STATUS_DISABLE.equals(status)||User.STATUS_NORMAL.equals(status))){
			userService.updateColumnById(User.class, "status", status, user.getId());
			json.setMsg("修改状态成功!");
			json.setSuccess(true);
		}else{
			json.setMsg("修改状态失败!");
			json.setSuccess(false);
		}
		return SUCCESS;
	}
	/**
	 * 
	 * 2015-1-15 下午5:03:09
	 * @return 
	 * TODO 获取所有用户
	 */
	public String listAllUsers(){
		List<User> users=userService.listUsers();
		if(users!=null){
			json.setMsg(users);
			json.setSuccess(true);
		}else{
			json.setSuccess(false);
		}
		return SUCCESS;
	}
	/**
	 * 
	 * 2015-1-16 下午3:27:31
	 * @return 
	 * TODO 获取所有公司用户
	 */
	public String listCompany(){
		Map<String, Object> whereParams=new HashMap<String, Object>();
		whereParams.put("roleCode", Role.CODE_COMPANY);
		List<User> users=userService.listByClassNameAndParams(User.class, whereParams);
		json.setMsg(users);
		json.setSuccess(true);
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

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDataTableParams() {
		return dataTableParams;
	}
	public void setDataTableParams(String dataTableParams) {
		this.dataTableParams = dataTableParams;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
