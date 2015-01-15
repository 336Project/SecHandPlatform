package com.sechand.platform.serviceimpl;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.sechand.platform.base.BaseServiceImpl;
import com.sechand.platform.model.User;
import com.sechand.platform.model.Role;
import com.sechand.platform.service.UserService;
import com.sechand.platform.utils.SysUtils;


public class UserServiceImpl extends BaseServiceImpl implements UserService{

	@Override
	public User login(String username, String password,String roleType) {
		Map<String, Object> whereParams=new HashMap<String, Object>();
		whereParams.put("userName", username);
		whereParams.put("password", SysUtils.encrypt(password));
		whereParams.put("roleCode", roleType);
		return baseDao.getByClassNameAndParams(User.class, whereParams);
	}

	@Override
	public long add(User user) {
		Map<String, Object> whereParams=new HashMap<String, Object>();
		whereParams.put("code", user.getRoleCode());
		Role role=baseDao.getByClassNameAndParams(Role.class, whereParams);
		if(role==null){
			return -1;
		}
		user.setRegisterTime(SysUtils.getDateFormat(new Date()));
		user.setRoleCode(role.getCode());
		user.setRoleName(role.getName());
		user.setPassword(SysUtils.encrypt(user.getPassword()));
		user.setStatus(User.STATUS_NORMAL);
		return baseDao.save(user);
	}

	@Override
	public List<User> listUsers() {
		return baseDao.listByClassName(User.class);
	}

	@Override
	public List<User> listPageRowsUsersByKeyword(int currentPage,
			int pageSize, String keyword) {
		/*String hql="from Account where 1=1";
		if(!StringUtils.isEmpty(keyword)){
			hql+=" and (userName like '%"+keyword+"%' or realName like '%"+keyword+"%' or nickName like '%"+keyword+"%' or email like '%"+keyword+"%' or tel like '%"+keyword+"%')";
		}
		return baseDao.listPageRowsByHQL(hql, currentPage, pageSize);*/
		Map<String, Object> whereParams=new HashMap<String, Object>();
		if(!StringUtils.isEmpty(keyword)){
			whereParams.put("or_userName_like", keyword);
			whereParams.put("or_realName_like", keyword);
			whereParams.put("or_nickName_like", keyword);
			whereParams.put("or_email_like", keyword);
			whereParams.put("or_tel_like", keyword);
			whereParams.put("or_status_like",keyword);
		}
		return baseDao.listPageRowsByClassNameAndParams(User.class, whereParams, currentPage, pageSize);
	}

	@Override
	public int countByKeyword(String keyword) {
		/*String hql="from Account where 1=1";
		if(!StringUtils.isEmpty(keyword)){
			hql+=" and (userName like '%"+keyword+"%' or realName like '%"+keyword+"%' or nickName like '%"+keyword+"%' or email like '%"+keyword+"%' or tel like '%"+keyword+"%')";
		}
		return baseDao.countByHQL(hql);*/
		Map<String, Object> whereParams=new HashMap<String, Object>();
		if(!StringUtils.isEmpty(keyword)){
			whereParams.put("or_userName_like", keyword);
			whereParams.put("or_realName_like", keyword);
			whereParams.put("or_nickName_like", keyword);
			whereParams.put("or_email_like", keyword);
			whereParams.put("or_tel_like", keyword);
			whereParams.put("or_status_like", keyword);
		}
		return baseDao.countByClassNameAndParams(User.class, whereParams);
	}

	@Override
	public void deleteByIds(String[] ids) {
		baseDao.deleteByClassNameAndIds(User.class, ids);
	}

	@Override
	public boolean updateUser(User user) {
		try {
			Map<String, Object> parmas=new HashMap<String, Object>();
			parmas.put("email", user.getEmail());
			parmas.put("nickName", user.getNickName());
			parmas.put("realName", user.getRealName());
			parmas.put("tel", user.getTel());
			parmas.put("introduction", user.getIntroduction());
			baseDao.updateColumnsByParmas(User.class, user.getId(), parmas);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
