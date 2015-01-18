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
		tables.user.draw();
	}
});

//页面内ui的控制
var view = {
		init:function(){
			//执行初始化用户列表    直接在ready中调用也是可以的，放于此处方便代码维护而已。
			tables.user = this.initUserTable();
			this.tableTool();
			//添加用户的表单验证
			$("#addUserForm").validate({
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
		            userName:{required: true ,minlength: 6},
		            password:{required: true ,minlength: 6,maxlength:20},
		            password2:{required: true ,equalTo: "#password"},
		            nickName:{required: true},
		            realName:{required: false},
		            email:{required: true,email:true },
		            tel:{required: true,digits:true,minlength: 11,maxlength:11},/*最大最小都是11则只能输入11位的电话号码，digits:只能是整数  */
		            introduction:{required: false},
		            roleCode:{required: true}
		        },
		        messages:{
		            userName:{required: "用户名不能为空",minlength:"用户名不能小于6个字符"},
		            password:{required: "密码不能为空" ,minlength:"密码不能小于6位数",maxlength:"密码最多20位数"},
		            password2:{required: "请输入相同的密码" ,equalTo: "请输入相同的密码"},
		            nickName:{required: "昵称不能为空"},
		            /* realName:{required: "真实姓名不能为空"}, */
		            email:{required: "邮箱不能为空",email:"请输入正确格式的邮箱地址"},
		            tel:{required: "电话不能为空",minlength: "请输入正确格式的电话号码",maxlength:"请输入正确格式的电话号码",digits:"请输入正确格式的电话号码"},
		            /* introduction:{required: "自我介绍不能为空"}, */
		            roleCode:{required: "请选择角色类型"}
		        }
		   });
			//修改用户的表单验证
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
		            nickName:{required: true},
		            realName:{required: false},
		            email:{required: true,email:true },
		            tel:{required: true,digits:true,minlength: 11,maxlength:11},/*最大最小都是11则只能输入11位的电话号码，digits:只能是整数  */
		        },
		        messages:{
		            nickName:{required: "昵称不能为空"},
		            email:{required: "邮箱不能为空",email:"请输入正确格式的邮箱地址"},
		            tel:{required: "电话不能为空",minlength: "请输入正确格式的电话号码",maxlength:"请输入正确格式的电话号码",digits:"请输入正确格式的电话号码"},
		        }
		   });
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
					$.W.alert("确定删除"+idList.length+"条记录？",true,function(){
						//console.log(idList);
						//ajax提交删除
						$.ajax({
			        		url:$.urlRoot+"/platform/userAction!deleteUserByIds.action",
			        		type:"post",
			        		dataType:"json",
			        		data:{ids:idList.toString()},
			        		success:function(d){
			        			$.W.alert(d.msg,true);
			        			//删除后刷新表格
			        			if(d.success){
			        				tables.user.draw();
			        			}
			        		}
			        	});
					});
				}else{
					$.W.alert("请选中要删除的用户！",true);
				}
			});
			//重置密码
			$("#btn-reset-password").on("click.delete",function(){
				var $userId = $("#table-user [name='slecteUser']:checked");
				if($userId.length>0){
					if($userId.length>1){
						$.W.alert("一次只能重置一个用户的密码！",true);
					}else{
						$.W.alert("确定要重置密码吗？",function(){
							$.ajax({
				        		url:$.urlRoot+"/platform/userAction!resetPassword.action",
				        		type:"post",
				        		dataType:"json",
				        		data:{ids:$userId.eq(0).data("uid")},
				        		success:function(d){
				        			$.W.alert(d.msg,true);
				        			//重置后刷新表格
				        			if(d.success){
				        				tables.user.draw();
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
			$("#btn-modal-adduser").click(function(){
				$.ajax({
			        type: "POST",
			        contentType: "application/json;utf-8",
			        dataType: "json",
			        url:$.urlRoot+"/platform/roleAction!listRole.action?type=1",
			        success: function (result) {
			        	var html="" ;
			        	$("#roleType").empty();
			        	for ( var i = 0; i < result.length; i++) {//动态加载角色
							var r = result[i];
							html += "<option value=" + r.code + ">" + r.name + "</option>\r\n";
						}
			            $("#roleType").append(html);
			        }
				});
				//重置表单,ps:form元素才有reset
    			$("#addUser").find("form")[0].reset();
				$("#addUser").modal("show");
			});
			//提交新增用户的表单
			$("#btn-addUser").off('click.save').on("click.save",function(){
				if($("#addUserForm").valid()){
					$.ajax({
		        		url:$.urlRoot+"/platform/userAction!addByManual.action",
		        		type:"post",
		        		dataType:"json",
		        		data:{
		        				"user.userName":$("#addUser").find("[name=userName]").val(),
		        				"user.realName":$("#addUser").find("[name=realName]").val(),
		        				"user.password":$("#addUser").find("[name=password]").val(),
		        				"user.nickName":$("#addUser").find("[name=nickName]").val(),
		        				"user.email":$("#addUser").find("[name=email]").val(),
		        				"user.tel":$("#addUser").find("[name=tel]").val(),
		        				"user.roleCode":$("#addUser").find("[name=roleCode]").val()
		        		},
		        		success:function(d){
		        			$.W.alert(d.msg,true);
		        			//添加后刷新表格
		        			if(d.success){
		        				tables.user.draw();
		        			}
		        		}
		        	});
				}
			});
			
			//为修改的表单赋值
			$("#btn-modal-updateuser").click(function(){
				//选中的行
				var $tr = $("#table-user [name='slecteUser']:checked").parent().parent();
				if($tr.length>1){
					$.W.alert("不能同时编辑多个用户!",true);
				}else if($tr.length<=0){
					$.W.alert("请先选中行再点击修改!",true);
				}else{
					//获取到该行用户的所有信息
					var account = tables.user.row($tr.eq(0)).data();
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
				if($("#updateUserForm").valid()){
					var userId = $("#table-user [name='slecteUser']:checked").eq(0).data("uid");
					$.ajax({
		        		url:$.urlRoot+"/platform/userAction!updateUser.action",
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
		        				tables.user.draw();
		        			}
		        		}
		        	});
				}
			});
			//点击行选中或取消选中用户行
			$("#table-user").on("click.select","tr",function(){
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
		initUserTable:function(){
			return $("#table-user").DataTable({
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
							{data : 'userName',sTitle : "账号"}, 
							{data : 'roleName',sTitle : "角色名称"}, 
							{data : 'realName',sTitle : "真实姓名"}, 
							{data : 'nickName',sTitle : "昵称"}, 
							{data : 'email',sTitle : "邮箱"}, 
							{data : 'tel',sTitle : "手机号码"},
							{data : 'balance',sTitle : "账户余额"},
							{data : 'registerTime',sTitle : "注册时间"},
							{data : 'source',sTitle : "来源"},
							{data : 'lastLoginTime',sTitle : "最后一次登录时间"},
							{data : 'introduction',sTitle : "简介"},
							{data : 'status',sTitle : "状态"},
							{data : 'status',sTitle:"操作",
					        	render: function(state) {
					        		var isChecked = (state == "正常")? "":"checked=checked";
					        		var str ='<input  type="checkbox" class="input-switch" '+isChecked+' />';
									return str;
					        	}
							}
						],
				"order": [[ 2, 'asc' ]],
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
		        		url:$.urlRoot+"/platform/userAction!listUsersByParams.action",
		        		type:"post",
		        		dataType:"json",
		        		data:{dataTableParams:JSON.stringify(params)},
		        		success:function(d){
		        			fnCallback(d.msg);
		        			$('.input-switch').bootstrapSwitch({
		        				onText:"启用",
		        				offText:"禁用",
		        				size:"mini",
		        				onSwitchChange:function($me,state){
		        					//ajax提交状态改变
		        					var status=state==true?"禁用":"正常"
		        					//当前的userID
		        					var $tr = $(this).parent().parent().parent().parent();
		        					var user = tables.user.row($tr).data(); //这一行的用户所有数据包括name什么的
		        					var userId = user.id;
		        					$.ajax({
		        		        		url:$.urlRoot+"/platform/userAction!updateStatus.action",
		        		        		type:"post",
		        		        		dataType:"json",
		        		        		data:{
		        		        				"user.id":userId,//被修改的用户的id
		        		        				"user.status":status,
		        		        		},
		        		        		success:function(d){
		        		        			//添加后刷新表格
		        		        			if(d.success){
		        		        				tables.user.draw();
		        		        			}else{
		        		        				$.W.alert(d.msg,true);
		        		        			}
		        		        		}
		        		        	});
		        				}
		        			});
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

