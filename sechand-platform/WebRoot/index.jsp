<%@page import="com.sechand.platform.model.Role"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
session.setAttribute("admin", Role.CODE_ADMIN);
session.setAttribute("company", Role.CODE_COMPANY);
session.setAttribute("user", Role.CODE_USER);
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
  <link rel="stylesheet" type="text/css" href="js/lib/datatables/css/jquery.dataTables.min.css">
  <link rel="stylesheet" type="text/css" href="js/lib/datatables/css/jquery.dataTables_themeroller.css">
<!-- self css -->
  <link rel="stylesheet" type="text/css" href="css/pages/index.css">
<!-- html5 兼容 -->
  <!--[if lt IE 9]>
      <script src="js/html5shiv.min.js"></script>
    <![endif]-->
<!-- common js -->
  <script src="js/jquery-1.11.1.min.js"></script>
  <script src="css/bootstrap/js/bootstrap.min.js"></script>
  <script src="js/common.js"></script>
  <script type="text/javascript" src="js/lib/datatables/js/jquery.dataTables.min.js"></script>
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
					<s:if test='#session.account.roleCode==#session.admin'>
						<jsp:include page="inc/admin-menu.jsp"></jsp:include>
					</s:if>
					<!-- 维修公司 -->
					<s:elseif test='#session.account.roleCode==#session.company'>
						<jsp:include page="inc/company-menu.jsp"></jsp:include>
					</s:elseif>
					<!-- 普通用户 -->
					<s:elseif test='#session.account.roleCode==#session.user'>
						<jsp:include page="inc/user-menu.jsp"></jsp:include>
					</s:elseif>
				</ul>
			</div>
		</div>
		<!-- 菜单 end -->

		<!-- 主要内容 start-->
		<div class="content">
			<jsp:include page="inc/top-nav.jsp"></jsp:include>
			<div id="main-content">
				<!--面包屑导航-->
				<div class="row">
					<div class="col-mod-12">
						<ul class="breadcrumb">
							<li><a href="index.jsp">系统管理</a></li>
							<li class="active">账户信息</li>
						</ul>
					</div>
				</div>
				<!-- 面板 -->
				<div class="row">
					<div class="col-md-12">
						<div class="panel panel-archon main-graph">
							<div class="panel-heading">
								<h3 class="panel-title">账户信息
									<span class="pull-right">
										<a href="#" class="panel-minimize"><i class="glyphicon glyphicon-chevron-up"></i></a>
									</span>
								</h3>
							</div>
							<div class="panel-body" style="overflow: hidden; display: block;">
								<!-- 放置表格或其他内容 -->
								<blockquote>
								  <p>欢迎使用"sechand"信息管理系统</p>
								<footer>用户类型：
								   <cite title="Source Title">
								  	${sessionScope.account.roleName}
								  </cite>
								</footer>
								</blockquote>
								<div class="row userMsg">
									<dl class="dl-horizontal col-xs-6">
									  <dt>用户名</dt>
									  <dd class="d-userName">${sessionScope.account.userName}</dd>
									  <dt>昵称</dt>
									  <dd class="d-nickName">${sessionScope.account.nickName}</dd>
									  <dt>真实姓名</dt>
									  <dd class="d-realName">${sessionScope.account.realName}</dd>
									  <dt>邮箱地址</dt>
									  <dd class="d-email">${sessionScope.account.email}</dd>
									  <dt>手机号</dt>
									  <dd class="d-tel">${sessionScope.account.tel}</dd>
									</dl>
									<dl class="dl-horizontal col-xs-6">
									  <dt>上次登入时间</dt>
									  <dd>${sessionScope.account.lastLoginTime}</dd>
									  <dt>账户状态</dt>
									  <dd>${sessionScope.account.status}</dd>
									  <dt>账户来源</dt>
									  <dd>${sessionScope.account.source}</dd>
									  <dt>注册时间</dt>
									  <dd>${sessionScope.account.registerTime}</dd>
									</dl>
									<button id="btn-edict" type="button" class="btn btn-primary">编辑</button>
								</div>
								<div class="row userMsgEdict">
									<dl class="dl-horizontal col-xs-6">
									  <dt>用户名</dt>
									  <dd>
									  	<input type="text" class="form-control" value="${sessionScope.account.userName}"/>
									  </dd>
									  <dt>昵称</dt>
									  <dd>
									  	<input type="text" class="form-control" value="${sessionScope.account.nickName}"></dd>
									  <dt>真实姓名</dt>
									  <dd><input type="text" class="form-control" value="${sessionScope.account.realName}"></dd>
									  <dt>邮箱地址</dt>
									  <dd><input type="text" class="form-control" value="${sessionScope.account.email}"></dd>
									  <dt>手机号</dt>
									  <dd><input type="text" class="form-control" value="${sessionScope.account.tel}"></dd>
									</dl>
									<dl class="dl-horizontal col-xs-6">
									  <dt>上次登入时间</dt>
									  <dd>${sessionScope.account.lastLoginTime}</dd>
									  <dt>账户状态</dt>
									  <dd>${sessionScope.account.status}</dd>
									  <dt>账户来源</dt>
									  <dd>${sessionScope.account.source}</dd>
									  <dt>注册时间</dt>
									  <dd>${sessionScope.account.registerTime}</dd>
									</dl>
									<button id="btn-save" type="button" class="btn btn-primary">保存</button>
								</div>
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
