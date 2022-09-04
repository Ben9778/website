/*上传留言*/

$(function() {
	$(".sub").click(function() {
		var username = $(".username").val()
		var phone = $(".phone").val()
		var message = $(".message").val()
		var re = new RegExp("^[1][3,4,5,7,8][0-9]{9}$")
		if (username != "" && phone != ""&&phone.match(re)) {
			$.ajax({
				type : "POST",
				url : "http://www.fjtydy.com/insertMessage",
				data : {"username":username,"phone":phone,"message":message},
				dataType:"json",
				success : function(msg) {
					if(msg.code=="yes"){
						$(".username").val("")
						$(".phone").val("")
						$(".message").val("")
						alert("提交成功！")	
					}else{
						alert("提交失败，请稍后再试！")
					}
				}
			})
		}else if(username == "" || phone == ""){
			$(".warn span").text("姓名和手机不能为空！")
			$(".warn").show()
		}else if(!phone.match(re)&&username!= ""&&phone!= ""){
			$(".warn span").text("请输入正确手机格式！")
			$(".warn").show()
		}
	})
	$(".username,.phone,.message").click(function() {
		$(".warn").hide()
	})
})