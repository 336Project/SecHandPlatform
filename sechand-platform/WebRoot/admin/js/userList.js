/*
 * 全局对象-表格 完成初始化后方可使用
 * 获取方式:tables.user or tables.role
 * 获取表中数据:tables.user.data 
 * 获取表中行数据:tables.user.row(i)
*/
var tables = {
		user:null
};

//页面加载完成后执行
$(document).ready(function(){
	//页面初始化,
	if(tables.user==null){
		view.init();
	}else{
		tables.user._fnReDraw();
	}
	//获取角色类型
	$.ajax({
        type: "POST",
        contentType: "application/json;utf-8",
        dataType: "json",
        url:$.urlRoot+"/platform/roleAction!listRole.action?type=2",
        success: function (result) {
        	var html="" ;
        	$("#roleType").empty();
        	for ( var i = 0; i < result.length; i++) {//动态加载角色
				var r = result[i];
				html += "<option value=" + r.id + ">" + r.name + "</option>\r\n";
			}
            $("#roleType").append(html);
        }
	});
});

//页面内ui的控制
var view = {
		init:function(){
			//执行初始化用户列表    直接在ready中调用也是可以的，放于此处方便代码维护而已。
			tables.user = this.initUserTable();
			this.tableTool();
		},
		tableTool:function(){
			//删除
			$("#btn-delete").on("click.delete",function(){
				var idList = [];//被选中的用户
				var $userId = $("#table-user [name='slecteUser']:checked");
				if($userId.length>0){
					for(var i =0;i<$userId.length;i++){
						idList.push($userId.eq(i).data("uid"));
					}
					$.W.alert("确定删除"+idList.length+"条记录？",function(){
						//console.log(idList);
						//ajax提交删除
						$.ajax({
			        		url:$.urlRoot+"/platform/accountAction!deleteUsers.action",
			        		type:"post",
			        		dataType:"json",
			        		data:{ids:idList.toString()},
			        		success:function(d){
			        			$.W.alert(d.msg,true);
			        			//删除后刷新表格
								tables.user._fnReDraw();
			        		}
			        	});
					});
				}else{
					$.W.alert("请选择要删除的记录！",true);
				}
			});
			
			//提交新增用户的表单
			$("#btn-addUser").off('click.save').on("click.save",function(){
				$.ajax({
	        		url:$.urlRoot+"/platform/accountAction!addManual.action",
	        		type:"post",
	        		dataType:"json",
	        		data:{
	        			"account.userName":$("#username").val(),
	        			"account.password":$("#password").val(),
	        			"account.nickName":$("#nickName").val(),
	        			"account.email":$("#email").val(),
	        			"account.tel":$("#tel").val(),
	        			"account.roleId":$("#roleType").val()
	        			},
	        		success:function(d){
	        			$.W.alert(d.msg,true);
	        			//添加后刷新表格
						tables.user._fnReDraw();
	        		}
	        	});
			});
		},
		/*
		 * 初始化用户列表
		 * return datatable 对象
		 */		
		initUserTable:function(){
			return $("#table-user").dataTable({
				"columns":[//定义要显示的列名
							{ data: 'id',sTitle:"",
					        	render: function(id) {
									var str = "<input name='slecteUser' data-uid='"+id+"' type=checkbox>";
									return str;
					        	}
							},
							{data : 'id',sTitle : "ID"}, 
							{data : 'userName',sTitle : "账号"}, 
							{data : 'realName',sTitle : "真实姓名"}, 
							{data : 'nickName',sTitle : "昵称"}, 
							{data : 'email',sTitle : "邮箱"}, 
							{data : 'tel',sTitle : "手机号码"},
							{data : 'registerTime',sTitle : "注册时间"},
							{data : 'source',sTitle : "来源"},
							{data : 'lastLoginTime',sTitle : "最后一次登录时间"}
						],
				"order": [[ 2, 'asc' ]],
				"processing": true,
		        "serverSide": true,
		        "bAutoWidth": false,//自适应宽度
		        "aLengthMenu": [10, 20, 30, 50],//定义每页显示数据数量
		        "fnServerData":function(n,params,fnCallback,table){//向后台请求列表数据
		        	//alert(JSON.stringify(params));
		        	params.push({name:"sSearch",value:params[5].value.value});
		        	$.ajax({
		        		url:$.urlRoot+"/platform/accountAction!listUsers.action",
		        		type:"post",
		        		dataType:"json",
		        		data:{dataTableParams:JSON.stringify(params)},
		        		success:function(d){
		        			fnCallback(d.msg);
		        		}
		        	});
		        },
				"sort": false, 
				"language": {
					"search":"快速搜索",                    //汉化   
				    "lengthMenu": "每页显示 _MENU_ 条记录",   
				    "zeroRecords": "没有查询到数据",
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

