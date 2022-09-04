/*作者：黄滨*/
$(function(){
	
	/*表格自增序号*/
	var len = $('table tr').length;
    for(var i = 1;i<len;i++){
        $('table tr:eq('+i+') td:eq(1)').text(i);
    }
    /*判断行数隐藏删除按钮*/
    $('.tb tr').length>0?$(".btns").css("display","block"):$(".btns").css("display","none")
    /*无内容显示文字提醒*/
    $('.tb tr').length==0?$(".no-rows").css("display","block"):$(".no-rows").css("display","none")		
	/*编辑框*/
	$(".edit").click(function () {
		//编辑框内赋表格对应的值
		var tr = $(this).closest("tr")
		var colData0 = tr.find("td:eq(0) input[type=checkbox]").val()
		var colData2 = tr.find("td:eq(2)").text()
		var colData3 = tr.find("td:eq(3)").text()
		var colData5 = tr.find("td:eq(5)").text()
		$("#user_id").prop("value",colData0)
		$("#username").prop("value",colData2)
		$("#tel").prop("value",colData3)
		$("#texts").prop("value",colData5)
		
		$("#edit-form,.shadow").show()
	})
	//取消
	$("#cancle").click(function () {
		$("#edit-form,.shadow").hide()
	})
	//保存，修改数据传后端
	$("#save").click(function () {
		var user_id=$("#user_id").val()
		var username=$("#username").val()
		var phone=$("#tel").val()
		var message=$("#texts").val()
		if(username==""||phone==""){
			$(this).parent().children("span").css("display","inline")
		}else{
			$.ajax({
				url:"http://www.fjtydy.com/updateMessage",
				type:"post",
				data:{"user_id":user_id,"username":username,"phone":phone,"message":message},
				dataType:"json",
				success:function(msg){
					if(msg.code=="yes"){
						alert("修改成功！")
						$("#edit-form,.shadow").hide()
						location.reload()//刷新页面
					}else{
						alert("修改失败！")
					}
				}
			})
		}
	})
	//把错误提示隐藏
	$("#username,#tel").click(function() {
		$(".editwarn").css("display","none")
	})

	/*删除数据*/
	//全选|全不选
	$('.checkall').click(function (){
		$('.checks').prop('checked',$(this).prop('checked'))
	});
    //获取选中的id值,判空提示
    $("#del").click(function () {
    	if($(".checks:checked").length==0){
    		alert("请选择删除内容！")
    	}else{
    		$(".confirm,.shadow").show()
    	}
    })

	//否
	$("#no").click(function () {
		$(".confirm,.shadow").hide()
	})
	//是,将ID传后端删除
	$("#yes").click(function () {
		var ids=[];
    	$('.checks:checked').each(function (){
    		ids.push($(this).val())
    	})
		$.ajax({
			type:"post",
			url:"http://www.fjtydy.com/deleteMessage",
			data:{"user_id":ids},
			traditional : "true",
            dataType:"json",
			success:function(msg){
				if(msg.code!=null&&msg.code=="yes"){
					$(".confirm,.shadow").hide()
					location.reload()//刷新页面
				}else{
					alert("删除失败！")
				}
			}
		
		})
	})

})



