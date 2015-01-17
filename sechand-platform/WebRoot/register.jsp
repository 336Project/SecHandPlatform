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
  <link href="css/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
  <link href="css/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet">
<!-- common css --> 
  <link rel="stylesheet" type="text/css" href="css/style.css">
<!-- self css -->
  <link rel="stylesheet" type="text/css" href="css/pages/register.css">
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
		<div class="box-header">
			<h3>用户注册</h3>
		</div>
		<div class="box-content row">
			<div class="col-xs-12" id="register">
				<div class="form-horizontal" role="form">
				  <div class="form-group">
				    <label for="inputEmail3" class="col-sm-3 control-label">用户名</label>
				    <div class="col-sm-9">
				      <input type="text" class="form-control" id="username" name="userName" placeholder="用户名">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="inputEmail3" class="col-sm-3 control-label">密码</label>
				    <div class="col-sm-9">
				      <input type="password" class="form-control" id="password" name="password" placeholder="密码长度为6-20位">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="inputEmail3" class="col-sm-3 control-label">确认密码</label>
				    <div class="col-sm-9">
				      <input type="password" class="form-control" id="password2" placeholder="请再次输入密码">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="inputEmail3" class="col-sm-3 control-label">昵称</label>
				    <div class="col-sm-9">
				      <input type="text" class="form-control" id="nickName" placeholder="一个好的昵称，可以彰显个性" name="nickName">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="update-realName" class="col-sm-3 control-label">真实姓名</label>
				    <div class="col-sm-9">
				      <input type="text" class="form-control" id="realName" placeholder="请填写真实姓名或称呼" name="realName">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="inputEmail3" class="col-sm-3 control-label">邮箱</label>
				    <div class="col-sm-9">
				      <input type="email" class="form-control" id="email" placeholder="请正确输入邮箱格式" name="email">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="inputEmail3" class="col-sm-3 control-label">手机号码</label>
				    <div class="col-sm-9">
				      <input type="text" class="form-control" id="tel" placeholder="请输入正确的手机格式" name="tel">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="inputEmail3" class="col-sm-3 control-label">简介</label>
				    <div class="col-sm-9">
				      <textarea rows="5" class="form-control" id="introduction" placeholder="简要介绍自己，让别人更了解你" name="introduction"></textarea>
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="inputEmail3" class="col-sm-3 control-label">用户类型</label>
					    <div class="col-sm-9">
						     <select class="form-control" id="roleType" name="roleCode" >
							 </select>
					    </div>
				  </div>
				</div>
			</div>		
		</div>
		<div class="box-footer">
			<button id="btnRegister" type="button" class="btn btn-primary">注册</button>
			<a href="login.jsp" style="margin-left: 40px;">已有账号?</a>
		</div>
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
		        url:"platform/roleAction!listRole.action?type=2",
		        success: function (result) {
		        	var html="" ;
		        	$("#roleType").empty();
		        	for ( var i = 0; i < result.length; i++) {//动态加载角色
						var r = result[i];
						html += "<option value=" + r.code + ">" + r.name + "</option>\r\n";
					}
		            $("#roleType").append(html);
		        }
    		});
    		
			$("#btnRegister").click(
					 function() {
						$.ajax({
							type : "POST",
							url : "platform/userAction!register.action",
							data : {
								"user.userName":$("#register").find("[name=userName]").val(),
	        					"user.realName":$("#register").find("[name=realName]").val(),
	        					"user.password":$("#register").find("[name=password]").val(),
	        					"user.nickName":$("#register").find("[name=nickName]").val(),
	        					"user.email":$("#register").find("[name=email]").val(),
	        					"user.tel":$("#register").find("[name=tel]").val(),
	        					"user.introduction":$("#register").find("[name=introduction]").val(),
	        					"user.roleCode":$("#register").find("[name=roleCode]").val()
							},
							dataType : "json",
							success : function(data) {
								if(data.success){
									if(data.msg=="恭喜,注册成功!"){
										$.W.alert("系统消息","即将跳转到登录页面...",true,function(){
											window.location.href ='<%=path%>'+"/login.jsp";
										});
									}else{
										$.W.alert("系统消息",data.msg,true);
									}
								}else{
									$.W.alert("系统消息",data.msg,true);
								}
							}
						}) ;
					});
		});
</script>
</html>
