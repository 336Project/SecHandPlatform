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
  <script type="text/javascript" src="../js/lib/validation/jquery.validate.min.js"></script>
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
									<button type="button" class="btn btn-primary" id="btn-modal-updateOrder">报价</button>
									<button type="button" class="btn btn-primary" id="btn-complete">完成</button>
								</div>
								<table id="table-order" class="hover order-column"></table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- 主要内容 end'-->
		
		<!-- 报价修改弹出框 start  -->
		<div class="modal fade" id="updateRepair" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
		        <h4 class="modal-title" id="myModalLabel">报价</h4>
		      </div>
		      <div class="modal-body row">
		        <form class="form-horizontal col-xs-offset-2 col-xs-8 " role="form" id="updateRepairForm">
				  <div class="form-group">
				    <label for="inputEmail3" class="col-sm-4 control-label">报修内容</label>
				    <div class="col-sm-8">
				      <textarea rows="8" class="form-control" id="update-repairContent" name="repairContent" placeholder="请填写需要维修事项" disabled="disabled"></textarea>
				      <input type="text" class="form-control" id="update-userId" placeholder="" name="userId" value="${sessionScope.user.id}" style="display: none;">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="inputEmail3" class="col-sm-4 control-label">客户联系电话</label>
				    <div class="col-sm-8">
				      <input type="text" class="form-control" id="update-contactTelUser" placeholder="" name="contactTelUser" disabled="disabled">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="inputEmail3" class="col-sm-4 control-label">我的联系电话</label>
				    <div class="col-sm-8">
				      <input type="text" class="form-control" id="update-contactTelCompany" placeholder="" name="contactTelCompany" value="${sessionScope.user.tel}" >
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="inputEmail3" class="col-sm-4 control-label">我的报价</label>
				    <div class="col-sm-8">
				      <input type="text" class="form-control" id="update-price" placeholder="请输入报价金额" name="price" >
				    </div>
				  </div>
				</form>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		        <button id="btn-updateRepair" type="button" class="btn btn-primary" data-dismiss="modal">确定</button>
		      </div>
		    </div>
		  </div>
		</div>
		<!-- 报价修改弹出框  end  -->
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
