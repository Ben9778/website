/*作者：黄滨*/
$(function(){

	/************************共用部分**************************/
	
	/*修改密码*/
	$(".updatepwd").click(function () {
		$(".updatewarn").css("display","none").html("")
		$("#updatepwd-form,.shadow").show()
	})
	//取消
	$("#cancles").click(function () {
		$("#updatepwd-form,.shadow").hide()
	})
	//提交，将新密码保存后端
	$("#sub").click(function () {
		var admin_name=$("#admin_name").val()
		var admin_password=$("#admin_password").val()
		var newpwd=$("#newpwd").val()
		var confirmpwd=$("#confirmpwd").val()
		
		if(admin_name!="" && admin_password!="" && newpwd!="" && confirmpwd!="" && newpwd==confirmpwd && admin_password!=newpwd){
			$.ajax({
				type:"POST",
				url:"http://www.fjtydy.com/updatePwd",
				data:{
					"admin_name":admin_name,
					"admin_password": admin_password,
					"newpwd":newpwd
				},
				dataType:"JSON",
				success:function(msg){
					if(msg.code=="yes"){
						alert("重置成功，请重新登陆！")
				 		$("#updatepwd-form,.shadow").hide()
				 		window.location.replace("http://www.fjtydy.com/tydyLogin")
					}else if(msg.code=="error"){
						$(".updatewarn").css("display","inline").html("用户名或密码错误！")
					}else{
						alert("重置失败！")
					}	
				}
			})
		}else if(admin_password!="" && newpwd!="" && admin_password==newpwd){
			$(".updatewarn").css("display","inline").html("新密码与旧密码不能相同！")
		}else if(newpwd!="" && confirmpwd!="" && newpwd!=confirmpwd){
			$(".updatewarn").css("display","inline").html("两次输入密码必须相同！")
		}else{
			$(".updatewarn").css("display","inline").html("以上选项不能为空！")
		}
		
	})

	//错误提示隐藏
	$("#admin_name,#admin_password,#newpwd,#confirmpwd").click(function() {
		$(".updatewarn").css("display","none")
	})
})

