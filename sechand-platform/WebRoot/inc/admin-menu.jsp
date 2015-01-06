<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<body>
	<li class="nav-header">系统信息</li>
	<li><a href="javascript:viod(0)" onclick="listUsers()"><i class="icon-group"></i>用户管理</a></li>
	<li><a href="javascript:viod(0)" onclick="listRoles()"><i class="icon-group"></i>角色管理</a></li>

	<script type="text/javascript">
		var usersTable=null;
		var rolesTable=null;
		$(function(){
			
		});
		//表格获取数据的处理函数 
		function retrieveData(actionUrl, dataTableParams, fnCallback) {
			//dataTableParams.push( { "name": "customerName", "value": "asdas" } );//添加自己的额外参数  
			$.ajax({
				"type" : "POST",
				"url" : actionUrl,
				"dataType" : "json",
				"data" :{dataTableParams:JSON.stringify(dataTableParams)}, // 以json格式传递  
				"success" : function(resp) {
					fnCallback(resp.msg);
				}
			});
		}
		//用户管理点击事件
		function listUsers() {
			if(usersTable==null){
				//初始化表格
			usersTable=$("#table-example").dataTable({
				"columns" : [
				//设置每个列的标题和数据
				 { data: 'id',sTitle:"",
		        	render: function(id) {
					//自定义显示方式：
					var str = "<input name=f value="+id+" type=checkbox>";
					return str;
				}},
				{data : 'id',sTitle : "ID"}, 
				{data : 'userName',sTitle : "账号"}, 
				{data : 'realName',sTitle : "真实姓名"}, 
				{data : 'nickName',sTitle : "昵称"}, 
				{data : 'email',sTitle : "邮箱"}, 
				{data : 'tel',sTitle : "手机号码"}
				],
				"bProcessing": true,                    //加载数据时显示正在加载信息   
				"bServerSide": true,                    //指定从服务器端获取数据   
				"bFilter": true,                       //不使用过滤功能   
				"bLengthChange": true,                 //用户不可改变每页显示数量   
				"iDisplayLength": 10,                    //每页显示8条数据   
				"sAjaxSource": "platform/accountAction!listUsersForTable.action",//获取数据的url   
				"fnServerData": retrieveData,           //获取数据的处理函数   
				"sPaginationType": "full_numbers",      //翻页界面类型   
				"bPaginate":true,
				"bSort": false, //关闭排序
				"oLanguage": {
					"sSearch":"快速搜索",                    //汉化   
				    "sLengthMenu": "每页显示 _MENU_ 条记录",   
				    "sZeroRecords": "没有检索到数据",   
				    "sInfo": " _START_ - _END_ 条数据；总共有 _TOTAL_ 条记录",   
				    "sInfoEmtpy": "没有数据",   
				    "sProcessing": "正在加载数据...",   
				    "oPaginate": {   
				        "sFirst": "首页",   
				        "sPrevious": "上一页",   
				        "sNext": "下一页",   
				        "sLast": "尾页"  
					} 
				},
				});
			}else{
				usersTable.fnDraw();
			}
		}
		
		function listRoles(){
			if(rolesTable==null){
				//初始化表格
			rolesTable=$("#table-example").dataTable({
				"columns" : [
				//设置每个列的标题和数据
				 { data: 'id',sTitle:"",
		        	render: function(id) {
					//自定义显示方式：
					var str = "<input name=f value="+id+" type=checkbox>";
					return str;
				}},
				{data : 'id',sTitle : "ID"}, 
				{data : 'userName',sTitle : "用户名"}, 
				{data : 'realName',sTitle : "真实姓名"}, 
				{data : 'nickName',sTitle : "昵称"}, 
				{data : 'email',sTitle : "邮箱"}, 
				{data : 'tel',sTitle : "手机号码"}
				],
				"bProcessing": true,                    //加载数据时显示正在加载信息   
				"bServerSide": true,                    //指定从服务器端获取数据   
				"bFilter": true,                       //不使用过滤功能   
				"bLengthChange": true,                 //用户不可改变每页显示数量   
				"iDisplayLength": 10,                    //每页显示8条数据   
				"sAjaxSource": "platform/accountAction!listUsersForTable.action",//获取数据的url   
				"fnServerData": retrieveData,           //获取数据的处理函数   
				"sPaginationType": "full_numbers",      //翻页界面类型   
				"bPaginate":true,
				"bSort": false, //关闭排序
				"oLanguage": {
					"sSearch":"快速搜索",                    //汉化   
				    "sLengthMenu": "每页显示 _MENU_ 条记录",   
				    "sZeroRecords": "没有检索到数据",   
				    "sInfo": " _START_ - _END_ 条数据；总共有 _TOTAL_ 条记录",   
				    "sInfoEmtpy": "没有数据",   
				    "sProcessing": "正在加载数据...",   
				    "oPaginate": {   
				        "sFirst": "首页",   
				        "sPrevious": "上一页",   
				        "sNext": "下一页",   
				        "sLast": "尾页"  
					} 
				},
				});
			}else{
				rolesTable.fnDraw();
			}
		}
	</script>
</body>
</html>
