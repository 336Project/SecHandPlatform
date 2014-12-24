package com.sechand.platform.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sechand.platform.base.BaseServiceImpl;
import com.sechand.platform.model.Role;
import com.sechand.platform.service.RoleService;

public class RoleServiceImpl extends BaseServiceImpl implements RoleService {

	@Override
	public List<Role> listByType(int type) {
		Map<String, Object> whereParams=new HashMap<String, Object>();
		if(type==RoleService.TYPE_INCLUDE){//包括管理员,即所有角色
		}else{
			whereParams.put("type_<>", Role.TYPE_ADMIN);
		}
		return baseDao.listByClassNameAndParams("Role", whereParams);
	}

}
