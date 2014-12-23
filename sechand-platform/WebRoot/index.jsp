<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
  <div align="center">
    <form action="demo/accountAction!add.action" method="post" >
		用户名:<input name="account.name"/><br/>
		密码:<input name="account.password"/><br/>
		真实姓名:<input name="account.realname"/><br/>
		<button type="submit" >保存</button>
	</form>
	<!-- <form action="demo/accountAction!delete.action" method="post" >
		id:<input name="account.id"/><br/>
		<button type="submit" >删除</button>
	</form> -->
	<form action="demo/accountAction!delete.action" method="post" >
		id:<input name="id"/><br/>
		<button type="submit" >删除</button>
	</form>
	<form action="demo/accountAction!delete.action" method="post" >
		ids(使用","分隔):<input name="ids"/><br/>
		<button type="submit" >批量删除</button>
	</form>
	<form action="demo/accountAction!count.action" method="post" >
		<button type="submit" >统计</button>
	</form>
	<form action="demo/accountAction!getRecord.action" method="post" >
	    id:<input name="id"/><br/>	
		<button type="submit" >获取一条数据</button>
	</form>
	<form action="demo/accountAction!update.action" method="post" >
	    id:<input name="account.id"/><br/>	
		用户名:<input name="account.name"/><br/>
		密码:<input name="account.password"/><br/>
		真实姓名:<input name="account.realname"/><br/>
		<button type="submit" >修改</button>
	</form>
	<form action="demo/accountAction!list.action" method="post" >
		<button type="submit" >查询</button>
	</form>
	<form action="demo/accountAction!listByPage.action" method="post" >
		当前页：<input name="currentPage"/><br/>	
		页大小：<input name="pageSize"/><br/>	
		<button type="submit" >分页查找</button>
	</form>
	</div>
  </body>
</html>
