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
<!--  -->  <link href="../css/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet">

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
							<li><a href="<%=path %>/index.jsp">订单管理</a></li>
							<li class="active">订单信息</li>
						</ul>
					</div>
				</div>
				<!-- 面板 -->
				<div class="row">
					<div class="col-md-12">
						<div class="panel panel-archon main-graph">
							<div class="panel-heading">
								<h3 class="panel-title">订单管理
									<span class="pull-right">
										<a href="#" class="panel-minimize"><i class="glyphicon glyphicon-chevron-up"></i></a>
									</span>
								</h3>
							</div>
							<div class="panel-body" style="overflow: hidden; display: block;">
								<!-- 放置表格或其他内容 -->
								<div class="tb-tools">
									<button type="button" class="btn btn-primary" id="btn-modal-lookOrder">详情</button>
									<button id="btn-disable" type="button" class="btn btn-warning">作废</button>
									<button id="btn-delete" type="button" class="btn btn-warning">删除</button>
								</div>
								<table id="table-order" class="hover order-column"></table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- 主要内容 end'-->
		<!-- 详情弹出框 start  -->
		<div class="modal fade" id="lookOrder" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
		        <h4 class="modal-title" id="myModalLabel">订单详情</h4>
		      </div>
		      <div class="modal-body row">
		        <form class="form-horizontal col-xs-offset-2 col-xs-8 " role="form">
				  <div class="form-group">
				    <label for="inputEmail3" class="col-sm-4 control-label">用户账号</label>
				    <div class="col-sm-8">
				      <input type="text" class="form-control" id="look-customerUser" name="customerUser" placeholder="" >
				    </div>
				  </div>
				   <div class="form-group">
				    <label for="inputEmail3" class="col-sm-4 control-label">用户联系电话</label>
				    <div class="col-sm-8">
				      <input type="text" class="form-control" id="look-contactTelUser" name="contactTelUser" placeholder="" >
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="inputEmail3" class="col-sm-4 control-label">公司账号</label>
				    <div class="col-sm-8">
				      <input type="text" class="form-control" id="look-customerCompany" placeholder="" name="customerCompany" >
				    </div>
				  </div>
				   <div class="form-group">
				    <label for="inputEmail3" class="col-sm-4 control-label">公司联系电话</label>
				    <div class="col-sm-8">
				      <input type="text" class="form-control" id="look-contactTelCompany" name="contactTelCompany" placeholder="" >
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="update-realName" class="col-sm-4 control-label">创建时间</label>
				    <div class="col-sm-8">
				      <input type="text" class="form-control" id="look-createTime" placeholder="" name="createTime" > 
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="inputEmail3" class="col-sm-4 control-label">完成时间</label>
				    <div class="col-sm-8">
				      <input type="text" class="form-control" id="look-completeTime" placeholder="" name="completeTime">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="inputEmail3" class="col-sm-4 control-label">报价(元)</label>
				    <div class="col-sm-8">
				      <input type="text" class="form-control" id="look-price" placeholder="" name="price">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="inputEmail3" class="col-sm-4 control-label">报修内容</label>
				    <div class="col-sm-8">
				      <textarea rows="5" class="form-control" id="look-repairContent" placeholder="" name="repairContent"></textarea>
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="inputEmail3" class="col-sm-4 control-label">状态</label>
				    <div class="col-sm-8">
				      <input type="text" class="form-control" id="look-status" placeholder="" name="status">
				    </div>
				  </div>
				</form>
		      </div>
		      <div class="modal-footer">
		        <button id="btn-sure" type="button" class="btn btn-primary" data-dismiss="modal">确定</button>
		      </div>
		    </div>
		  </div>
		</div>
		
		<!-- 详情弹出框  end  -->
		<div class="row footer">
			<div class="col-md-12 text-center">
				© 2015 <a href="#">版权申明</a>
			</div>
		</div>
	</div>

	<script src="../js/archon.js"></script>
	<script type="text/javascript" src="../js/lib/switch/js/bootstrap-switch.min.js"></script>
	<script type="text/javascript" src="js/orderList.js"></script>
</body>
</html>
