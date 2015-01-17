

$(document).ready(function(){
	/*$.W.setButton("sss","cccc");//配置后再使用下面的alert，设置有效仅为一次使用；提示一次后恢复默认按钮设置。
	$.W.alert("消息提示测试",true,function(){
		$.W.setButton("aaa");
		$.W.alert("消息提示测试",true,function(){
			$.W.alert("消息提示测试",true);
		});
	});*/
	view.init();
});

var view={
		init:function(){
			this.edictMsg();
		},
		edictMsg:function(){
			$("#btn-edict").on("click.edict",function(){
				$(".userMsg").hide();
				$(".userMsgEdict").show();
			});
			$("#btn-save").on("click.save",function(){
				$(".userMsgEdict").hide();
				$(".userMsg").show();
				var newUser = view.getFormVal();
				//ajax提交保存并刷新页面上的数据
				$.ajax({
				  url:$.urlRoot+"/platform/userAction!updateUserAndSession.action",
				  type:"post",
				  dataType:"json",
				  data:{
					  "user.id":newUser.id,
					  "user.realName":newUser.realName,
					  "user.email":newUser.email,
					  "user.tel":newUser.tel,
					  "user.introduction":newUser.introduction
				  },
				  success:function(data){
				    //view.refresh(data.msg.user);//刷新页面上的数据
					  if(data.success){
						  view.refresh(newUser)
					  }
				  }
				});
				
			});
		},
		refresh:function(){
			//根据表单上的新数据刷新
			/*在jsp页面上，放置改用户信息数据的地方请添加class="d-XXX" 其中XXX例：
			 * {name:"张",email:"..."}
			 * 那么放置name的<dd> 要写为<dd class="d-name"></dd>
			 * 刷新数据的时候才会更新最新的数据
			 * */
			
			var user = view.getFormVal();
			
			for(key in user){
				$("dd.d-"+key).text(user[key]);
			}
		},
		getFormVal:function(){
			//获取表单上的数据，请分离开来写，这样代码可以复用
			var account = new Object();
			account.id = $(".userMsgEdict input[name='id']").val();
			account.realName = $(".userMsgEdict input[name='realName']").val();
			account.email = $(".userMsgEdict input[name='email']").val();
			account.tel = $(".userMsgEdict input[name='tel']").val();
			account.introduction = $(".userMsgEdict input[name='introduction']").val();
			return account;
		}
};