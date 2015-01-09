package com.sechand.platform.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.sechand.platform.base.BaseAction;
import com.sechand.platform.model.Role;
import com.sechand.platform.service.RoleService;
import com.sechand.platform.utils.DataTableParams;

public class RoleAction extends BaseAction {
	private RoleService roleService;
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
		roles=roleService.listByType(type);
		return "list";
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
		List<Role> roleList=roleService.listPageRowsRolesByKeyword(params.current_page, params.page_size, params.keyword);//accountService.listUsers();
		int count=roleService.countByKeyword(params.keyword);
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
			roleService.deleteByIds(idList);
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
		if(role!=null){
			String code=role.getCode();
			if(Role.CODE_ADMIN.equals(code)||Role.CODE_COMPANY.equals(code)||Role.CODE_USER.equals(code)){
				Role r=roleService.getRoleByCode(code);
				if(r!=null){
					json.setMsg("该角色已经存在，请勿重复添加!");
					json.setSuccess(false);
				}else{
					roleService.save(role);
					json.setMsg("添加成功!");
					json.setSuccess(true);
				}
			}else{
				json.setMsg("角色编号只能为“1：(管理员)，2：(维修公司)，3(普通用户)”中的一种");
				json.setSuccess(false);
			}
		}else{
			json.setMsg("添加失败!");
			json.setSuccess(false);
		}
		return SUCCESS;
	}
	
	public RoleService getRoleService() {
		return roleService;
	}
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
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
