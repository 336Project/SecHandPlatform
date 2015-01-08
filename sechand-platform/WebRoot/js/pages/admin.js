/*
 * 全局对象-表格 完成初始化后方可使用
 * 获取方式:tables.user or tables.role
 * 获取表中数据:tables.user.data 
 * 获取表中行数据:tables.user.row(i)
*/
var tables = {
		user:null,
		role:null
};

//页面加载完成后执行
$(document).ready(function(){
	//页面初始化,
	view.init();
	
});

//页面内ui的控制
var view = {
		init:function(){
			//执行初始化用户列表    直接在ready中调用也是可以的，放于此处方便代码维护而已。
			tables.user = this.initUserTable();
			tables.role = this.initroleTable();
		},
		tableTool:function(){
			//$(删除)
		},
		/*
		 * 初始化用户列表
		 * return datatable 对象
		 */		
		initUserTable:function(){
			return $("#table-user").dataTable({
				"columns":[
							{ data: 'id',sTitle:"",
					        	render: function(id) {
									var str = "<input name=f value="+id+" type=checkbox>";
									return str;
					        	}
							},
							{data : 'id',sTitle : "ID"}, 
							{data : 'userName',sTitle : "账号"}, 
							{data : 'realName',sTitle : "真实姓名"}, 
							{data : 'nickName',sTitle : "昵称"}, 
							{data : 'email',sTitle : "邮箱"}, 
							{data : 'tel',sTitle : "手机号码"}
						],
				"processing": true,
		        "serverSide": true,
		        "fnServerData":function(n,params,fnCallback,table){
		        	params.push({name:"sSearch",value:params[5].value.value});
		        	$.ajax({
		        		url:"platform/accountAction!listUsers.action",
		        		type:"get",
		        		data:params,
		        		success:function(d){
		        			fnCallback(d.msg);
		        		}
		        	});
		        },
				"sort": false, 
				"language": {
					"search":"快速搜索",                    //汉化   
				    "lengthMenu": "每页显示 _MENU_ 条记录",   
				    "zeroRecords": "没有检索到数据",
				    "info" : "当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录",
				    "infoEmtpy": "没有数据",   
				    "processing": "正在加载数据...",   
				    "paginate": {   
				        "first": "首页",   
				        "previous": "上一页",   
				        "next": "下一页",   
				        "last": "尾页"  
					} 
				}
			});
		},
		/*
		 * 初始化角色列表
		 * return datatable 对象
		 */	
		initroleTable:function(){
			return $("#table-role").dataTable({
				"columns":[
							{ data: 'id',sTitle:"",
					        	render: function(id) {
									var str = "<input name=f value="+id+" type=checkbox>";
									return str;
					        	}
							},
							{data : 'id',sTitle : "ID"}, 
							{data : 'userName',sTitle : "账号"}, 
							{data : 'realName',sTitle : "真实姓名"}, 
							{data : 'nickName',sTitle : "昵称"}, 
							{data : 'email',sTitle : "邮箱"}, 
							{data : 'tel',sTitle : "手机号码"}
						],
				"processing": true,
		        "serverSide": true,
		        "ajax":{
		        	"url":"platform/accountAction!listUsers.action",
		        	"dataSrc": "msg.data"
		        },
				"sort": false, 
				"language": {
					"search":"快速搜索",                    //汉化   
				    "lengthMenu": "每页显示 _MENU_ 条记录",   
				    "zeroRecords": "没有检索到数据",
				    "info" : "当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录",
				    "infoEmtpy": "没有数据",   
				    "processing": "正在加载数据...",   
				    "paginate": {   
				        "first": "首页",   
				        "previous": "上一页",   
				        "next": "下一页",   
				        "last": "尾页"  
					} 
				}
			});
		}
};

