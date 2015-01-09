package com.sechand.platform.service;

import java.util.List;

import com.sechand.platform.base.BaseService;
import com.sechand.platform.model.Role;


public interface RoleService extends BaseService{
	/*
	 * 不包括管理员（其他角色）
	 */
	public static final int TYPE_NOT_INCLUDE=1;
	/*
	 * 包括管理员
	 */
	public static final int TYPE_INCLUDE=1;
	public List<Role> listByType(int type);
	
	/**
	 * 
	 * 2015-1-6 下午12:43:16
	 * @param currentPage
	 * @param pageSize
	 * @param keyword
	 * @return 
	 * TODO 根据搜索关键字分页获取数据
	 */
	public List<Role> listPageRowsRolesByKeyword(int currentPage,int pageSize,String keyword);
	/**
	 * 
	 * 2015-1-6 下午12:54:15
	 * @param keyword
	 * @return 
	 * TODO 根据搜索关键字统计记录条数
	 */
	public int countByKeyword(String keyword);
	/**
	 * 
	 * 2015-1-8 下午4:50:25
	 * @param ids 
	 * TODO 批量删除
	 */
	public void deleteByIds(String[] ids);
	/**
	 * 
	 * 2015-1-9 下午1:14:28
	 * @param code
	 * @return 
	 * TODO 根据角色编号获取角色
	 */
	public Role getRoleByCode(String code); 
}
