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
  <link rel="stylesheet" href="js/lib/select2/css/select2.min.css" type="text/css"></link>
<!-- html5 兼容 -->
  <!--[if lt IE 9]>
      <script src="js/html5shiv.min.js"></script>
    <![endif]-->
<!-- common js -->
  <script src="js/jquery-1.11.1.min.js"></script>
  <script src="css/bootstrap/js/bootstrap.min.js"></script>
  <script src="js/common.js"></script>
 
	
  <script type="text/javascript" src="js/lib/validation/jquery.validate.min.js"></script>
  
  <script type="text/javascript" src="js/lib/select2/js/select2.min.js"></script>
  <script type="text/javascript" src="js/lib/select2/js/i18n/zh-CN.js"></script>
  </head>
<body>
	<div class="box-holder row">
		<div class="box-header">
			<h3>用户注册</h3>
		</div>
		<form class="box-content row" id="registerForm">
			<div class="col-xs-12" id="register">
				<div class="form-horizontal" role="form">
				<div class="form-group">
				    <label for="inputEmail3" class="col-sm-3 control-label">用户类型</label>
					    <div class="col-sm-9">
						    <select class="select2" id="roleType" name="roleCode" ></select>
					    </div>
				  </div>
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
				      <input type="password" class="form-control" id="password2" name="password2" placeholder="请再次输入密码">
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
				      <textarea rows="5" class=" form-control" id="introduction" placeholder="简要介绍自己，让别人更了解你" name="introduction"></textarea>
				    </div>
				  </div>
				</div>
			</div>		
		</form>
		<div class="box-footer">
			<button id="btnRegister" type="button" class="btn btn-primary">注册</button>
			<a href="login.jsp" style="margin-left: 40px;">已有账号?</a>
		</div>
	</div>
</body>
<script type="text/javascript">
	$().ready(function(){
	    //被验证的元素是一个form元素，如果原来不是，请用一个<form></form>包裹一下表单
		$("#registerForm").validate({
	             onkeyup: false,
	             errorClass: 'error',
	             validClass: 'valid',
	             highlight: function(element) {
	                 $(element).closest('div').addClass("f-error");
	             },
	             unhighlight: function(element) {
	                 $(element).closest('div').removeClass("f-error");
	             },
	             errorPlacement: function(error, element) {
	                 $(element).closest('div').append(error);
	             },
	             rules: {
	                 userName:{required: true ,minlength: 6},
	                 password:{required: true ,minlength: 6,maxlength:20},
	                 password2:{required: true ,equalTo: "#password"},
	                 nickName:{required: true},
	                 realName:{required: false},
	                 email:{required: true,email:true },
	                 tel:{required: true,digits:true,minlength: 11,maxlength:11},/*最大最小都是11则只能输入11位的电话号码，digits:只能是整数  */
	                 introduction:{required: false},
	                 roleCode:{required: true}
	             },
	             messages:{
	                 userName:{required: "用户名不能为空",minlength:"用户名不能小于6个字符"},
	                 password:{required: "密码不能为空" ,minlength:"密码不能小于6位数",maxlength:"密码最多20位数"},
	                 password2:{required: "请输入相同的密码" ,equalTo: "请输入相同的密码"},
	                 nickName:{required: "昵称不能为空"},
	                 /* realName:{required: "真实姓名不能为空"}, */
	                 email:{required: "邮箱不能为空",email:"请输入正确格式的邮箱地址"},
	                 tel:{required: "电话不能为空",minlength: "请输入正确格式的电话号码",maxlength:"请输入正确格式的电话号码",digits:"请输入正确格式的电话号码"},
	                 /* introduction:{required: "自我介绍不能为空"}, */
	                 roleCode:{required: "请选择角色类型"}
	             }
	        });
  			
            //获取角色类型
    		 $.ajax({
		        type: "POST",
		        contentType: "application/json;utf-8",
		        dataType: "json",
		        //  data:"type:1", 
		        url:"platform/roleAction!listRole.action?type=2",
		        success: function (result) {
		         	/* var data=[];
		            for ( var i = 0; i < result.length; i++) {//动态加载角色
						var obj=new Object();
						obj.id=result[i].code;
						obj.text=result[i].name;
						data.push(obj);
					}
		        	$("#roleType").select2({
					  placeholder: "选择用户名",
					  data:data
					});  */
					var html="" ;
		        	$("#roleType").empty();
		        	for ( var i = 0; i < result.length; i++) {//动态加载角色
						var r = result[i];
						html += "<option value=" + r.code + ">" + r.name + "</option>\r\n";
					}
		            $("#roleType").append(html);
	            }
    		}); 
    		$("#registerForm")[0].reset();
    		$("#btnRegister").click(function() {
    			//.validate({。。。})是初始化验证表单，.valid()是进行验证
	    		  if($("#registerForm").valid()){
	    		      //如果表单验证通过则执行
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
										$.W.alert("系统消息","恭喜,注册成功!即将跳转到登录页面...",true,function(){
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
	    		  }
			});
	
	});
	
	//请尽量使用$().ready(function(){...js代码})来启动某个函数。意思是在加载完成后执行。页面会先完成界面渲染，后执行脚本。不会因为脚本执行而延迟渲染时间。js代码很少的时候可以忽略这点
	//$(function() {});
</script>
</html>
