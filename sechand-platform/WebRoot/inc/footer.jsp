<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=Edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>分析算法工具</title>
<!-- bootstrap -->
  <link href="css/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
  <link href="css/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
<!-- common css --> 
  <link rel="stylesheet" type="text/css" href="css/style.css">
<!-- self css -->
  <link rel="stylesheet" type="text/css" href="css/index.css">
<!-- html5 兼容 -->
  <!--[if lt IE 9]>
      <script src="js/html5shiv.min.js"></script>
    <![endif]-->
</head>
<body>
  <!-- 左侧面板 start -->
  <section class="catalog">
    <h4 class="left-title">分析算法驱动工具</h4>
    <ul class="nav nav-pills">
	    <li class="active" data-type="menu"><a href="javascript:void(0);">模型菜单</a></li>
	    <li  data-type="list"><a href="javascript:void(0);">执行历史</a></li>
    </ul>
    <!-- 左侧模型菜单 -->
    <ul id="menu" class="ztree"></ul>
    <!-- 左侧算法列表 -->
    <ul id="list" class="unstyled">
    	<li data-lim="50">最近50条</li>
    	<li data-lim="200">最近200条</li>
    	<li data-lim="1000">最近1000条</li>
    	<li data-lim="0" class="disable">高级查询</li>
    </ul>
	<!-- 新建模型提示框 -->
    <div class="new-modules">
      <div class="title-container">
  			<h3>新建模型</h3>
  			<div class="title-tools">
  				<button class="close cancel">&times;</button>
  	   		</div>
  	  </div>
      <form class="form-horizontal select-container">
  	    <div class="control-group">
  	    	<label class="control-label" for="new-modules-type">业务类型</label>
  		    <div class="controls">
  		    	<select id="new-modules-type"></select>
  		    </div>
  	    </div>
  	    <div class="control-group">
  	    	<label class="control-label" for="new-modules-lang">算法语言</label>
  		    <div class="controls">
  		    	<select id="new-modules-lang"></select>
  		    </div>
  	    </div>
      </form>
      <div class="btn-container">
        <button class="btn btn-primary sure">确定</button>
        <button class="btn cancel">取消</button>
      </div>
    </div>
    <div class="new-backdrop"></div>
    <!-- 是否隐藏选择框 -->
    <div id="toggleNodes">
    	<form class="form-inline">
		    <label class="checkbox">
		    	<input type="checkbox" name="isHideNodes" id="isHideNodes" checked="checked">
		    	隐藏不可用模型
		    </label>
	    </form>
    </div>
  </section>
  <!-- 左侧目录 end -->

  <!-- 右侧主面板 start -->
  <section class="main">
    <iframe src="modules/runtime_start.jsp" id="rightIframe" name="rightIframe"></iframe>
  </section>
  <!-- 右侧主面板 end -->
  
  <!-- 等待提示框 -->
  <div class="tech-loading">
    <i class="fa fa-spinner fa-spin"></i>
    <span>加载中，请耐心等待...</span> 
  </div>
  <div class="tech-backdrop"></div>
  
  <!-- 消息提示框 -->
  <div class="tech-alert" style="display:none;">
    <div>
      <span class="close">X</span>
      <p class="title">系统消息：</p>
      <section class="message">
		消息提示!
      </section>
      <p class="alert-btn">
        <button class="btn btn-small btn-primary sure" type="button">确定</button>
        <button class="btn btn-small cancel" type="button">取消</button>
      </p>
    </div>
  </div>
  <!-- common js -->
  <script src="js/jquery-1.11.1.min.js"></script>
  <script src="css/bootstrap/js/bootstrap.min.js"></script>
  <script src="js/common.js"></script>
  <!-- self js -->
  <script src="js/index.js"></script>
</body>
</html>