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
			//提现
			$("#btn-modal-pickup").on("click.delete",function(){
				$("#pickupAccount").find("form")[0].reset();
				$("#pickupAccount").modal("show");
			});
			//提交提现的表单
			$("#btn-pickupAccount").off('click.save').on("click.save",function(){
				var balance=$("#pickupAccount").find("[name=balance]").val();
				var money=$("#pickupAccount").find("[name=money]").val();
				$.ajax({
	        		url:$.urlRoot+"/platform/accountAction!applyPickup.action",
	        		type:"post",
	        		dataType:"json",
	        		data:{
	        				"account.userId":$("#pickupAccount").find("[name=userId]").val(),
	        				"account.balance":balance,
	        				"account.money":money
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
			
			//点击充值按钮时
			$("#btn-modal-addAccount").click(function(){
				//重置表单,ps:form元素才有reset
    			$("#addAccount").find("form")[0].reset();
				$("#addAccount").modal("show");
			});
			//提交充值的表单
			$("#btn-addAccount").off('click.save').on("click.save",function(){
				$.ajax({
	        		url:$.urlRoot+"/platform/accountAction!applyRecharge.action",
	        		type:"post",
	        		dataType:"json",
	        		data:{
	        				"account.userId":$("#addAccount").find("[name=userId]").val(),
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
							{data : 'balance',sTitle : "当前余额"},
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
		        		url:$.urlRoot+"/platform/accountAction!listAccountsByUser.action",
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

