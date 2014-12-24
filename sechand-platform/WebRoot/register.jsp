<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="zh">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta charset="utf-8">
  <!-- Title and other stuffs -->
  <title>用户注册页面</title> 
  <meta name="keywords" content="" />
  <meta name="description" content="" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="author" content="">
  <!-- Stylesheets -->
  <link href="bootstrap/style/bootstrap.css" rel="stylesheet">
  <link rel="stylesheet" href="bootstrap/style/font-awesome.css">
  <link href="bootstrap/style/style.css" rel="stylesheet">
  <link href="bootstrap/style/bootstrap-responsive.css" rel="stylesheet">
  
  <!-- HTML5 Support for IE -->
  <!--[if lt IE 9]>
  <script src="js/html5shim.js"></script>
  <![endif]-->

  <!-- Favicon -->
  <link rel="shortcut icon" href="bootstrap/img/favicon/favicon.png">
</head>

<body>

<div class="admin-form">
  <div class="container">
    <div class="row">
      <div class="col-lg-12">
        <!-- Widget starts -->
            <div class="widget wred">
              <div class="widget-head">
                <i class="icon-lock"></i> 用户注册
              </div>
              <div class="widget-content">
                <div class="padd">
                  
                  <form class="form-horizontal" action="<%=path%>/platform/accountAction!register.action" method="post">
                    <!-- Registration form starts -->
                    					  <!-- Select box role-->
                                          <div class="form-group">
                                            <label class="control-label col-lg-3">用户类型:</label>
                                            <div class="col-lg-9">                               
                                                <select class="form-control" id="role" name="account.roleId">
                                                
                                                </select>  
                                            </div>
                                          </div>   
                                      <!-- Name -->
                                          <div class="form-group">
                                            <label class="control-label col-lg-3" for="name">昵称:</label>
                                            <div class="col-lg-9">
                                              <input type="text" class="form-control" id="name" name="account.nickName">
                                            </div>
                                          </div>   
                                          <!-- Email -->
                                          <div class="form-group">
                                            <label class="control-label col-lg-3" for="email">邮箱:</label>
                                            <div class="col-lg-9">
                                              <input type="text" class="form-control" id="email" name="account.email">
                                            </div>
                                          </div>
                                          <!-- Select box sex-->
                                          <!-- <div class="form-group">
                                            <label class="control-label col-lg-3">性别:</label>
                                            <div class="col-lg-9">                               
                                                <select class="form-control" name="account.sex">
                                                <option value="男" selected="selected">男</option>
                                                <option value="女">女</option>
                                                </select>  
                                            </div>
                                          </div>          -->                                  
                                          <!-- Username -->
                                          <div class="form-group">
                                            <label class="control-label col-lg-3" for="username">用户名:</label>
                                            <div class="col-lg-9">
                                              <input type="text" class="form-control" id="username" name="account.userName">
                                            </div>
                                          </div>
                                          <!-- Password -->
                                          <div class="form-group">
                                            <label class="control-label col-lg-3" for="email">密码:</label>
                                            <div class="col-lg-9">
                                              <input type="password" class="form-control" id="password" name="account.password">
                                            </div>
                                          </div>
                                          <!-- Accept box and button s-->
                                          <div class="form-group">
                                            <div class="col-lg-9 col-lg-offset-3">
											  <div class="checkbox">
                                              <label>
                                                <input type="checkbox"> 我已接受注册协议
                                              </label>
											  </div>
                                              <br />
                                              <button type="submit" class="btn btn-danger">免费注册</button>
                                              <button type="reset" class="btn btn-success">重设</button>
                                            </div>
                                          </div>
										  <br />
                  </form>

                </div>
              </div>
                <div class="widget-foot">
                  已经注册过了? <a href="<%=path %>/login.jsp">登录</a>
                </div>
            </div>  
      </div>
    </div>
  </div> 
</div>
	
		

<!-- JS -->
<script src="bootstrap/js/jquery.js"></script>
<script src="bootstrap/js/bootstrap.js"></script>
<script>
	$(function(){
		$.ajax({//获取角色
        type: "POST",
        contentType: "application/json;utf-8",
        dataType: "json",
        url: "platform/roleAction!listRole.action?type=2",
        success: function (result) {
        	var html="" ;
        	for ( var int = 0; int < result.length; int++) {//动态加载用户类型
				var r = result[int];
				html += "<option value=" + r.id + ">" + r.name + "</option>\r\n";
			}
            $("#role").append(html);
        }
    	});
	});
</script>
</body>
</html>