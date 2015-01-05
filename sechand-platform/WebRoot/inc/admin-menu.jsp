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
	<li><a href=""><i class="icon-group"></i>角色管理</a></li>

	<script type="text/javascript">
		/* function listUsers(){
			$.ajax({
		        type: "POST",
		        dataType: "json",
		        url:"platform/accountAction!listUsers.action",
		        success: function (result) {
					var modelTable = $("#table-example").DataTable({
						data : (function() {
							if (result.success) {
								return result.msg;
							} else {
								return [];
							}
						})(),
						columns : [
								//设置每个列的标题和数据
								{
									data : 'id',sTitle : "ID"
								}, {
									data : 'userName',sTitle : "账号"
								}, {
									data : 'realName',sTitle : "真实姓名"
								}, {
									data : 'nickName',sTitle : "昵称"
								}, {
									data : 'email',sTitle : "邮箱"
								}, {
									data : 'tel',sTitle : "手机号码"
								}
						],
						bLengthChange: true,                 //用户不可改变每页显示数量  
						language : {
							"processing" : "正在加载中......",
							"zeroRecords" : "对不起，查询不到相关数据！",
							"emptyTable" : "表中无数据存在！",
							"info" : "当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录",
							"paginate" : {
								"first" : "首页",
								"previous" : "上一页",
								"next" : "下一页",
								"last" : "末页"
							}
						},
						dom : "<lf<t>ip>"
					});
		}});
		}; */

		function retrieveData(sSource, aoData, fnCallback) {
			// 将客户名称加入参数数组  
			//aoData.push( { "name": "customerName", "value": "asdas" } );//添加自己的额外参数  
			alert(JSON.stringify(aoData));
			$.ajax({
				"type" : "POST",
				"contentType" : "application/json",
				"url" : sSource,
				"dataType" : "json",
				"data" :JSON.stringify(aoData)
				, // 以json格式传递  
				"success" : function(resp) {
					fnCallback(resp.aaData);
				}
			});
		}
		function listUsers() {
			var table=$("#table-example").dataTable({
				"aoColumns" : [
				//设置每个列的标题和数据
				{
					data : 'id',sTitle : "ID"
				}, {
					data : 'userName',sTitle : "账号"
				}, {
					data : 'realName',sTitle : "真实姓名"
				}, {
					data : 'nickName',sTitle : "昵称"
				}, {
					data : 'email',sTitle : "邮箱"
				}, {
					data : 'tel',sTitle : "手机号码"
				}
				
		],
		"bProcessing": true,                    //加载数据时显示正在加载信息   
		"bServerSide": true,                    //指定从服务器端获取数据   
		"bFilter": false,                       //不使用过滤功能   
		"bLengthChange": false,                 //用户不可改变每页显示数量   
		"iDisplayLength": 8,                    //每页显示8条数据   
		"sAjaxSource": "platform/accountAction!listUsers.action",//获取数据的url   
		"fnServerData": retrieveData,           //获取数据的处理函数   
		"sPaginationType": "full_numbers",      //翻页界面类型   
		"oLanguage": {                       //汉化   
		    "sLengthMenu": "每页显示 _MENU_ 条记录",   
		    "sZeroRecords": "没有检索到数据",   
		    "sInfo": "当前数据为从第 _START_ 到第 _END_ 条数据；总共有 _TOTAL_ 条记录",   
		    "sInfoEmtpy": "没有数据",   
		    "sProcessing": "正在加载数据...",   
		    "oPaginate": {   
		        "sFirst": "首页",   
		        "sPrevious": "前页",   
		        "sNext": "后页",   
		        "sLast": "尾页"  
			} 
		}
		});
		table.fnDraw();
		}
	</script>
</body>
</html>
