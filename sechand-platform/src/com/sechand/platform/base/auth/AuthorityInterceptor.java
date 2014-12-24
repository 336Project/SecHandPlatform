package com.sechand.platform.base.auth;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.sechand.platform.model.Account;
import com.sechand.platform.utils.WebUtil;
/**
 * 
 * @author Helen
 *	登录验证拦截器,拦截所有未登录的非法Action操作 
 */
public class AuthorityInterceptor extends AbstractInterceptor{
	private static final long serialVersionUID = -2905756241500468595L;
	
	private String excludeName;//排除(不拦截)的方法名
	private List<String>  list;
	
	@Override
	public void init() {
		 String[]  methods = excludeName.split(",");
	     list = new  ArrayList<String>();
	     for(String method : methods){
	        list.add(method.trim());
	     }
	}
	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		String methodName = arg0.getProxy().getMethod();
		if(!list.contains(methodName)){
			Account account=(Account) WebUtil.getSession(WebUtil.KEY_LOGIN_USER_SESSION);
			if(account==null){
				return Action.ERROR;
			}
		}
		return arg0.invoke();
	}
	public String getExcludeName() {
		return excludeName;
	}
	public void setExcludeName(String excludeName) {
		this.excludeName = excludeName;
	}
	public List<String> getList() {
		return list;
	}
	public void setList(List<String> list) {
		this.list = list;
	}

}
