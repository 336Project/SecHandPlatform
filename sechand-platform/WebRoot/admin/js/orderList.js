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
		},
		tableTool:function(){
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
									var str = "<input class='tcheckbox' id='d"+index+"' name='slecteUser' data-uid='"+id+"' type=checkbox> "
									   +"<label for='d"+index+"'>"+index+"</label>";
									return str;
					        	}
							},
							{data : 'customerUser',sTitle : "用户账号"}, 
							{data : 'customerCompany',sTitle : "公司账号"}, 
							{data : 'createTime',sTitle : "创建时间"}, 
							{data : 'completeTime',sTitle : "完成时间"}, 
							{data : 'repairContent',sTitle : "报修内容"}, 
							{data : 'status',sTitle : "状态"}
						],
				"order": [[ 1, 'asc' ]],
				/*"scrollX": true,//水平滚动条
				"scrollXInner":"120%",*/
				"processing": true,
		        "serverSide": true,
		        "bAutoWidth": false,//自适应宽度
		        "aLengthMenu": [10, 20, 30, 50],//定义每页显示数据数量
		        "fnServerData":function(n,params,fnCallback,table){//向后台请求列表数据
		        	//alert(JSON.stringify(params));
		        	params.push({name:"sSearch",value:params[5].value.value});
		        	$.ajax({
		        		url:$.urlRoot+"/platform/orderAction!listOrdersByParams.action",
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

