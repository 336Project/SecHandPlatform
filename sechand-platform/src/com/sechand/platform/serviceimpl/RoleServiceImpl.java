package com.sechand.platform.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.sechand.platform.base.BaseServiceImpl;
import com.sechand.platform.model.Role;
import com.sechand.platform.service.RoleService;

public class RoleServiceImpl extends BaseServiceImpl implements RoleService {

	@Override
	public List<Role> listByType(int type) {
		if(type==RoleService.TYPE_INCLUDE){//包括管理员,即所有角色
			return baseDao.listByClassName(Role.class);
		}else{
			String hql="from Role where code <> '"+Role.CODE_ADMIN+"'";
			return baseDao.listByHQL(hql);
		}
	}

	@Override
	public List<Role> listPageRowsRolesByKeyword(int currentPage, int pageSize,
			String keyword) {
		String hql="from Role where 1=1";
		if(!StringUtils.isEmpty(keyword)){
			hql+=" and (name like '%"+keyword+"%' or code like '%"+keyword+"%')";
		}
		return baseDao.listPageRowsByHQL(hql, currentPage, pageSize);
	}

	@Override
	public int countByKeyword(String keyword) {
		String hql="from Role where 1=1";
		if(!StringUtils.isEmpty(keyword)){
			hql+=" and (name like '%"+keyword+"%' or code like '%"+keyword+"%')";
		}
		return baseDao.countByHQL(hql);
	}

	@Override
	public void deleteByIds(String[] ids) {
		baseDao.deleteByClassNameAndIds(Role.class, ids);
	}

	@Override
	public Role getRoleByCode(String code) {
		Map<String, Object> whereParams=new HashMap<String, Object>();
		whereParams.put("code", code);
		return baseDao.getByClassNameAndParams(Role.class, whereParams);
	}

	@Override
	public String add(Role role) {
		if(role!=null){
			String code=role.getCode();
			if(Role.CODE_ADMIN.equals(code)||Role.CODE_COMPANY.equals(code)||Role.CODE_CUSTOMER.equals(code)){
				Role r=getRoleByCode(code);
				if(r!=null){
					return "该角色已经存在，请勿重复添加!";
				}else{
					Role re=new Role();
					re.setCode(role.getCode());
					re.setName(role.getName());
					int i=baseDao.save(re);
					if(i>0){
						return "添加成功!";
					}
				}
			}else{
				return "角色编号只能为“1：(管理员)，2：(维修公司)，3(普通用户)”中的一种!";
			}
		}
		return null;
	}

}
