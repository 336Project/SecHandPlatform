<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
  <link href="../css/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet">
 <link rel="stylesheet" href="../js/lib/select2/css/select2.min.css" type="text/css"></link>
<!-- common css --> 
  <link rel="stylesheet" type="text/css" href="../css/style.css">
  <link rel="stylesheet" type="text/css" href="../css/archon.css">
  <link rel="stylesheet" type="text/css" href="../js/lib/datatables/css/jquery.dataTables.min.css">
  <link rel="stylesheet" type="text/css" href="../js/lib/datatables/css/jquery.dataTables_themeroller.css">

<!-- self css -->
  <link rel="stylesheet" href="../js/lib/switch/css/bootstrap-switch.min.css" type="text/css"></link>
  <link rel="stylesheet" type="text/css" href="css/userList.css">
  
<!-- html5 兼容 -->
  <!--[if lt IE 9]>
      <script src="../js/html5shiv.min.js"></script>
    <![endif]-->
<!-- common js -->
  <script src="../js/jquery-1.11.1.min.js"></script>
  <script src="../css/bootstrap/js/bootstrap.min.js"></script>
  <script src="../js/common.js"></script>
  <script type="text/javascript" src="../js/lib/datatables/js/jquery.dataTables.min.js"></script>
  
  <script type="text/javascript" src="../js/lib/validation/jquery.validate.min.js"></script>
  
  <script type="text/javascript" src="../js/lib/select2/js/select2.min.js"></script>
  <script type="text/javascript" src="../js/lib/select2/js/i18n/zh-CN.js"></script>
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
				<a href="<%=path %>/index.jsp" class="logo"><img src="../images/logo.png" alt="后台管理" /></a>
				<jsp:include page="../inc/menu-list.jsp"></jsp:include>
			</div>
		</div>
		<!-- 菜单 end -->

		<!-- 主要内容 start-->
		<div class="content">
			<jsp:include page="../inc/top-nav.jsp"></jsp:include>
			<div id="main-content">
				<!--面包屑导航-->
				<div class="row">
					<div class="col-mod-12">
						<ul class="breadcrumb">
							<li><a href="<%=path %>/index.jsp">系统管理</a></li>
							<li class="active">维修人员管理</li>
						</ul>
					</div>
				</div>
				<!-- 面板 -->
				<div class="row">
					<div class="col-md-12">
						<div class="panel panel-archon main-graph">
							<div class="panel-heading">
								<h3 class="panel-title">维修人员管理
									<span class="pull-right">
										<a href="#" class="panel-minimize"><i class="glyphicon glyphicon-chevron-up"></i></a>
									</span>
								</h3>
							</div>
							<div class="panel-body" style="overflow: hidden; display: block;">
								<!-- 放置表格或其他内容 -->
								<div class="tb-tools">
									<!-- <button id="btn-reset-password" type="button" class="btn btn-warning">重置密码</button> -->
									<button id="btn-delete" type="button" class="btn btn-warning">删 除</button>
									<button type="button" class="btn btn-primary" id="btn-modal-adduser">新 增</button>
									<button type="button" class="btn btn-primary" id="btn-modal-updateuser">修改</button>
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
		        <h4 class="modal-title" id="myModalLabel">添加维修人员信息</h4>
		      </div>
		      <div class="modal-body row">
		        <form class="form-horizontal col-xs-offset-2 col-xs-8 " id="addUserForm">
				  <div class="form-group">
				    <label for="inputEmail3" class="col-sm-4 control-label">用户名</label>
				    <div class="col-sm-8">
				      <input type="text" class="form-control" id="username" name="userName" placeholder="用户名">
				       <input type="text" class="form-control" id="userId" name="userId" value="${sessionScope.user.id}" style="display: none;">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="inputEmail3" class="col-sm-4 control-label">密码</label>
				    <div class="col-sm-8">
				      <input type="password" class="form-control" id="password" name="password" placeholder="密码长度为6-20位">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="inputEmail3" class="col-sm-4 control-label">确认密码</label>
				    <div class="col-sm-8">
				      <input type="password" class="form-control" id="password2" placeholder="请再次输入密码">
				    </div>
				  </div>
				  <!-- <div class="form-group">
				    <label for="inputEmail3" class="col-sm-4 control-label">昵称</label>
				    <div class="col-sm-8">
				      <input type="text" class="form-control" id="nickName" placeholder="一个好的昵称，可以彰显个性" name="nickName">
				    </div>
				  </div> -->
				  <div class="form-group">
				    <label for="update-realName" class="col-sm-4 control-label">真实姓名</label>
				    <div class="col-sm-8">
				      <input type="text" class="form-control" id="realName" placeholder="请填写真实姓名或称呼" name="realName">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="inputEmail3" class="col-sm-4 control-label">邮箱</label>
				    <div class="col-sm-8">
				      <input type="email" class="form-control" id="email" placeholder="请正确输入邮箱格式" name="email">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="inputEmail3" class="col-sm-4 control-label">手机号码</label>
				    <div class="col-sm-8">
				      <input type="text" class="form-control" id="tel" placeholder="请输入正确的手机格式" name="tel">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="inputEmail3" class="col-sm-4 control-label">地址</label>
				    <div class="col-sm-8">
				      <input type="text" class="form-control" id="introduction" placeholder="" name="introduction">
				    </div>
				  </div>
				  <!-- <div class="form-group">
				    <label for="inputEmail3" class="col-sm-4 control-label">用户类型</label>
					    <div class="col-sm-8">
						     <select class="form-control" id="roleType" name="roleCode" >
							 </select>
					    </div>
				  </div> -->
				</form>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		        <button id="btn-addUser" type="button" class="btn btn-primary">添加</button>
		      </div>
		    </div>
		  </div>
		</div>
		
		<!-- 新增用户弹出框  end  -->
		<!-- 修改用户弹出框 start  -->
		<div class="modal fade" id="updateUser" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
		        <h4 class="modal-title" id="myModalLabel">编辑用户信息</h4>
		      </div>
		      <div class="modal-body row">
		        <form class="form-horizontal col-xs-offset-2 col-xs-8 " role="form" id="updateUserForm">
				  <div class="form-group">
				    <label for="update-username" class="col-sm-4 control-label">用户名</label>
				    <div class="col-sm-8">
				      <input type="text" class="form-control" id="update-username" name="userName" placeholder="用户名" disabled="disabled">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="update-roleName" class="col-sm-4 control-label">用户类型</label>
				    <div class="col-sm-8">
					     <input type="text" class="form-control" id="update-roleName" name="roleName" placeholder="角色名称" disabled="disabled">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="update-realName" class="col-sm-4 control-label">真实姓名</label>
				    <div class="col-sm-8">
				      <input type="text" class="form-control" id="update-realName" placeholder="请填写真实姓名或称呼" name="realName">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="update-email" class="col-sm-4 control-label">邮箱</label>
				    <div class="col-sm-8">
				      <input type="email" class="form-control" id="update-email" placeholder="请正确输入邮箱格式" name="email">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="update-tel" class="col-sm-4 control-label">手机号码</label>
				    <div class="col-sm-8">
				      <input type="text" class="form-control" id="update-tel" placeholder="请输入正确的手机格式" name="tel">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="inputEmail3" class="col-sm-4 control-label">地址</label>
				    <div class="col-sm-8">
				      <input type="text" class="form-control" id="update-introduction" placeholder="" name="introduction">
				    </div>
				  </div>
				</form>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		        <button id="btn-updateUser" type="button" class="btn btn-primary">保存</button>
		      </div>
		    </div>
		  </div>
		</div>
		<!-- 修改用户弹出框  end  -->
		<div class="row footer">
			<div class="col-md-12 text-center">
				© 2015 <a href="#">版权申明</a>
			</div>
		</div>
	</div>

	<script src="../js/archon.js"></script>
	<script type="text/javascript" src="../js/lib/switch/js/bootstrap-switch.min.js"></script>
	<script type="text/javascript" src="js/repairList.js"></script>
</body>
</html>
