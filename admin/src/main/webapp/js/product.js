/*作者：黄滨*/
$(function(){

 	/*****添加产品******/
	/*表格自增序号*/
	var len = $('table tr').length;
    for(var i = 1;i<len;i++){
        $('table tr:eq('+i+') td:eq(1)').text(i);
    }
    /*判断行数隐藏删除按钮*/
    $('.tb tr').length>0?$("#del").css("display","inline-block"):$("#del").css("display","none")
     /*无内容显示文字提醒*/
    $('.tb tr').length==0?$(".no-rows").css("display","block"):$(".no-rows").css("display","none")
    		
    $("#insert").click(function () {
		$(".insert-form,.shadow").show()
	})
	
	//取消
	$("#cancle").click(function () {
		$(".insert-form,.shadow").hide()
	})
	//保存
	// $("#save").click(function () {

	// 	var product_name=$(".product_name").val()
	// 	var pic=$(".pic").val()
	// 	var detail=$(".detail").val()

	// 	if(product_name!=null&&pic!=null&&detail!=null){
	// 		$(".shadow,#insert").hide()
	// 	}else{
	// 		$(".insertwarn").show()
	// 	}
	//})
	
	//把错误提示隐藏
	$(".product_name,.choose1,.choose2").click(function() {
		$(".insertwarn").css("display","none")
	})
	  /*选主图*/
    $(".choose1").click(function(){
    	$(".pic").click()
    })
    $(".pic").change(function(){
    	 $(".choose1 img").attr("src",URL.createObjectURL($(this)[0].files[0])).css("margin-top","5px").height("90%").width("90%")
    	 $(".choose1").css("border-style","solid").css("border-width","2px").css("border-color","rgb(242,242,242)")
    })
  	//选详情图
    $(".choose2").click(function(){
    	$(".detail").click()
    })
    $(".detail").change(function(){
    	 $(".choose2 img").attr("src",URL.createObjectURL($(this)[0].files[0])).css("margin-top","5px").height("90%").width("90%")
    	 $(".choose2").css("border-style","solid").css("border-width","2px").css("border-color","rgb(242,242,242)")
    })


    /*****删除产品******/
	//全选|全不选
	$('.checkall').click(function (){
		$('.checks').prop('checked',$(this).prop('checked'))
	})
    //删除选中的id值
    $("#del").click(function () {
    	 var num=$(".checks:checked").length
         if(num==0){
           alert("请选择数据!")  
         }else{
             var ids=[]
             $(".checks:checked").each(function (){
                 ids.push($(this).val())
             })
             if(confirm("是否删除？")){
              $.ajax({
               type:"get",
               url:"http://www.fjtydy.com/deleteProduct",
               data:{"product_id":ids},
               traditional : "true",
               dataType:"json",
               success:function(msg){
                   if(msg.code=="yes"){
                       alert("删除成功！")
                       location.reload()
                   }else{
                       alert("删除失败！")
                   }
               }
              })
             }
     	}
    })
})


