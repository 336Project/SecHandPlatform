package com.sechand.platform.serviceimpl;


import com.sechand.platform.base.BaseServiceImpl;
import com.sechand.platform.model.Account;
import com.sechand.platform.model.Role;
import com.sechand.platform.service.AccountService;
import com.sechand.platform.utils.SysUtils;
import com.sechand.platform.utils.WebUtil;


public class AccountServiceImpl extends BaseServiceImpl implements AccountService{

	@Override
	public boolean login(String username, String password,String roleName) {
		String hql="from Account where (userName='"+username+"' or email='"+username+"') and password ='"+SysUtils.encrypt(password)+"' and roleName ='"+roleName+"'";
		Account account=baseDao.getByHQL(hql);
		if(account!=null){
			WebUtil.add2Session(WebUtil.KEY_LOGIN_USER_SESSION, account);
			return true;
		}
		return false;
	}

	@Override
	public long add(Account account) {
		Role role=baseDao.getByClassAndId(Role.class, account.getRoleId());
		if(role==null){
			return -1;
		}
		account.setRoleId(role.getId());
		account.setRoleName(role.getName());
		account.setPassword(SysUtils.encrypt(account.getPassword()));
		return baseDao.save(account);
	}

}
