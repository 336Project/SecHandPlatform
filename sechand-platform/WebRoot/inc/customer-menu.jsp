<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
	<li class="nav-header">用户管理</li>
	<li><a href="<%=path%>/index.jsp"><i class="icon-picture"></i>用户信息</a></li>
	<li><a href="<%=path%>/index.jsp"><i class="icon-picture"></i>账户信息</a></li>
	<li class="nav-header">订单管理</li>
	<li><a href="<%=path%>/customer/orderCustomerList.jsp"><i class="icon-picture"></i>订单信息</a></li>
