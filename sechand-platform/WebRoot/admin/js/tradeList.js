/*
 * 全局对象-表格 完成初始化后方可使用
 * 获取方式:tables.trade or tables.role
 * 获取表中数据:tables.trade.data 
 * 获取表中行数据:tables.trade.row(i)
*/
var tables = {
		trade:null
};

//页面加载完成后执行
$(document).ready(function(){
	//页面初始化,
	if(tables.trade==null){
		view.init();
	}else{
		tables.trade.draw();
	}
});

//页面内ui的控制
var view = {
		init:function(){
			//执行初始化用户列表    直接在ready中调用也是可以的，放于此处方便代码维护而已。
			tables.trade = this.initTradeTable();
			this.tableTool();
		},
		tableTool:function(){
			//删除
			$("#btn-delete").on("click.delete",function(){
				var idList = [];//被选中的用户
				var $Ids = $("#table-trade [name='slecteTrade']:checked");
				if($Ids.length>0){
					for(var i =0;i<$Ids.length;i++){
						idList.push($Ids.eq(i).data("uid"));
					}
					$.W.alert("确定删除"+idList.length+"条记录？",true,function(){
						//console.log(idList);
						//ajax提交删除
						$.ajax({
			        		url:$.urlRoot+"/platform/tradeAction!deleteTradeByIds.action",
			        		type:"post",
			        		dataType:"json",
			        		data:{ids:idList.toString()},
			        		success:function(d){
			        			$.W.alert(d.msg,true);
			        			//删除后刷新表格
			        			if(d.success){
			        				tables.trade.draw();
			        			}
			        		}
			        	});
					});
				}else{
					$.W.alert("请选中要删除的记录！",true);
				}
			});
			//查看详情
			$("#btn-modal-lookTrade").click(function(){
				//选中的行
				var $tr = $("#table-trade [name='slecteTrade']:checked").parent().parent();
				if($tr.length>1){
					$.W.alert("不能同时查看多个记录!",true);
				}else if($tr.length<=0){
					$.W.alert("请选中要查看的记录!",true);
				}else{
					//获取到该行用户的所有信息
					var trade = tables.trade.row($tr.eq(0)).data();
					//将用户信息填充到表单上
					$("#look-fromUserName").val(trade.fromUserName);
					$("#look-fromUserNickName").val(trade.fromUserNickName);
					$("#look-toUserName").val(trade.toUserName);
					$("#look-toUserNickName").val(trade.toUserNickName);
					$("#look-money").val(trade.money);
					$("#look-time").val(trade.time);
					$("#look-status").val(trade.status);
					$("#lookTrade").modal("show");
				}
			});
			
			//点击行选中或取消选中用户行
			$("#table-trade").on("click.select","tr",function(){
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
		initTradeTable:function(){
			return $("#table-trade").DataTable({
				"columns":[//定义要显示的列名
							{ data: 'id',sTitle:"",
								render: function(id) {
					        		var cell = arguments[3];
					        		var index = (cell.settings._iDisplayStart+cell.row+1);
									var str = "<input class='tcheckbox' id='d"+index+"' name='slecteTrade' data-uid='"+id+"' type=checkbox> "
									   +"<label for='d"+index+"'>"+index+"</label>";
									return str;
					        	}
							},
							{data : 'fromUserName',sTitle : "用户账号(流出)"},
							{data : 'fromUserNickName',sTitle : "用户名称(流出)"},
							{data : 'toUserName',sTitle : "用户账号(流进)"}, 
							{data : 'toUserNickName',sTitle : "用户名称(流进)"},
							{data : 'money',sTitle : "交易金额"}, 
							{data : 'time',sTitle : "交易时间"},
							{data : 'status',sTitle : "状态"}
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
		        		url:$.urlRoot+"/platform/tradeAction!listTradesByParams.action",
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

