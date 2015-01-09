package com.sechand.platform.serviceimpl;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.sechand.platform.base.BaseServiceImpl;
import com.sechand.platform.model.Account;
import com.sechand.platform.model.Role;
import com.sechand.platform.service.AccountService;
import com.sechand.platform.utils.SysUtils;


public class AccountServiceImpl extends BaseServiceImpl implements AccountService{

	@Override
	public Account login(String username, String password,String roleType) {
		//String hql="from Account where (userName='"+username+"' or email='"+username+"') and password ='"+SysUtils.encrypt(password)+"' and roleType ='"+roleType+"'";//邮箱也可以登录
		String hql="from Account where userName='"+username+"' and password ='"+SysUtils.encrypt(password)+"' and roleCode ='"+roleType+"'";
		return baseDao.getByHQL(hql);
	}

	@Override
	public long add(Account account) {
		Map<String, Object> whereParams=new HashMap<String, Object>();
		whereParams.put("code", account.getRoleCode());
		Role role=baseDao.getByClassNameAndParams("Role", whereParams);
		if(role==null){
			return -1;
		}
		account.setRegisterTime(SysUtils.getDateFormat(new Date()));
		account.setRoleCode(role.getCode());
		account.setRoleName(role.getName());
		account.setPassword(SysUtils.encrypt(account.getPassword()));
		account.setStatus(Account.STATUS_NORMAL);
		return baseDao.save(account);
	}

	@Override
	public List<Account> listUsers() {
		return baseDao.listByClassName("Account");
	}

	@Override
	public List<Account> listPageRowsUsersByKeyword(int currentPage,
			int pageSize, String keyword) {
		String hql="from Account where 1=1";
		if(!StringUtils.isEmpty(keyword)){
			hql+=" and (userName like '%"+keyword+"%' or realName like '%"+keyword+"%' or nickName like '%"+keyword+"%' or email like '%"+keyword+"%' or tel like '%"+keyword+"%')";
		}
		return baseDao.listPageRowsByHQL(hql, currentPage, pageSize);
	}

	@Override
	public int countByKeyword(String keyword) {
		String hql="from Account where 1=1";
		if(!StringUtils.isEmpty(keyword)){
			hql+=" and (userName like '%"+keyword+"%' or realName like '%"+keyword+"%' or nickName like '%"+keyword+"%' or email like '%"+keyword+"%' or tel like '%"+keyword+"%')";
		}
		return baseDao.countByHQL(hql);
	}

	@Override
	public void deleteByIds(String[] ids) {
		baseDao.deleteByClassNameAndIds("Account", ids);
	}
}
