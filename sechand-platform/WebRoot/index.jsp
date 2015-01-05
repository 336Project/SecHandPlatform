<%@page import="com.sechand.platform.model.Role"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
session.setAttribute("admin", Role.TYPE_ADMIN);
session.setAttribute("company", Role.TYPE_COMPANY);
session.setAttribute("user", Role.TYPE_USER);
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=Edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>后台管理系统</title>
<!-- bootstrap -->
  <link href="css/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
<!--  -->  <link href="css/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet">

<!-- common css --> 
  <link rel="stylesheet" type="text/css" href="css/style.css">
  <link rel="stylesheet" type="text/css" href="css/archon.css">
  <link rel="stylesheet" type="text/css" href="css/jquery.dataTables.min.css">
  <link rel="stylesheet" type="text/css" href="css/jquery.dataTables_themeroller.css">
<!-- self css -->
  <link rel="stylesheet" type="text/css" href="css/pages/login.css">
<!-- html5 兼容 -->
  <!--[if lt IE 9]>
      <script src="js/html5shiv.min.js"></script>
    <![endif]-->
<!-- common js -->
  <script src="js/jquery-1.11.1.min.js"></script>
  <script src="css/bootstrap/js/bootstrap.min.js"></script>
  <script src="js/common.js"></script>
  <script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
</head>
<body>
	<div class="frame">
		<!-- 菜单 start -->
		<div class="sidebar">
			<div class="wrapper">
				<!-- Replace the src of the image with your logo -->
				<a href="index.jsp" class="logo"><img src="images/logo.png" alt="后台管理" /></a>
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
					<s:if test='#session.account.roleType==#session.admin'>
						<jsp:include page="inc/admin-menu.jsp"></jsp:include>
					</s:if>
					<!-- 维修公司 -->
					<s:elseif test='#session.account.roleType==#session.company'>
						<jsp:include page="inc/company-menu.jsp"></jsp:include>
					</s:elseif>
					<!-- 普通用户 -->
					<s:elseif test='#session.account.roleType==#session.user'>
						<jsp:include page="inc/user-menu.jsp"></jsp:include>
					</s:elseif>
				</ul>
			</div>
		</div>
		<!-- 菜单 end -->

		<!-- 主要内容 start-->
		<div class="content">
			<div class="navbar">
				<a href="" class="btn pull-left toggle-sidebar"><i class="icon-list"></i></a>
				<a class="navbar-brand" href="index.html">Archon</a>

				<!-- 右上角 user menu -->
				<ul class="nav navbar-nav user-menu pull-right">
					<li class="dropdown user-name">
						<a class="dropdown-toggle" data-toggle="dropdown">
						<!-- 用户名称 -->
						<img src="images/theme/avatarSeven.png" class="user-avatar" alt="" />${sessionScope.account.nickName}</a>
							<ul class="dropdown-menu right inbox user">
								<li class="user-avatar">
								<!-- 角色名称 -->
									<img src="images/theme/avatarSeven.png" class="user-avatar" alt="" />
									${sessionScope.account.userName}
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
							<li><a href="<%=path%>/platform/accountAction!logout.action">注销</a></li>
						</ul>
					</li><!-- / dropdown -->				
				</ul><!-- / Top right user menu 点击右上角的admin展开-->

			</div><!-- / Navbar-->

			<div id="main-content">
				<!--面包屑导航-->
				<div class="row">
					<div class="col-mod-12">
						<ul class="breadcrumb">
							<li><a href="index.html">面包屑1</a></li>
							<li><a href="index.html">面包屑2</a></li>
							<li class="active">面包屑3</li>
						</ul>
					</div>
				</div>
				<!-- 面板 -->
				<div class="row">
					<div class="col-md-12">
						<div class="panel panel-archon main-graph">
							<div class="panel-heading">
								<h3 class="panel-title">内容
									<span class="pull-right">
										<a href="#" class="panel-minimize"><i class="glyphicon glyphicon-chevron-up"></i></a>
									</span>
								</h3>
							</div>
							<div class="panel-body" style="overflow: hidden; display: block;">
								<!-- 放置表格或其他内容 -->
								<table id="table-example" class="hover order-column">
									<!-- <thead>
										<tr>
											<th>ID</th>
											<th>账号</th>
											<th>真实姓名</th>
											<th>昵称</th>
											<th>邮箱</th>
											<th>手机号码</th>
										</tr>
									</thead> -->
								</table>
							</div>
						</div>
					</div>
				</div>


			</div>

		</div>
		<!-- 主要内容 end'-->

		<div class="row footer">
			<div class="col-md-12 text-center">
				© 2015 <a href="#">版权申明</a>
			</div>
		</div>
	</div>

	<script src="js/archon.js"></script>
	<script type="text/javascript" src="js/pages/index.js"></script>
</body>
</html>
