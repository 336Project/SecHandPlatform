<%@page import="com.sechand.platform.model.Role"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
session.setAttribute("admin", Role.CODE_ADMIN);
session.setAttribute("company", Role.CODE_COMPANY);
session.setAttribute("customer", Role.CODE_CUSTOMER);
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
  <script>
  	jQuery.urlRoot = "<%=path%>";
  </script>
</head>
<body>
	<div class="frame">
		<!-- 菜单 start -->
		<div class="sidebar">
			<div class="wrapper">
				<!-- Replace the src of the image with your logo -->
				<a href="index.jsp" class="logo"><img src="images/logo.png" alt="后台管理" /></a>
				<jsp:include page="inc/menu-list.jsp"></jsp:include>
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
							<li>
							<a href="index.jsp">
								<s:if test='#session.user.roleCode==#session.admin'>系统管理</s:if>
								<s:else>用户管理</s:else>
							</a>
							</li>
							<li class="active">用户信息</li>
						</ul>
					</div>
				</div>
				<!-- 用户信息面板 -->
				<div class="row">
					<div class="col-md-12">
						<div class="panel panel-archon main-graph">
							<div class="panel-heading">
								<h3 class="panel-title">用户信息
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
								  	${sessionScope.user.roleName}
								  </cite>
								</footer>
								</blockquote>
								<div class="row userMsg">
									<dl class="dl-horizontal col-xs-6">
									  <dt>用户名</dt>
									  <dd class="d-userName">${sessionScope.user.userName}</dd>
									  <dt>昵称</dt>
									  <dd class="d-nickName">${sessionScope.user.nickName}</dd>
									  <dt>真实姓名</dt>
									  <dd class="d-realName">${sessionScope.user.realName}</dd>
									  <dt>邮箱地址</dt>
									  <dd class="d-email">${sessionScope.user.email}</dd>
									  <dt>手机号</dt>
									  <dd class="d-tel">${sessionScope.user.tel}</dd>
									  <dt>简介</dt>
									  <dd class="d-introduction">${sessionScope.user.introduction}</dd>
									</dl>
									<dl class="dl-horizontal col-xs-6">
									  <dt>账户余额</dt>
									  <dd>￥${sessionScope.user.balance}</dd>
									  <dt>上次登入时间</dt>
									  <dd>${sessionScope.user.lastLoginTime}</dd>
									  <dt>账户状态</dt>
									  <dd>${sessionScope.user.status}</dd>
									  <dt>账户来源</dt>
									  <dd>${sessionScope.user.source}</dd>
									  <dt>注册时间</dt>
									  <dd>${sessionScope.user.registerTime}</dd>
									</dl>
									<button id="btn-edict" type="button" class="btn btn-primary">编辑</button>
								</div>
								<div class="row userMsgEdict">
									<dl class="dl-horizontal col-xs-6">
									 <dt>用户名</dt>
									  <dd class="d-userName">${sessionScope.user.userName}<input type="text" class="form-control" name="id" style="display: none;" value="${sessionScope.user.id}"></dd>
									  <dt>昵称</dt>
									  	<dd><input type="text" class="form-control" name="nickName" value="${sessionScope.user.nickName}"></dd>
									  <dt>真实姓名</dt>
									  	<dd><input type="text" class="form-control" name="realName" value="${sessionScope.user.realName}"></dd>
									  <dt>邮箱地址</dt>
									 	 <dd><input type="text" class="form-control" name="email" value="${sessionScope.user.email}"></dd>
									  <dt>手机号</dt>
									  	<dd><input type="text" class="form-control" name="tel" value="${sessionScope.user.tel}"></dd>
									  <dt>简介</dt>
									  	<dd><input type="text" class="form-control" name="introduction" value="${sessionScope.user.introduction}"></dd>
									</dl>
									<dl class="dl-horizontal col-xs-6">
									  <dt>账户余额</dt>
									  <dd>￥${sessionScope.user.balance}</dd>
									  <dt>上次登入时间</dt>
									  <dd>${sessionScope.user.lastLoginTime}</dd>
									  <dt>账户状态</dt>
									  <dd>${sessionScope.user.status}</dd>
									  <dt>账户来源</dt>
									  <dd>${sessionScope.user.source}</dd>
									  <dt>注册时间</dt>
									  <dd>${sessionScope.user.registerTime}</dd>
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
