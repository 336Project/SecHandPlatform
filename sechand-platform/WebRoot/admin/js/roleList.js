/*
 * 全局对象-表格 完成初始化后方可使用
 * 获取方式:tables.user or tables.role
 * 获取表中数据:tables.user.data
 * 获取表中行数据:tables.user.row(i)
*/
var tables = {
		roles:null
};

//页面加载完成后执行
$(document).ready(function(){
	//页面初始化,
	view.init();
	
});

//页面内ui的控制
var view = {
		init:function(){
			//执行初始化角色列表    直接在ready中调用也是可以的，放于此处方便代码维护而已。
			tables.roles = this.initRoleTable();
			this.tableTool();
			//添加的表单验证
			$("#updateUserForm").validate({
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
		        	roleName:{required: true},
		            roleCode:{required: true,digits:true,range:[1,2,3]}
		        },
		        messages:{
		        	roleName:{required: "角色名称不能为空"},
		            roleCode:{required: "角色编号不能为空",digits:"编号只能为正整数",range:"1:管理员2:维修公司3:普通用户"},
		        }
		   });
		},
		tableTool:function(){
			//点击行选中或取消选中用户行
			$("#table-role").on("click.select","tr",function(){
		    	var $check = $(this).find(".tcheckbox");
		    	if($check.prop("checked")){
		    		$check.prop("checked",false);
		    		$(this).removeClass("selected");
		    	}else{
		    		$check.prop("checked",true);
		    		$(this).addClass("selected");
		    	}
		    });
			//删除
			$("#btn-delete").on("click.delete",function(){
				var idList = [];//被选中的用户
				var $userId = $("#table-role [name='slecteRole']:checked");
				if($userId.length>0){
					for(var i =0;i<$userId.length;i++){
						idList.push($userId.eq(i).data("uid"));
					}
					$.W.alert("确定删除"+idList.length+"条记录？",function(){
						//console.log(idList);
						//ajax提交删除
						$.ajax({
			        		url:$.urlRoot+"/platform/roleAction!deleteRoleByIds.action",
			        		type:"post",
			        		dataType:"json",
			        		data:{ids:idList.toString()},
			        		success:function(d){
			        			$.W.alert(d.msg,true);
			        			//删除后刷新表格
			        			if(d.success){
			        				tables.roles._fnReDraw();
			        			}
			        		}
			        	});
					});
				}else{
					$.W.alert("请选择要删除的记录！",true);
				}
			});
			
			//提交新增角色的表单
			$("#btn-addRole").off('click.save').on("click.save",function(){
				if($("#addRoleForm").valid()){
					$.ajax({
		        		url:$.urlRoot+"/platform/roleAction!addRole.action",
		        		type:"post",
		        		dataType:"json",
		        		data:{
		        			"role.name":$("#roleName").val(),
		        			"role.code":$("#roleCode").val()
		        			},
		        		success:function(d){
		        			$.W.alert(d.msg,true);
		        			//添加后刷新表格
		        			if(d.success){
		        				tables.roles._fnReDraw();
		        			}
		        			//重置表单,ps:form元素才有reset
		        			$("#addRole").find("form")[0].reset();
		        		}
		        	});
				}
			});
		},
		/*
		 * 初始化用户列表
		 * return datatable 对象
		 */		
		initRoleTable:function(){
			return $("#table-role").dataTable({
				"columns":[
							{ data: 'id',sTitle:"",
								render: function(id) {
					        		var cell = arguments[3];
					        		var index = (cell.settings._iDisplayStart+cell.row+1);
									var str = "<input class='tcheckbox' id='d"+index+"' name='slecteRole' data-uid='"+id+"' type=checkbox> "
									   +"<label for='d"+index+"'>"+index+"</label>";
									return str;
					        	}
							},
							{data : 'name',sTitle : "角色名称"}, 
							{data : 'code',sTitle : "类型"}
						],
				"scrollX": true,//水平滚动条
				"scrollXInner":"100%",
				"processing": true,
		        "serverSide": true,
		        "bAutoWidth": false,//自适应宽度
		        "aLengthMenu": [5,10, 20, 30, 50],//定义每页显示数据数量
		        "fnServerData":function(n,params,fnCallback,table){
		        	params.push({name:"sSearch",value:params[5].value.value});
		        	$.ajax({
		        		url:$.urlRoot+"/platform/roleAction!listRoleByParams.action",
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

