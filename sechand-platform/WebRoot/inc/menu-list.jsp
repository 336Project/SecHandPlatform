<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.sechand.platform.model.Role"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
session.setAttribute("admin", Role.CODE_ADMIN);
session.setAttribute("company", Role.CODE_COMPANY);
session.setAttribute("user", Role.CODE_USER);
%>
<ul class="nav nav-list">
	<!-- sidebar input search box -->
	<li class="nav-search">
		<div class="form-group">
			<input type="text" class="form-control nav-search" placeholder="Search through site" />
			<span class="input-icon fui-search"></span>
		</div>
	</li>

	<!-- Sidebar header @add class nav-header for sidebar header -->
	<!-- 根据不同的角色加载不同的左边菜单栏 -->
	<!-- 管理员 -->
	<s:if test='#session.account.roleCode==#session.admin'>
		<jsp:include page="admin-menu.jsp"></jsp:include>
	</s:if>
	<!-- 维修公司 -->
	<s:elseif test='#session.account.roleCode==#session.company'>
		<jsp:include page="company-menu.jsp"></jsp:include>
	</s:elseif>
	<!-- 普通用户 -->
	<s:elseif test='#session.account.roleCode==#session.user'>
		<jsp:include page="customer-menu.jsp"></jsp:include>
	</s:elseif>
</ul>
