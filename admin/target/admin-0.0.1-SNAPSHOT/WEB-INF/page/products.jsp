<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-CN"> 
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>产品展示</title>
	<meta name="keywords" content="天叶丹云,三叶青,关于三叶青,天叶丹云官网,三叶青是什么,天叶丹云公司怎么样,永泰三叶青">
	<meta name="description" content="福建天叶丹云生物科技有限公司专注于三叶青产业链，集种植、研发、销售于一体，为客户提供纯天然农副产品。">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
	<meta name="author" content="黄滨">
	<meta http-equiv="X-UA-Compatible" content="IE=Edge">
	<link rel="shortcut icon" href="http://www.fjtydy.com/picture/client/logo-ico.png">
	<link rel="stylesheet" type="text/css" href="http://www.fjtydy.com/css/public.css">
	<link rel="stylesheet" type="text/css" href="http://www.fjtydy.com/css/products.css">
	<script type="text/javascript" src="http://www.fjtydy.com/js/jquery-3.6.0.min.js"></script>
</head>
<body>
	<!--脚本未被执行时提示文本-->
	<noscript>Sorry, your browser does not support JavaScript!</noscript>
	<!--页面头部区域-->
	<div id="header">
		<!-- 企业LOGO-->
		<div class="logo-area">
			<a href="http://www.fjtydy.com/index.html">
				<img src="http://www.fjtydy.com/picture/client/logo.png" alt="天叶丹云">
			</a>
		</div>
		<!--导航-->
		<div class="navigation">
			<ul class="nav">
				<li>
					<a href="http://www.fjtydy.com/index.html">首页</a>
				</li>
				<li>
					<a href="http://www.fjtydy.com/queryProduct" style="color: skyblue">产品展示</a>
				</li> 
				<li>
					<a href="http://www.fjtydy.com/aboutUs.html">关于我们</a>
				</li>
				<li>
					<a href="http://www.fjtydy.com/invite.html">招商合作</a>
				</li>
				<li>
					<a href="http://www.fjtydy.com/queryNews">新闻动态</a>
				</li>
				<li>
					<a href="http://www.fjtydy.com/contact.html">联系我们</a>
				</li>
			</ul>
		</div>
	</div>
	
	<!--页面中间区域-->
	<div id="center">
		<!--横幅背景-->
		<div class="product-banner"></div>
		<!--产品展示-->
		<div class="display">
			<div class="display-title">产品系列</div>
			<div class="display-item">
				<%-- 遍历所有产品--%>
				<c:forEach items="${productsList}" var="list">
				<c:if test="${empty list}"></c:if>
				<c:if test="${not empty list}">
					<div class="product">
						<a href="http://www.fjtydy.com/${list.pageurl}" target="_blank">
							<img src="http://www.fjtydy.com/${list.imgurl}" class="scale">
							<p class="product-name">${list.product_name}</p>
						</a>
					</div>
				</c:if>
				</c:forEach>
			</div>
		</div>
	</div>
	
	<!--页面底部区域-->
	<div id="footer">
		<div class="footer-item">
			<div class="contact">
				<p>办公地址: 福州市仓山区金桔一路115号</p>
				<p>种植地址：福州市永泰县丹云乡赤岸村</p>
				<p>服务热线: 0591-86258511</p>
				<p>
					<a href="http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=35012502000143" target="_blank" style="color: rgb(255,255,255);">
						<img src="http://www.fjtydy.com/picture/client/bak.png" style="padding-right: 5px;">闽公网安备 35012502000143号
					</a>
					<a href="https://beian.miit.gov.cn" target="_blank" style="color: rgb(255,255,255);margin-left: 10px;">闽ICP备2022011206号</a>
				</p>
				<p>Copyright © 2022 福建天叶丹云生物科技有限公司. All Rights Reserved. 天叶公司 版权所有</p>
			</div>
			<div class="gzh">
				<img src="http://www.fjtydy.com/picture/client/public.jpg" style="width: 119px; height: 117px;">
				<p>微信公众号</p>
			</div>
		</div>
	</div>
	<!--回顶部-->
	<div class="backTop">
		<a href="#">
			<img src="http://www.fjtydy.com/picture/client/back-top.png" title="回到顶部">
		</a>
	</div>
</body>
</html>