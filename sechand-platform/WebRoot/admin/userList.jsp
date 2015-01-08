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
  <link href="../css/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
<!--  -->  <link href="../css/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet">

<!-- common css --> 
  <link rel="stylesheet" type="text/css" href="../css/style.css">
  <link rel="stylesheet" type="text/css" href="../css/archon.css">
  <link rel="stylesheet" type="text/css" href="../js/lib/datatables/css/jquery.dataTables.min.css">
  <link rel="stylesheet" type="text/css" href="../js/lib/datatables/css/jquery.dataTables_themeroller.css">
<!-- self css -->
  <link rel="stylesheet" type="text/css" href="../css/pages/index.css">
<!-- html5 兼容 -->
  <!--[if lt IE 9]>
      <script src="../js/html5shiv.min.js"></script>
    <![endif]-->
<!-- common js -->
  <script src="../js/jquery-1.11.1.min.js"></script>
  <script src="../css/bootstrap/js/bootstrap.min.js"></script>
  <script src="../js/common.js"></script>
  <script type="text/javascript" src="../js/lib/datatables/js/jquery.dataTables.min.js"></script>
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
				<a href="index.jsp" class="logo"><img src="../images/logo.png" alt="后台管理" /></a>
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
						<jsp:include page="../inc/admin-menu.jsp"></jsp:include>
					</s:if>
					<!-- 维修公司 -->
					<s:elseif test='#session.account.roleType==#session.company'>
						<jsp:include page="../inc/company-menu.jsp"></jsp:include>
					</s:elseif>
					<!-- 普通用户 -->
					<s:elseif test='#session.account.roleType==#session.user'>
						<jsp:include page="../inc/user-menu.jsp"></jsp:include>
					</s:elseif>
				</ul>
			</div>
		</div>
		<!-- 菜单 end -->

		<!-- 主要内容 start-->
		<div class="content">
			<div class="navbar">
				<a href="" class="btn pull-left toggle-sidebar"><i class="icon-list"></i></a>
				<a class="navbar-brand" href="index.html">后台管理</a>

				<!-- 右上角 user menu -->
				<ul class="nav navbar-nav user-menu pull-right">
					<li class="dropdown user-name">
						<a class="dropdown-toggle" data-toggle="dropdown">
						<!-- 用户名称 -->
						<img src="../images/theme/avatarSeven.png" class="user-avatar" alt="" />${sessionScope.account.nickName}</a>
							<ul class="dropdown-menu right inbox user">
								<li class="user-avatar">
								<!-- 角色名称 -->
									<img src="../images/theme/avatarSeven.png" class="user-avatar" alt="" />
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
								<h3 class="panel-title">用户管理
									<span class="pull-right">
										<a href="#" class="panel-minimize"><i class="glyphicon glyphicon-chevron-up"></i></a>
									</span>
								</h3>
							</div>
							<div class="panel-body" style="overflow: hidden; display: block;">
								<!-- 放置表格或其他内容 -->
								<div class="tb-tools">
									<button id="btn-delete" type="button" class="btn btn-warning">删 除</button>
									<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addUser">新 增</button>
								</div>
								<table id="table-user" class="hover order-column"></table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- 主要内容 end'-->
		<!-- 新增用户弹出框 start  -->
		<div class="modal fade" id="addUser" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
		        <h4 class="modal-title" id="myModalLabel">添加新用户</h4>
		      </div>
		      <div class="modal-body row">
		        <form class="form-horizontal col-xs-offset-2 col-xs-8 " role="form">
				  <div class="form-group">
				    <label for="inputEmail3" class="col-sm-4 control-label">用户名</label>
				    <div class="col-sm-8">
				      <input type="text" class="form-control" id="username" name="account.userName" placeholder="用户名">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="inputEmail3" class="col-sm-4 control-label">密码</label>
				    <div class="col-sm-8">
				      <input type="password" class="form-control" id="password" name="account.password" placeholder="密码长度为6-20位">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="inputEmail3" class="col-sm-4 control-label">确认密码</label>
				    <div class="col-sm-8">
				      <input type="password" class="form-control" id="password2" placeholder="">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="inputEmail3" class="col-sm-4 control-label">昵称</label>
				    <div class="col-sm-8">
				      <input type="text" class="form-control" id="nickName" placeholder="一个好的昵称，可以彰显个性" name="account.nickName">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="inputEmail3" class="col-sm-4 control-label">邮箱</label>
				    <div class="col-sm-8">
				      <input type="email" class="form-control" id="email" placeholder="请正确输入邮箱格式" name="account.email">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="inputEmail3" class="col-sm-4 control-label">手机号码</label>
				    <div class="col-sm-8">
				      <input type="text" class="form-control" id="tel" placeholder="请输入正确的手机格式" name="account.tel">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="inputEmail3" class="col-sm-4 control-label">用户类型</label>
					    <div class="col-sm-8">
						     <select class="form-control" id="roleType" name="account.roleId" >
							 </select>
					    </div>
				  </div>
				</form>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		        <button id="btn-addUser" type="button" class="btn btn-primary" data-dismiss="modal">添加</button>
		      </div>
		    </div>
		  </div>
		</div>
		
		<!-- 新增用户弹出框  end  -->
		<div class="row footer">
			<div class="col-md-12 text-center">
				© 2015 <a href="#">版权申明</a>
			</div>
		</div>
	</div>

	<script src="../js/archon.js"></script>
	<script type="text/javascript" src="js/userList.js"></script>
</body>
</html>