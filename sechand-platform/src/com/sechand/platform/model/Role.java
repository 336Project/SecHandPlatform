package com.sechand.platform.model;

import java.io.Serializable;
/**
 * 角色表
 */
public class Role implements Serializable{
	/**
	 * 管理员类型
	 */
	public static final String TYPE_ADMIN="admin";//管理员
	public static final String TYPE_COMPANY="company";//维修公司
	public static final String TYPE_USER="user";//用户
	private static final long serialVersionUID = 7124806791366190520L;
	private Integer id;//主键
	private String name;//角色名称
	private String type;//角色类型
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
