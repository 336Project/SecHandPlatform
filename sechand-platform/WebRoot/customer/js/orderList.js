/*
 * 全局对象-表格 完成初始化后方可使用
 * 获取方式:tables.user or tables.role
 * 获取表中数据:tables.user.data 
 * 获取表中行数据:tables.user.row(i)
*/
var tables = {
		order:null
};

//页面加载完成后执行
$(document).ready(function(){
	//页面初始化,
	if(tables.order==null){
		view.init();
	}else{
		tables.order.draw();
	}
});

//页面内ui的控制
var view = {
		init:function(){
			//执行初始化用户列表    直接在ready中调用也是可以的，放于此处方便代码维护而已。
			tables.order = this.initOrderTable();
			this.tableTool();
			//报修的表单验证
			$("#addRepairForm").validate({
		        onkeyup: false,
		        errorClass: 'error',
		        validClass: 'valid',
		        highlight: function(element) {
		            $(element).closest('div').addClass("f-error");
		        },
		        unhighlight: function(element) {
		            $(element).closest('div').removeClass("f-error");
		        },
		        errorPlacement: function(error, element) {
		            $(element).closest('div').append(error);
		        },
		        rules: {
		        	repairContent:{required: true ,maxlength:300},
		            contactTelUser:{required: true,digits:true,minlength: 11,maxlength:11},/*最大最小都是11则只能输入11位的电话号码，digits:只能是整数  */
		            companyId:{required: true}
		        },
		        messages:{
		        	repairContent:{required: "报修内容不能为空",maxlength:"报修内容最多300个字符"},
		            contactTelUser:{required: "电话不能为空",minlength: "请输入正确格式的电话号码",maxlength:"请输入正确格式的电话号码",digits:"请输入正确格式的电话号码"},
		            companyId:{required: "请选择一个维修公司"}
		        }
		   });
			//修改报修的表单验证
			$("#updateRepairForm").validate({
		        onkeyup: false,
		        errorClass: 'error',
		        validClass: 'valid',
		        highlight: function(element) {
		            $(element).closest('div').addClass("f-error");
		        },
		        unhighlight: function(element) {
		            $(element).closest('div').removeClass("f-error");
		        },
		        errorPlacement: function(error, element) {
		            $(element).closest('div').append(error);
		        },
		        rules: {
		        	repairContent:{required: true ,maxlength:300},
		            contactTelUser:{required: true,digits:true,minlength: 11,maxlength:11},/*最大最小都是11则只能输入11位的电话号码，digits:只能是整数  */
		            companyId:{required: true}
		        },
		        messages:{
		        	repairContent:{required: "报修内容不能为空",maxlength:"报修内容最多300个字符"},
		            contactTelUser:{required: "电话不能为空",minlength: "请输入正确格式的电话号码",maxlength:"请输入正确格式的电话号码",digits:"请输入正确格式的电话号码"},
		            companyId:{required: "请选择一个维修公司"}
		        }
		   });
		},
		tableTool:function(){
			//取消订单
			$("#btn-cancel").on("click.delete",function(){
				var $orderId = $("#table-order [name='slecteOrder']:checked");
				if($orderId.length>0){
					if($orderId.length>1){//避免还要解决并发问题
						$.W.alert("一次只能取消一条记录！",true);
					}else{
						$.W.alert("确定取消该条订单？",true,function(){
							//console.log(idList);
							$.ajax({
				        		url:$.urlRoot+"/platform/orderAction!cancelOrderById.action",
				        		type:"post",
				        		dataType:"json",
				        		data:{ids:$orderId.eq(0).data("uid")},
				        		success:function(d){
				        			$.W.alert(d.msg,true);
				        			//取消后刷新表格
				        			if(d.success){
				        				tables.order.draw();
				        			}
				        		}
				        	});
						});
					}
				}else{
					$.W.alert("请选中要取消的订单！",true);
				}
			});
			//订单确认
			$("#btn-confirm").on("click.delete",function(){
				var $ids = $("#table-order [name='slecteOrder']:checked");
				if($ids.length>0){
					if($ids.length>1){//避免还要解决并发问题
						$.W.alert("一次只能确认一条记录！",true);
					}else{
						$.W.alert("确认之后，将从账户中扣去维修费用",false,function(){
							$.ajax({
				        		url:$.urlRoot+"/platform/orderAction!confirmOrderById.action",
				        		type:"post",
				        		dataType:"json",
				        		data:{ids:$ids.eq(0).data("uid")},
				        		success:function(d){
				        			$.W.alert(d.msg,true);
				        			//确认后刷新表格
				        			if(d.success){
				        				tables.order.draw();
				        			}
				        		}
				        	});
						});
					}
				}else{
					$.W.alert("请选中要确认的记录！",true);
				}
			});
			//点击报修按钮时，加载维修公司下拉框
			$("#btn-modal-repair").click(function(){
				$.ajax({
			        type: "POST",
			        contentType: "application/json;utf-8",
			        dataType: "json",
			        url:$.urlRoot+"/platform/userAction!listCompany.action",
			        success: function (d) {
			        	var data=[];
    		            var result=d.msg;
    		            for ( var i = 0; i < result.length; i++) {//动态加载
    						var obj=new Object();
    						obj.id=result[i].id;
    						obj.text=result[i].nickName;
    						data.push(obj);
    					}
    		            console.log(data);
    		            $("#companyId").select2({
    					  placeholder: "请选择维修公司",
    					  data:data
    					}); 
			        	/*var html="" ;
			        	var result=d.msg;
			        	$("#companyId").empty();
			        	for ( var i = 0; i < result.length; i++) {//动态加载公司
							var r = result[i];
							html += "<option value=" + r.id + ">" + r.nickName + "</option>\r\n";
						}
			            $("#companyId").append(html);*/
			        }
				});
				//重置表单,ps:form元素才有reset
    			$("#addRepair").find("form")[0].reset();
				$("#addRepair").modal("show");
			});
			//提交报修的表单
			$("#btn-addRepair").off('click.save').on("click.save",function(){
				if($("#addRepairForm").valid()){
					$.ajax({
		        		url:$.urlRoot+"/platform/orderAction!repairByCustomer.action",
		        		type:"post",
		        		dataType:"json",
		        		data:{
		        				"order.userId":$("#addRepair").find("[name=userId]").val(),
		        				"order.customerUser":$("#addRepair").find("[name=customerUser]").val(),
		        				"order.repairContent":$("#addRepair").find("[name=repairContent]").val(),
		        				"order.contactTelUser":$("#addRepair").find("[name=contactTelUser]").val(),
		        				"order.companyId":$("#addRepair").find("[name=companyId]").val()
		        		},
		        		success:function(d){
		        			$("#addRepair").modal('hide');
		        			$.W.alert(d.msg,true);
		        			//添加后刷新表格
		        			if(d.success){
		        				tables.order.draw();
		        			}
		        		}
		        	});
				}
			});
			
			
			//为修改的表单赋值
			$("#btn-modal-updateOrder").click(function(){
				//选中的行
				//获取到该行订单的所有信息
				var $tr = $("#table-order [name='slecteOrder']:checked").parent().parent();
				var order = tables.order.row($tr.eq(0)).data();
				if($tr.length>1){
					$.W.alert("不能同时编辑多条记录!",true);
				}else if($tr.length<=0){
					$.W.alert("请先选中行再点击修改!",true);
				}else{
					if(order.status=="新订单"){
						//将订单信息填充到表单上
						$("#update-repairContent").val(order.repairContent);
						$("#update-contactTelUser").val(order.contactTelUser);
						$.ajax({
					        type: "POST",
					        contentType: "application/json;utf-8",
					        dataType: "json",
					        url:$.urlRoot+"/platform/userAction!listCompany.action",
					        success: function (d) {
					        	var data=[];
		    		            var result=d.msg;
		    		            for ( var i = 0; i < result.length; i++) {//动态加载
		    						var obj=new Object();
		    						obj.id=result[i].id;
		    						obj.text=result[i].nickName;
		    						data.push(obj);
		    					}
		    		            console.log(data);
		    		            $("#update-companyId").select2({
		    					  placeholder: "选择用户名",
		    					  data:data
		    					}); 
					        	/*var html="" ;
					        	var result=d.msg;
					        	$("#update-companyId").empty();
					        	for ( var i = 0; i < result.length; i++) {//动态加载公司
									var r = result[i];
									if(order.userId==r.id){
										html += "<option selected='selected' value=" + r.id + ">" + r.nickName + "</option>\r\n";
									}else{
										html += "<option value=" + r.id + ">" + r.nickName + "</option>\r\n";
									}
								}
					            $("#update-companyId").append(html);*/
					        }
						});
						$("#updateRepair").modal("show");
					}else{
						$.W.alert("只有新订单才能修改!",true);
					}
				}
			});
			
			//提交修改订单的表单
			$("#btn-updateRepair").off('click.save').on("click.save",function(){
				if($("#updateRepairForm").valid()){
					var id = $("#table-order [name='slecteOrder']:checked").eq(0).data("uid");
					$.ajax({
		        		url:$.urlRoot+"/platform/orderAction!updateByCustomer.action",
		        		type:"post",
		        		dataType:"json",
		        		data:{
		        				"order.id":id ,//被修改的订单的id
		        				"order.userId":$("#updateRepair").find("[name=userId]").val(),
		        				"order.repairContent":$("#updateRepair").find("[name=repairContent]").val(),
		        				"order.contactTelUser":$("#updateRepair").find("[name=contactTelUser]").val(),
		        				"order.companyId":$("#updateRepair").find("[name=companyId]").val()
		        		},
		        		success:function(d){
		        			$("#updateRepair").modal('hide');
		        			$.W.alert(d.msg,true);
		        			//添加后刷新表格
		        			if(d.success){
		        				tables.order.draw();
		        			}
		        		}
		        	});
				}
			});
			
			//点击行选中或取消选中用户行
			$("#table-order").on("click.select","tr",function(){
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
		initOrderTable:function(){
			return $("#table-order").DataTable({
				"columns":[//定义要显示的列名
							{ data: 'id',sTitle:"",
								render: function(id) {
					        		var cell = arguments[3];
					        		var index = (cell.settings._iDisplayStart+cell.row+1);
									var str = "<input class='tcheckbox' id='d"+index+"' name='slecteOrder' data-uid='"+id+"' type=checkbox> "
									   +"<label for='d"+index+"'>"+index+"</label>";
									return str;
					        	}
							},
							{data : 'contactTelUser',sTitle : "我的联系电话"},
							{data : 'customerCompany',sTitle : "维修公司名称"},
							{data : 'contactTelCompany',sTitle : "公司联系电话"},
							{data : 'createTime',sTitle : "创建时间"}, 
							{data : 'quoteTime',sTitle : "报价时间"},
							{data : 'completeTime',sTitle : "完成时间"},
							{data : 'price',sTitle : "公司报价(元)"},
							{data : 'repairContent',sTitle : "报修内容"},
							{data : 'status',sTitle : "状态"}
						],
				"order": [[ 1, 'asc' ]],
				"scrollX": true,//水平滚动条
				"scrollXInner":"120%",
				"processing": true,
		        "serverSide": true,
		        "bAutoWidth": false,//自适应宽度
		        "aLengthMenu": [5,10, 20, 30, 50],//定义每页显示数据数量
		        "fnServerData":function(n,params,fnCallback,table){//向后台请求列表数据
		        	//alert(JSON.stringify(params));
		        	params.push({name:"sSearch",value:params[5].value.value});
		        	$.ajax({
		        		url:$.urlRoot+"/platform/orderAction!listCustomerOrdersByParams.action",
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

