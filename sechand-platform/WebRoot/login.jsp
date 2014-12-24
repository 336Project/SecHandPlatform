<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
<title>系统登录</title>
<link href="css/login.css" rel="stylesheet" rev="stylesheet"
	type="text/css" media="all" />
<link href="css/demo.css" rel="stylesheet" rev="stylesheet"
	type="text/css" media="all" />
<script type="text/javascript" src="js/jquery1.42.min.js"></script>
<script type="text/javascript" src="js/jquery.SuperSlide.js"></script>
<script type="text/javascript" src="js/Validform_v5.3.2_min.js"></script>

<script>
	$(function() {
		$.ajax({//获取角色
        type: "POST",
        contentType: "application/json;utf-8",
        dataType: "json",
        url: "platform/roleAction!list.action?type=1",
        success: function (result) {
        	var html="" ;
        	for ( var int = 0; int < result.length; int++) {
				var r = result[int].name;
				html += "<option value=" + r + ">" + r + "</option>\r\n";
			}
            $("#type").append(html);
        }
    	});
		$(".i-text").focus(function() {
			$(this).addClass('h-light');
		});

		$(".i-text").focusout(function() {
			$(this).removeClass('h-light');
		});

		$("#username").focus(function() {
			var username = $(this).val();
			if (username == '输入账号') {
				$(this).val('');
			}
		});

		$("#username").focusout(function() {
			var username = $(this).val();
			if (username == '') {
				$(this).val('输入账号');
			}
		});

		$("#password").focus(function() {
			var username = $(this).val();
			if (username == '输入密码') {
				$(this).val('');
			}
		});

		$("#yzm").focus(function() {
			var username = $(this).val();
			if (username == '输入验证码') {
				$(this).val('');
			}
		});

		$("#yzm").focusout(function() {
			var username = $(this).val();
			if (username == '') {
				$(this).val('输入验证码');
			}
		});

		$(".registerform").Validform({
			tiptype : function(msg, o, cssctl) {
			//msg：提示信息;
    		//o:{obj:*,type:*,curform:*},
    		//obj指向的是当前验证的表单元素（或表单对象，验证全部验证通过，提交表单时o.obj为该表单对象），
    		//type指示提示的状态，值为1、2、3、4， 1：正在检测/提交数据，2：通过验证，3：验证失败，4：提示ignore状态, 
    		//curform为当前form对象;
    		//cssctl:内置的提示信息样式控制函数，该函数需传入两个参数：显示提示信息的对象 和 当前提示的状态（既形参o中的type）;
				var objtip = $(".error-box");
				cssctl(objtip, o.type);
				objtip.text(msg);
			},
			ajaxPost : true,
			callback:function(result){//回调
				if(result.success){
					window.location.href='<%=path%>'+result.msg;
				}else{
					var objtip = $(".error-box");
					objtip.text(result.msg);
				}
			}
		});

	});
</script>


</head>

<body>


	<div class="header">
		<h1 class="headerLogo">
			<a title="后台管理系统" target="_blank" ><img alt="logo"
				src="images/logo.gif"/>
			</a>
		</h1>
		<!-- <div class="headerNav">
			<a target="_blank" href="#">华软官网</a> <a target="_blank" href="#">关于华软</a>
			<a target="_blank" href="#">开发团队</a> <a target="_blank" href="#">意见反馈</a>
			<a target="_blank" href="#">帮助</a>
		</div> -->
	</div>

	<div class="banner" >

		<div class="login-aside" >
			<div id="o-box-up"></div>
			<div id="o-box-down" style="table-layout:fixed;">
				<div class="error-box" style="height: 5px;color: red;"></div>
				<form class="registerform" action="platform/accountAction!login.action" >
					<div class="fm-item">
						<label for="logonId" class="form-label">用户名：</label> <input
							type="text" value="输入账号" maxlength="100" id="username"
							class="i-text"  datatype="s5-18"
							name="username"
							errormsg="用户名至少5个字符,最多18个字符！"/>
							<div class="ui-form-explain"></div>
					</div>

					<div class="fm-item">
						<label for="logonId" class="form-label">密码：</label> <input
							type="password" value="" maxlength="100" id="password"
							name="password"
							class="i-text" datatype="*5-16" nullmsg="请设置密码！"
							errormsg="密码范围在6~16位之间！"/>
							<div class="ui-form-explain"></div>
					</div>
					<div class="fm-item" >
						<label for="logonId" class="form-label">角色：</label> 
						<select class="i-text" style="width: 245px;text-align: center;" id="type" name="type">
							<!-- <option value="管理员">管理员</option>
							<option value="公司">公司</option>
							<option value="用户">用户</option> -->
						</select>
							<div class="ui-form-explain"></div>
					</div>
					<!-- <div class="fm-item pos-r">
						<label for="logonId" class="form-label">验证码</label> <input
							type="text" value="输入验证码" maxlength="100" id="yzm"
							class="i-text yzm" nullmsg="请输入验证码！"/>
							<div class="ui-form-explain">
								<img src="images/yzm.jpg" class="yzm-img" />
							</div>
					</div> -->

					<div class="fm-item">
						<label for="logonId" class="form-label"></label> 
						<input
							type="submit" value="" tabindex="4" id="send-btn"
							class="btn-login"/>
							<div class="ui-form-explain"></div>
					</div>
					<div class="fm-item" style="margin-top: 10px;">
						<a href="<%=path %>/register.jsp">免费注册</a>
							<div class="ui-form-explain"></div>
					</div>
				</form>
			</div>

		</div>

		<div class="bd">
			<ul>
				<li
					style="background:url(themes/theme-pic1.jpg) #CCE1F3 center 0 no-repeat;"><a
					target="_blank" ></a>
				</li>
				<li
					style="background:url(themes/theme-pic2.jpg) #BCE0FF center 0 no-repeat;"><a
					target="_blank" ></a>
				</li>
			</ul>
		</div>

		<div class="hd">
			<ul></ul>
		</div>
	</div>
	<script type="text/javascript">
		jQuery(".banner").slide({
			titCell : ".hd ul",
			mainCell : ".bd ul",
			effect : "fold",
			autoPlay : true,
			autoPage : true,
			trigger : "click"
		});
	</script>


	<div class="banner-shadow"></div>

	<div class="footer">
		<p>版权所有 Copyright 2012-2013 HUARUAN Corporation, All Rights Reserved</p>
	</div>

	<div style="text-align:center;"></div>

</body>
</html>
