package com.sechand.platform.base.auth;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sechand.platform.model.Account;
import com.sechand.platform.utils.SysUtils;
import com.sechand.platform.utils.WebUtil;


/** 
 * @author Helen
 * 登录验证拦截器,拦截所有未登录的非法jsp操作 (直接在地址栏请求jsp)
 */
public class AuthorityFilter implements Filter{

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest request= (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		String basePath=request.getContextPath();
		String uri=request.getRequestURI().replace(basePath, "");
		String excludeJsp=SysUtils.readFromProperties("excludeJsp", "/filter.properties");
		if(!excludeJsp.contains(uri)){
			Account account=(Account)request.getSession().getAttribute(WebUtil.KEY_LOGIN_USER_SESSION);
			if(account==null){
				response.sendRedirect(basePath+"/error.jsp");
			}
		}
		arg2.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}


}
