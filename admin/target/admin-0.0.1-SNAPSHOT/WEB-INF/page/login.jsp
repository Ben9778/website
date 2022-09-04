<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="zh-CN" style="font-size: 80px;">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>登录界面</title>
    <meta name="author" content="黄滨">
	<meta http-equiv="X-UA-Compatible" content="IE=Edge">
	<link rel="shortcut icon" href="http://www.fjtydy.com/picture/client/logo-ico.png">
    <link rel="stylesheet" href="http://www.fjtydy.com/css/login.css">
    <script type="text/javascript" src="http://www.fjtydy.com/js/jquery-3.6.0.min.js"></script>
</head>
<body>
    <script>
        $(document).ready(function () {
            var whei = $(window).width()
            $("html").css({ fontSize: whei / 24 });
            $(window).resize(function () {
                var whei = $(window).width();
                $("html").css({ fontSize: whei / 24 })
            });

            $(".btn").click(function() {
                let result="yes";
                let admin_name=$(".admin_name").val();
                let admin_password=$(".admin_password").val();
                if(admin_name!=""&&admin_password!=""){
                	$.ajax({
                    	type:"post",
                    	url:"http://www.fjtydy.com/checkLogin",
                    	data:{"admin_name":admin_name,"admin_password":admin_password},
                    	dataType:"json",
                    	success:function(data) {
                        	if(data.message==result){
                          		window.location.replace("http://www.fjtydy.com/adminSys");
                        	}else if(data.message!=result){
                           	 	$(".fail").css("display","block");
                        	}
                    	}
                	})
                }else{
                	alert("用户名和密码不能为空！")
                }
            })
            /*清除提示*/
            $(".admin_name,.admin_password").click(function() {
                $(".fail").css("display","none");
            })
        })
    </script>
    <div class="main">
        <div class="header">
            <div class="header-center fl">
                <div class="header-title">
                    	登录界面
                </div>
                <div class="header-img"></div>
            </div>
            <div class="header-bottom fl"></div>

        </div>
        <div class="content">
            <div class="content-left"></div>
            <div class="content-right">
                <div class="right-infp">
                    <div class="right-infp-name">
                        <input type="text" name="admin_name" placeholder="请输入用户名" maxlength="12" autocomplete="off" class="admin_name">
                    </div>
                    <div class="right-infp-name">
                        <input type="password" name="admin_password" placeholder="请输入密码" autocomplete="off" class="admin_password">
                    </div>

                    <div class="right-infp-btn">
                        <button class="btn">登录</button>
                    </div>
                    <div class="fail" style="display: none; color: rgb(255,255,255); text-align: center;">账号或密码有误,请重新输入！</div>
                </div>
            </div>
        </div>
    </div>

</body>
</html>