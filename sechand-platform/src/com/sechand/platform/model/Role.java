package com.sechand.platform.model;

import java.io.Serializable;
/**
 * 角色表
 */
public class Role implements Serializable{
	/**
	 * 管理员类型
	 */
	public static final String CODE_ADMIN="1";//管理员
	public static final String CODE_COMPANY="2";//维修公司
	public static final String CODE_CUSTOMER="3";//用户
	public static final String CODE_REPAIR="4";//维修人员
	private static final long serialVersionUID = 7124806791366190520L;
	private Integer id;//主键
	private String name;//角色名称
	private String code;//角色编号
	
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}
