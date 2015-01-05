<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
  <link href="css/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
  <link href="css/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet">
<!-- common css --> 
  <link rel="stylesheet" type="text/css" href="css/style.css">
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
						<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
						<input type="text" class="form-control" id="user_name" placeholder="用户名">
					</div>					  
				</div>
			</div>
			<div class="form-group">
				<label for="inputPassword1" class="col-lg-4 control-label">密&nbsp;&nbsp;&nbsp;&nbsp;码</label>
				<div class="col-lg-8">
					<div class="input-group">
						<span class="input-group-addon"><i class="glyphicon glyphicon-tag"></i></span>
						<input type="password" class="form-control" id="password" placeholder="密码">
					</div>					  
				</div>
			</div>
			<div class="form-group">
				<label for="inputPassword1" class="col-lg-4 control-label">角&nbsp;&nbsp;&nbsp;&nbsp;色</label>
				<div class="col-lg-8">
					<div class="input-group">
						<span class="input-group-addon"><i class="glyphicon glyphicon-leaf"></i></span>
						<select class="form-control" id="role_type" >
						</select>
					</div>					  
				</div>
			</div>
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
			<li><a href="javascript:$.W.alert('系统消息','请联系管理员!',true)">忘记密码?</a></li>
		</ul><!-- /Create and forget links -->
	</div>
</body>
 <script type="text/javascript">
		$(function() {
			//获取角色类型
			$.ajax({
		        type: "POST",
		        contentType: "application/json;utf-8",
		        dataType: "json",
		        /*  data:"type:1", */
		        url:"platform/roleAction!listRole.action?type=1",
		        success: function (result) {
		        	var html="" ;
		        	$("#role_type").empty();
		        	for ( var i = 0; i < result.length; i++) {//动态加载角色
						var r = result[i];
						html += "<option value=" + r.type + ">" + r.name + "</option>\r\n";
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
									window.location.href ='<%=path%>'+data.msg;
								}else{
									$.W.alert("系统消息",data.msg,true);
								}
							}
						}) ;
					});
		});
	</script>
</html>
