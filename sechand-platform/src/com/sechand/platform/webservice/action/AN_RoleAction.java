package com.sechand.platform.webservice.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.sechand.platform.base.BaseAction;
import com.sechand.platform.model.Role;
import com.sechand.platform.service.RoleService;
import com.sechand.platform.utils.DataTableParams;
import com.sechand.platform.webservice.service.AN_RoleService;

public class AN_RoleAction extends BaseAction {
	private AN_RoleService appRoleService;
	private int type;
	private List<Role> roles;
	private Role role;
	private String dataTableParams;
	private String ids;
	/**
	 * 
	 * 2014-12-24 下午5:01:11
	 * @return 
	 * TODO 获取角色下拉列表数据
	 */
	public String listRole(){
		roles=appRoleService.listByType(type);
		json.setMsg(roles);
		json.setSuccess(true);
		return SUCCESS;
	}
	/**
	 * 
	 * 2015-1-9 上午11:31:52
	 * @return 
	 * TODO 获取角色表格数据
	 */
	public String listRoleByParams(){
		DataTableParams params=DataTableParams.getInstance();
		params.parse(dataTableParams);
		Map<String, Object> dataMap=new HashMap<String, Object>();
		List<Role> roleList=appRoleService.listPageRowsRolesByKeyword(params.current_page, params.page_size, params.keyword);//accountService.listUsers();
		int count=appRoleService.countByKeyword(params.keyword);
		dataMap.put("recordsTotal", count);
		dataMap.put("recordsFiltered", count);
		dataMap.put("draw",params.draw);
		dataMap.put("data", roleList);
		json.setMsg(dataMap);
		json.setSuccess(true);
		return SUCCESS;
	}
	/**
	 * 
	 * 2015-1-8 下午4:47:48
	 * @return 
	 * TODO 批量删除角色
	 */
	public String deleteRoleByIds(){
		if(!StringUtils.isBlank(ids)){
			String[] idList=ids.split(",");
			appRoleService.deleteByIds(idList);
			json.setMsg("删除成功!");
			json.setSuccess(true);
		}else{
			json.setMsg("删除失败!");
			json.setSuccess(false);
		}
		return SUCCESS;
	}
	/**
	 * 2015-1-9 下午1:17:06
	 * @return 
	 * TODO 添加角色
	 */
	public String addRole(){
		String msg=appRoleService.add(role);
		if(StringUtils.isNotBlank(msg)){
			json.setMsg(msg);
			json.setSuccess(true);
		}else{
			json.setMsg("添加失败!");
			json.setSuccess(false);
		}
		return SUCCESS;
	}
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public AN_RoleService getAppRoleService() {
		return appRoleService;
	}
	public void setAppRoleService(AN_RoleService appRoleService) {
		this.appRoleService = appRoleService;
	}

	public String getDataTableParams() {
		return dataTableParams;
	}


	public void setDataTableParams(String dataTableParams) {
		this.dataTableParams = dataTableParams;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
}
