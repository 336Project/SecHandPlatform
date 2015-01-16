<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="navbar">
	<a href="" class="btn pull-left toggle-sidebar"><i class="icon-list"></i></a>
	<a class="navbar-brand" href="index.jsp">后台管理</a>

	<!-- 右上角 user menu -->
	<ul class="nav navbar-nav user-menu pull-right">
		<li class="dropdown user-name">
			<a class="dropdown-toggle" data-toggle="dropdown">
			<!-- 用户名称 -->
			<img src="<%=basePath%>/images/theme/avatarSeven.png" class="user-avatar" alt="" />${sessionScope.user.nickName}</a>
				<ul class="dropdown-menu right inbox user">
					<li class="user-avatar">
					<!-- 角色名称 -->
						<img src="<%=basePath%>/images/theme/avatarSeven.png" class="user-avatar" alt="" />
						${sessionScope.user.userName}
					</li>
				<li>
					<i class="icon-user avatar"></i>
					<div class="message">
						<span class="username">个人信息</span> 
					</div>
				</li>
				<li>
					<i class="icon-cogs avatar"></i>
					<div class="message">
						<span class="username">系统设置 </span> 
					</div>
				</li>
				<!-- <li>
					<i class="icon-book avatar"></i>
					<div class="message">
						<span class="username">帮助手册 </span> 
					</div>
				</li> -->
				<li><a href="<%=path%>/platform/userAction!logout.action">注销</a></li>
			</ul>
		</li><!-- / dropdown -->				
	</ul><!-- / Top right user menu 点击右上角的admin展开-->

</div><!-- / Navbar-->