package com.sechand.platform.base;

import java.io.Serializable;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport{
	private static final long serialVersionUID = 2817071984746760965L;
	protected BaseService baseService;
	protected JsonResult json=new JsonResult();
	public JsonResult getJson() {
		return json;
	}
	public void setJson(JsonResult json) {
		this.json = json;
	}
	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}
	
	protected class JsonResult implements Serializable{
		private static final long serialVersionUID = -8483874375183014284L;
		private String msg="";
		private boolean isSuccess=false;
		public String getMsg() {
			return msg;
		}
		public void setMsg(String msg) {
			this.msg = msg;
		}
		public boolean isSuccess() {
			return isSuccess;
		}
		public void setSuccess(boolean isSuccess) {
			this.isSuccess = isSuccess;
		}
	}
}
