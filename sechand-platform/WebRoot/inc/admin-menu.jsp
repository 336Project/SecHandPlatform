<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<body>
	<li class="nav-header">系统信息</li>
	<li><a href="<%=path%>/index.jsp"><i class="icon-picture"></i>账户信息</a></li>
	<li><a href="<%=path%>/admin/userList.jsp"><i class="icon-picture"></i>用户管理</a></li>
	<li><a href="<%=path%>/admin/roleList.jsp"><i class="icon-picture"></i>角色管理</a></li>
</body>
</html>
