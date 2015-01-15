/*
 * 全局对象-表格 完成初始化后方可使用
 * 获取方式:tables.account or tables.role
 * 获取表中数据:tables.account.data 
 * 获取表中行数据:tables.account.row(i)
*/
var tables = {
		account:null
};

//页面加载完成后执行
$(document).ready(function(){
	//页面初始化,
	if(tables.account==null){
		view.init();
	}else{
		tables.account.draw();
	}
});

//页面内ui的控制
var view = {
		init:function(){
			//执行初始化用户列表    直接在ready中调用也是可以的，放于此处方便代码维护而已。
			tables.account = this.initAccountTable();
			this.tableTool();
		},
		tableTool:function(){
			//充值确认
			$("#btn-comfirm").on("click.delete",function(){
				var idList = [];//被选中的用户
				var $userId = $("#table-account [name='slecteAccount']:checked");
				if($userId.length>0){
					if($userId.length>1){//避免还要解决并发问题
						$.W.alert("一次只能确认一条记录！",true);
					}else{
						$.ajax({
			        		url:$.urlRoot+"/platform/accountAction!confirmAccount.action",
			        		type:"post",
			        		dataType:"json",
			        		data:{ids:$userId.eq(0).data("uid")},
			        		success:function(d){
			        			$.W.alert(d.msg,true);
			        			//确认后刷新表格
			        			if(d.success){
			        				tables.account.draw();
			        			}
			        		}
			        	});
					}
				}else{
					$.W.alert("请选中要确认的记录！",true);
				}
			});
			//重置密码
			$("#btn-reset-password").on("click.delete",function(){
				var $userId = $("#table-account [name='slecteUser']:checked");
				if($userId.length>0){
					if($userId.length>1){
						$.W.alert("一次只能重置一个用户的密码！",true);
					}else{
						$.W.alert("确定要重置密码吗？",function(){
							$.ajax({
				        		url:$.urlRoot+"/platform/accountAction!resetPassword.action",
				        		type:"post",
				        		dataType:"json",
				        		data:{ids:$userId.eq(0).data("uid")},
				        		success:function(d){
				        			$.W.alert(d.msg,true);
				        			//重置后刷新表格
				        			if(d.success){
				        				tables.account.draw();
				        			}
				        		}
				        	});
						});
					}
				}else{
					$.W.alert("请选中要重置密码的用户！",true);
				}
			});
			//点击新增按钮时，加载角色类型下拉框
			$("#btn-modal-addAccount").click(function(){
				$.ajax({
			        type: "POST",
			        contentType: "application/json;utf-8",
			        dataType: "json",
			        url:$.urlRoot+"/platform/userAction!listAllUsers.action",
			        success: function (d) {
			        	var html="" ;
			        	var result=[];
			        	if(d.success){
			        		result=d.msg;
			        	}
			        	$("#userName").empty();
			        	for ( var i = 0; i < result.length; i++) {//动态加载角色
							var r = result[i];
							html += "<option value=" + r.id + ">" + r.nickName + "</option>\r\n";
						}
			            $("#userName").append(html);
			        }
				});
				//重置表单,ps:form元素才有reset
    			$("#addAccount").find("form")[0].reset();
				$("#addAccount").modal("show");
			});
			//提交新增用户的表单
			$("#btn-addAccount").off('click.save').on("click.save",function(){
				$.ajax({
	        		url:$.urlRoot+"/platform/accountAction!recharge.action",
	        		type:"post",
	        		dataType:"json",
	        		data:{
	        				"account.userId":$("#addAccount").find("[name=userName]").val(),
	        				"account.money":$("#addAccount").find("[name=money]").val(),
	        				"account.remark":$("#addAccount").find("[name=remark]").val()
	        		},
	        		success:function(d){
	        			$.W.alert(d.msg,true);
	        			//添加后刷新表格
	        			if(d.success){
	        				tables.account.draw();
	        			}
	        		}
	        	});
				
			});
			
			//为修改的表单赋值
			$("#btn-modal-updateuser").click(function(){
				//选中的行
				var $tr = $("#table-account [name='slecteUser']:checked").parent().parent();
				if($tr.length>1){
					$.W.alert("不能同时编辑多个用户!",true);
				}else if($tr.length<=0){
					$.W.alert("请先选中行再点击修改!",true);
				}else{
					//获取到该行用户的所有信息
					var account = tables.account.row($tr.eq(0)).data();
					//将用户信息填充到表单上
					$("#update-username").val(account.userName);
					$("#update-nickName").val(account.nickName);
					$("#update-realName").val(account.realName);
					$("#update-email").val(account.email);
					$("#update-tel").val(account.tel);
					$("#update-roleName").val(account.roleName);
					$("#updateUser").modal("show");
				}
			});
			//提交修改用户的表单
			$("#btn-updateUser").off('click.save').on("click.save",function(){
				var userId = $("#table-account [name='slecteUser']:checked").eq(0).data("uid");
				$.ajax({
	        		url:$.urlRoot+"/platform/accountAction!updateUser.action",
	        		type:"post",
	        		dataType:"json",
	        		data:{
	        				"user.id":userId,//被修改的用户的id
	        				"user.userName":$("#updateUser").find("[name=userName]").val(),
	        				"user.realName":$("#updateUser").find("[name=realName]").val(),
	        				"user.nickName":$("#updateUser").find("[name=nickName]").val(),
	        				"user.email":$("#updateUser").find("[name=email]").val(),
	        				"user.tel":$("#updateUser").find("[name=tel]").val()
	        		},
	        		success:function(d){
	        			$.W.alert(d.msg,true);
	        			//添加后刷新表格
	        			if(d.success){
	        				tables.account.draw();
	        			}
	        		}
	        	});
			});
			//点击行选中或取消选中用户行
			$("#table-account").on("click.select","tr",function(){
		    	var $check = $(this).find(".tcheckbox");
		    	
		    	if($check.prop("checked")){
		    		$check.prop("checked",false);
		    		$(this).removeClass("selected");
		    	}else{
		    		$check.prop("checked",true);
		    		$(this).addClass("selected");
		    	}
		    });
			
		},//end tableTool
		
		/*
		 * 初始化用户列表
		 * return datatable 对象
		 */		
		initAccountTable:function(){
			return $("#table-account").DataTable({
				"columns":[//定义要显示的列名
							{ data: 'id',sTitle:"",
								render: function(id) {
					        		var cell = arguments[3];
					        		var index = (cell.settings._iDisplayStart+cell.row+1);
									var str = "<input class='tcheckbox' id='d"+index+"' name='slecteAccount' data-uid='"+id+"' type=checkbox> "
									   +"<label for='d"+index+"'>"+index+"</label>";
									return str;
					        	}
							},
							{data : 'userName',sTitle : "用户账号"}, 
							{data : 'nickName',sTitle : "用户名称"},
							{data : 'createTime',sTitle : "创建时间"}, 
							{data : 'completeTime',sTitle : "完成时间"}, 
							{data : 'money',sTitle : "交易金额(元)"}, 
							{data : 'type',sTitle : "交易类型"}, 
							{data : 'status',sTitle : "状态"},
							{data : 'remark',sTitle : "备注"}
						],
				"order": [[ 2, 'asc' ]],
				"scrollX": true,//水平滚动条
				"scrollXInner":"110%",
				"processing": true,
		        "serverSide": true,
		        "bAutoWidth": false,//自适应宽度
		        "aLengthMenu": [5,10, 20, 30, 50],//定义每页显示数据数量
		        "fnServerData":function(n,params,fnCallback,table){//向后台请求列表数据
		        	//alert(JSON.stringify(params));
		        	params.push({name:"sSearch",value:params[5].value.value});
		        	$.ajax({
		        		url:$.urlRoot+"/platform/accountAction!listAccountsByParams.action",
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
				    "zeroRecords": "没有查询到相关数据",
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

