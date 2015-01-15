package com.sechand.platform.webservice.serviceimpl;


import com.sechand.platform.base.BaseServiceImpl;
import com.sechand.platform.model.User;
import com.sechand.platform.utils.SysUtils;
import com.sechand.platform.webservice.service.AN_AccountService;

public class AN_AccountServiceImpl extends BaseServiceImpl implements AN_AccountService{

	@Override
	public User login(String username, String password) {
		String hql="from Account where (userName='"+username+"' or email='"+username+"') and password ='"+SysUtils.encrypt(password)+"'";
		User account=baseDao.getByHQL(hql);
		if(account!=null){
			return account;
		}
		return null;
	}

}
