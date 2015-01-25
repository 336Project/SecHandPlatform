package com.sechand.platform.webservice.serviceimpl;

import java.util.HashMap;
import java.util.Map;

import com.sechand.platform.base.BaseServiceImpl;
import com.sechand.platform.model.User;
import com.sechand.platform.utils.SysUtils;
import com.sechand.platform.webservice.service.AN_UserService;

public class AN_UserServiceImpl extends BaseServiceImpl implements AN_UserService{

	@Override
	public User login(String username, String password) {
		Map<String, Object> whereParams=new HashMap<String, Object>();
		whereParams.put("userName", username);
		whereParams.put("password", SysUtils.encrypt(password));
		return baseDao.getByClassNameAndParams(User.class, whereParams);
	}

}
