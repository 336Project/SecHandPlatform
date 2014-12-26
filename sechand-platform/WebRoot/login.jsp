<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>后天管理系统</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">

	<!-- Loading Bootstrap -->
	<link href="bootstrap/css/bootstrap.css" rel="stylesheet">

	<!-- Loading Stylesheets -->    
	<link href="css/archon.css" rel="stylesheet">
	<link href="css/responsive.css" rel="stylesheet">
	<link href="css/login.css" rel="stylesheet">
	<!-- Loading Custom Stylesheets -->    
	<link href="css/custom.css" rel="stylesheet">

	<!-- Loading Custom Stylesheets -->    
	<link href="css/custom.css" rel="stylesheet">

	<link rel="shortcut icon" href="images/favicon.ico">

	<!-- HTML5 shim, for IE6-8 support of HTML5 elements. All other JS at the end of file. -->
	<!--[if lt IE 9]>
	<script src="js/html5shiv.js"></script>
	<![endif]-->
	<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript">
		$(function() {
			$.ajax({//获取角色类型
		        type: "POST",
		        contentType: "application/json;utf-8",
		        dataType: "json",
		        /*  data:"type:1", */
		        url:"platform/roleAction!listRole.action?type=1",
		        success: function (result) {
		        	var html="" ;
		        	$("#role_type").empty();
		        	for ( var i = 0; i < result.length; i++) {//动态加载角色
						var r = result[i].name;
						html += "<option value=" + r + ">" + r + "</option>\r\n";
					}
		            $("#role_type").append(html);
		        }
    		});
    		
			$('#btnLogin').click(
					function() {
						$.ajax({
							type : "POST",
							url : "platform/accountAction!login.action",
							data : {
								username : $("#user_name").val(),
								password : $("#password").val(),
								type : $("#role_type").val()
							},
							dataType : "json",
							success : function(data) {
								if(data.success){
									alert("登录成功");
								}else{
									alert(data.msg);
								}
							}
						});
					});
		});
	</script>
</head>
<body>
	<div class="box-holder row">
		<!-- Title -->
		<h2>后台管理系统</h2><hr>
		<!-- Login Form -->
		<form class="form-horizontal">
			<div class="form-group">
				<label for="userName" class="col-lg-4 control-label">用户名</label>
				<div class="col-lg-8">
					<div class="input-group">
						<span class="input-group-addon"><i class="icon-user"></i></span>
						<input type="text" class="form-control" id="user_name" placeholder="用户名">
					</div>					  
				</div>
			</div>
			<div class="form-group">
				<label for="inputPassword1" class="col-lg-4 control-label">密&nbsp;&nbsp;&nbsp;&nbsp;码</label>
				<div class="col-lg-8">
					<div class="input-group">
						<span class="input-group-addon"><i class="icon-lock"></i></span>
						<input type="password" class="form-control" id="password" placeholder="password">
					</div>					  
				</div>
			</div>
			<div class="form-group">
				<label for="inputPassword1" class="col-lg-4 control-label">角&nbsp;&nbsp;&nbsp;&nbsp;色</label>
				<div class="col-lg-8">
					<div class="input-group">
						<span class="input-group-addon"><i class="glyphicon glyphicon-leaf"></i></span>
						<select class="form-control" id="role_type">
						</select>
					</div>					  
				</div>
			</div>
			<!-- <div class="form-group">
				<div class="col-lg-offset-2 col-lg-10">
					<div class="checkbox">
						<label>
							<input type="checkbox"> 记住密码
						</label>
					</div>
				</div>
			</div> -->
			<div class="form-group">
				<div class="col-lg-offset-2 col-lg-10">
					<button id="btnLogin" type="button" class="btn btn-default">登录</button>
				</div>
			</div>
		</form>
		<hr>
		<!-- Create and forget links -->
		<ul class="list-inline"><li><a href="">注册</a></li>
			<li>&nbsp;&nbsp;</li>
			<li><a href="javascript:alert('请联系管理员!')">忘记密码?</a></li>
		</ul><!-- /Create and forget links -->
	</div>

</body>
</html>
