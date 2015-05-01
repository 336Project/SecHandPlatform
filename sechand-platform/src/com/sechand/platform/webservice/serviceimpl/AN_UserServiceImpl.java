package com.sechand.platform.webservice.serviceimpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.sechand.platform.base.BaseServiceImpl;
import com.sechand.platform.model.Role;
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
	
	@Override
	public String add(User user) {
		if(user!=null){
			Map<String, Object> whereParams=new HashMap<String, Object>();
			whereParams.put("code", user.getRoleCode());
			Role role=baseDao.getByClassNameAndParams(Role.class, whereParams);
			if(role==null){
				return "注册失败,该角色不存在!";
			}
			whereParams.clear();
			whereParams.put("userName", user.getUserName());
			User u=baseDao.getByClassNameAndParams(User.class, whereParams);
			if(u!=null){
				return "该用户名已存在,请重新注册!";
			}
			whereParams.clear();
			whereParams.put("nickName", user.getNickName());
			u=baseDao.getByClassNameAndParams(User.class, whereParams);
			if(u!=null){
				return "该昵称太抢手啦,已经被人注册过了，请重新填写!";
			}
			u=new User();
			u.setBalance("0");
			u.setEmail(user.getEmail());
			u.setIntroduction(user.getIntroduction());
			u.setNickName(user.getNickName());
			u.setPassword(SysUtils.encrypt(user.getPassword()));
			u.setRealName(user.getRealName());
			u.setRegisterTime(SysUtils.getDateFormat(new Date()));
			u.setRoleCode(role.getCode());
			u.setRoleName(role.getName());
			u.setSource(user.getSource());
			u.setStatus(User.STATUS_NORMAL);
			u.setTel(user.getTel());
			u.setUserName(user.getUserName());
			u.setParentId(user.getParentId());
			int id=baseDao.save(u);
			if(id>0){
				return "恭喜,注册成功!";
			}else{
				return "不好意思，服务器异常,请稍后再试!";
			}
		}
		return null;
	}
	
	@Override
	public boolean updateUser(String id,String email,String realName,String tel,String introduction) {
		try {
			Map<String, Object> parmas=new HashMap<String, Object>();
			parmas.put("email", email);
			parmas.put("realName", realName);
			parmas.put("tel", tel);
			parmas.put("introduction", introduction);
			
			baseDao.updateColumnsByParmas(User.class, id, parmas);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public boolean updateUserFromAdministrator(String id,String email,String realName,String tel,String nickName) {
		try {
			Map<String, Object> parmas=new HashMap<String, Object>();
			parmas.put("email", email);
			parmas.put("realName", realName);
			parmas.put("tel", tel);
			parmas.put("nickName", nickName);
			baseDao.updateColumnsByParmas(User.class, id, parmas);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public List<User> listCompany() {
		Map<String, Object> whereParams=new HashMap<String, Object>();
		whereParams.put("roleCode", Role.CODE_COMPANY);
		List<User> users=baseDao.listByClassNameAndParams(User.class, whereParams);
		return users;
	}
	@Override
	public List<User> listServiceman(String parentId) {
		Map<String, Object> whereParams=new HashMap<String, Object>();
		whereParams.put("roleCode", Role.CODE_REPAIR);
		whereParams.put("parentId", parentId);
		List<User> users=baseDao.listByClassNameAndParams(User.class, whereParams);
		return users;
	}
	@Override
	public List<User> listUsers() {
		return baseDao.listByClassName(User.class);
	}
	@Override
	public List<User> listPageRowsUsersByKeyword(int currentPage,
			 String keyword) {
		Map<String, Object> whereParams=new HashMap<String, Object>();
		if(!StringUtils.isEmpty(keyword)){
			whereParams.put("or_userName_like", keyword);
			whereParams.put("or_realName_like", keyword);
			whereParams.put("or_nickName_like", keyword);
			whereParams.put("or_email_like", keyword);
			whereParams.put("or_tel_like", keyword);
			whereParams.put("or_status_like",keyword);
			whereParams.put("or_balance_like",keyword);
		}
		return baseDao.listPageRowsByClassNameAndParams(User.class, whereParams, currentPage, 15);
	}

	@Override
	public int countByKeyword(String keyword) {
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
}
