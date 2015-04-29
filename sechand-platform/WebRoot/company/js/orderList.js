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
			//报价的表单验证
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
		        	contactTelCompany:{required: true,digits:true,minlength: 11,maxlength:11},/*最大最小都是11则只能输入11位的电话号码，digits:只能是整数  */
		        	price:{required: true,number:true}
		        },
		        messages:{
		        	contactTelCompany:{required: "电话不能为空",minlength: "请输入正确格式的电话号码",maxlength:"请输入正确格式的电话号码",digits:"请输入正确格式的电话号码"},
		            price:{required: "报价金额不能为空",number:"请输入正确的金额格式"}
		        }
		   });
		},
		tableTool:function(){
			//为派遣的表单赋值
			$("#btn-modal-addRepair").click(function(){
				//选中的行
				//获取到该行订单的所有信息
				var $tr = $("#table-order [name='slecteOrder']:checked").parent().parent();
				var order = tables.order.row($tr.eq(0)).data();
				if($tr.length>1){
					$.W.alert("不能同时操作多条记录!",true);
				}else if($tr.length<=0){
					$.W.alert("请先选中行再操作!",true);
				}else{
					if(order.status=="新订单"){
						//将订单信息填充到表单上
						$("#add-repairContent").val(order.repairContent);
						$("#add-contactTelUser").val(order.contactTelUser);
						$("#add-address").val(order.address);
						$("#add-price").val("");
						$.ajax({
		    		        type: "POST",
		    		        contentType: "application/json;utf-8",
		    		        dataType: "json",
		    		        url:$.urlRoot+"/platform/userAction!listAllUsers.action",
		    		        success: function (d) {
		    		        	 //请将返回值的格式设置为[{id: "这里等于value", text: '这是text' },{id: "admin", text: '管理员'}]
		    		            var data=[];
		    		            var result=d.msg;
		    		            for ( var i = 0; i < result.length; i++) {//动态加载维修人员
		    		            	if(result[i].roleCode=='4'&result[i].parentId==order.companyId){
			    						var obj=new Object();
			    						obj.id=result[i].id;
			    						obj.text=result[i].realName;
			    						data.push(obj);
		    		            	}
		    					}
		    		            console.log(data);
		    		            $.fn.modal.Constructor.prototype.enforceFocus = function() {}; 
		    		            $("#addRepair").find("[name=userName]").select2({
		    					  placeholder: "选择用户名",
		    					  data:data
		    					}); 
		    		        }
		    			});
						$("#addRepair").modal("show");
					}else{
						$.W.alert("只有新订单才能派遣!",true);
					}
				}
			});
			//提交派遣的表单
			$("#btn-addRepair").off('click.save').on("click.save",function(){
				if($("#addRepairForm").valid()){
					var id = $("#table-order [name='slecteOrder']:checked").eq(0).data("uid");
					$.ajax({
		        		url:$.urlRoot+"/platform/orderAction!dispatch.action",
		        		type:"post",
		        		dataType:"json",
		        		data:{
		        				"order.id":id ,//被修改的订单的id
		        				"order.contactTelCompany":$("#addRepair").find("[name=contactTelCompany]").val(),
		        				id:$("#addRepair").find("[name=userName]").val()
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
			//为报价的表单赋值
			$("#btn-modal-updateOrder").click(function(){
				//选中的行
				//获取到该行订单的所有信息
				var $tr = $("#table-order [name='slecteOrder']:checked").parent().parent();
				var order = tables.order.row($tr.eq(0)).data();
				if($tr.length>1){
					$.W.alert("不能同时报价多条记录!",true);
				}else if($tr.length<=0){
					$.W.alert("请先选中行再点击报价!",true);
				}else{
					if(order.status=="新订单"||order.status=="工人已到"){
						//将订单信息填充到表单上
						$("#update-repairContent").val(order.repairContent);
						$("#update-contactTelUser").val(order.contactTelUser);
						$("#update-address").val(order.address);
						$("#update-price").val("");
						$("#update-quoteContent").val("");
						$("#updateRepair").modal("show");
					}else{
						$.W.alert("只有新订单或工人已到才能报价!",true);
					}
				}
			});
			
			//提交报价订单的表单
			$("#btn-updateRepair").off('click.save').on("click.save",function(){
				if($("#updateRepairForm").valid()){
					var id = $("#table-order [name='slecteOrder']:checked").eq(0).data("uid");
					$.ajax({
		        		url:$.urlRoot+"/platform/orderAction!quoteOrderByCompany.action",
		        		type:"post",
		        		dataType:"json",
		        		data:{
		        				"order.id":id ,//被修改的订单的id
		        				"order.price":$("#updateRepair").find("[name=price]").val(),
		        				"order.quoteContent":$("#updateRepair").find("[name=quoteContent]").val(),
		        				"order.contactTelCompany":$("#updateRepair").find("[name=contactTelCompany]").val(),
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
			
			//订单完成
			$("#btn-complete").on("click.delete",function(){
				var $tr = $("#table-order [name='slecteOrder']:checked").parent().parent();
				var order = tables.order.row($tr.eq(0)).data();
				if(order.status=="已报价"){
					if($tr.length>0){
						if($tr.length>1){//避免还要解决并发问题
							$.W.alert("一次只能完成一条记录！",true);
						}else{
							$.W.alert("完成之后，等待客户确认后，费用才会到账!",true,function(){
								$.ajax({
					        		url:$.urlRoot+"/platform/orderAction!completeOrderById.action",
					        		type:"post",
					        		dataType:"json",
					        		data:{ids:order.id},
					        		success:function(d){
					        			$.W.alert(d.msg,true);
					        			//完成后刷新表格
					        			if(d.success){
					        				tables.order.draw();
					        			}
					        		}
					        	});
							});
						}
					}else{
						$.W.alert("请选中要完成的记录！",true);
					}
				}else{
					$.W.alert("只有已报价的订单才能完成！",true);
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
							{data : 'customerUser',sTitle : "客户名称"},
							{data : 'contactTelUser',sTitle : "客户联系电话"},
							{data : 'address',sTitle : "地址"},
							{data : 'repairMan',sTitle : "维修人员信息"}, 
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

