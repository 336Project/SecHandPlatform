package com.sechand.platform.webservice.service;


import java.util.List;

import com.sechand.platform.base.BaseService;
import com.sechand.platform.model.User;

public interface AN_UserService extends BaseService {
	public User login(String username,String password);
	public boolean updateUser(String id,String email,String realName,String tel,String introduction);
	public boolean updateUserFromAdministrator(String id,String email,String realName,String tel,String nickName);
	public List<User> listCompany();
	public List<User> listServiceman(String parentId);
	public List<User> listPageRowsUsersByKeyword(int currentPage,String keyword);
	public int countByKeyword(String keyword);
	public void deleteByIds(String[] ids);
	public String add(User user);
	public List<User> listUsers();
}
