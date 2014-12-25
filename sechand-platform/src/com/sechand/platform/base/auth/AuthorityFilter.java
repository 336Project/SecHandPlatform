package com.sechand.platform.base.auth;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

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
	private List<String> urlList;
	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest request= (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		String basePath=request.getContextPath();
		//获取请求的路径
		String requestURI=request.getRequestURI();
		String uri=requestURI.replace(basePath, "");
		if(uri!=null&&!uri.equals("/")){
			if(urlList!=null&&urlList.size()>0){
				if(!urlList.contains(uri)){
					Account account=(Account)request.getSession().getAttribute(WebUtil.KEY_LOGIN_USER_SESSION);
					if(account==null){
						response.sendRedirect(basePath+"/error.jsp");
					}
				}
			}
		}
		arg2.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		//从配置文件获取不需要拦截的路径
		String excludeJsp=SysUtils.readFromProperties("excludeJsp", "/filter.properties");
		if(excludeJsp!=null){
			//拆分
			String[] jsp_urls=excludeJsp.split(",");
			urlList=Arrays.asList(jsp_urls);
		}
	}


}
