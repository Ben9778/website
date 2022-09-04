<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>产品管理</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<meta name="author" content="黄滨">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<link rel="shortcut icon" href="http://www.fjtydy.com/picture/client/logo-ico.png">
<link
	href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="http://www.fjtydy.com/css/product.css">
<link rel="stylesheet" type="text/css"
	href="http://www.fjtydy.com/css/main.css">
<script type="text/javascript"
	src="http://www.fjtydy.com/js/jquery-3.6.0.min.js"></script>
<script
	src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="http://www.fjtydy.com/js/product.js"></script>
<script type="text/javascript"
	src="http://www.fjtydy.com/js/main.js"></script>
</head>
<body>
	<!--头部-->
	<div class="header">
		<div>
			天叶管理系统<i>v1.00</i>
		</div>
		<div>
			<a href="http://www.fjtydy.com"
				style="color: rgb(255, 255, 255); text-decoration: none;"
				target="_blank">官网首页</a>
		</div>
		<div>欢迎您管理员</div>
		<div class="updatepwd" style="cursor: pointer;">修改密码</div>
		<div class="loginout">
			<a href="http://www.fjtydy.com/tydyLogin"
				style="color: rgb(255, 255, 255); text-decoration: none;"> <img
				src="http://www.fjtydy.com/picture/data/logout.png"
				class="icon"><span>退出登陆</span>
			</a>
		</div>
	</div>
	<!--中间-->
	<div class="center">
		<!--左侧导航-->
		<div class="center-left">
			<ul>
				<li style="letter-spacing: 12px;">
					<a href="http://www.fjtydy.com/adminSys" style="color: rgb(255, 255, 255); text-decoration: none;"> 
						<img src="http://www.fjtydy.com/picture/data/home.png" style="width: 20px; height: 20px;">
						<span style="padding-left: 5px;">首页</span>
					</a>
				</li>
				<li>
					<a href="http://www.fjtydy.com/queryProducts" style="color: rgb(255, 255, 255); text-decoration: none;"> 
						<img src="http://www.fjtydy.com/picture/data/product.png" style="width: 20px; height: 20px;"> 
						<span style="padding-left: 5px; color: #4EEE94;">产品管理</span>
					</a>
				</li>
				<li>
					<a href="http://www.fjtydy.com/queryNew" style="color: rgb(255, 255, 255); text-decoration: none;"> 
						<img src="http://www.fjtydy.com/picture/data/new.png" style="width: 20px; height: 20px;"> 
						<span style="padding-left: 5px;">新闻管理</span>
					</a>
				</li>
			</ul>
		</div>
		<!--右侧面板-->
		<div class="center-right">
			<!--产品模块-->
			<div class="product">
				<div class="product-nav">
					<div class="product-nav-item">
						<span>首页</span> <span>/</span> <span>产品管理</span>
					</div>
				</div>
				<div class="container col-lg-12">
					<div class="btns" style="padding: 5px; margin-left: 20px;">
						<button class="btn btn-primary btn-xs" type="button" id="del">批量删除</button>
						<button class="btn btn-primary btn-xs" type="button" id="insert">添加产品</button>
					</div>
					<table class="table table-hover">
						<thead>
							<tr>
								<th><input type="checkbox" class="checkall"></th>
								<th>序号</th>
								<th>产品名称</th>
								<th>主图</th>
								<th>详情图</th>
								<th>创建时间</th>
							</tr>
						</thead>
						<tbody class="tb">
							<c:forEach items="${productList}" var="index">
							<c:if test="${empty index}"></c:if>
							<c:if test="${not empty index}">
								<tr>
									<td><input type="checkbox" name="" class="checks"
										value="${index.product_id}"></td>
									<td></td>
									<td>${index.product_name}</td>
									<td><img src="http://www.fjtydy.com/${index.imgurl}"
										style="width: 80px; height: 80px; display: inline-block; margin: 0 auto;"
										class="imgurl"></td>
									<td><img src="http://www.fjtydy.com/${index.detailurl}"
										style="width: 80px; height: 80px; display: inline-block; margin: 0 auto;"
										class="imgurl"></td>
									<td><fmt:formatDate value="${index.create_time}"
											type="both" pattern="yyyy-MM-dd HH:mm" /></td>
								</tr>
							</c:if>
							</c:forEach>
						</tbody>
					</table>
					<div class="no-rows" style="font-size: 16px; text-align: center;">暂无产品！</div>
				</div>
				<!--添加产品-->
				<form action="http://www.fjtydy.com/addProduct"
					enctype="multipart/form-data" method="post" class="insert-form"
					style="text-align: center;">
					<div>
						产品名称：<input type="text" name="product_name" class="product_name">
					</div>
					<input type="file" name="pic" accept="image/*" class="pic"
						style="visibility: hidden;">
					<div>
						<div style="display: inline-block;">
							<div style="margin-left: -20px;">产品图片</div>
							<div class="choose1">
								<img src="http://www.fjtydy.com/picture/data/add.png">
							</div>
						</div>
						<div style="display: inline-block;">
							<div style="margin-left: 30px;">详情图</div>
							<div class="choose2">
								<img src="http://www.fjtydy.com/picture/data/add.png">
							</div>
						</div>
					</div>
					<input type="file" name="detail" accept="image/*" class="detail"
						style="visibility: hidden;">
					<div class="col-sm-offset-3 col-sm-6">
						<button type="submit" class="btn btn-primary btn-ms" id="save">保存</button>
						<button type="button" class="btn btn-default btn-ms" id="cancle">取消</button>
						<span style="display: none; color: #FF0000; margin-left: 30px;"
							class="insertwarn">名称和图片不能为空!</span>
					</div>
				</form>
				<!--修改密码-->
					<form class="form-horizontal" role="form" id="updatepwd-form">
						<div class="form-group">
							<label for="admin_name" class="col-sm-3 control-label">用户名</label>
							<div class="col-sm-7">
								<input type="text" class="form-control" id="admin_name">
							</div>
						</div>
						<div class="form-group">
							<label for="admin_password" class="col-sm-3 control-label">旧密码</label>
							<div class="col-sm-7">
								<input type="password" class="form-control" id="admin_password">
							</div>
						</div>
						<div class="form-group">
							<label for="newpwd" class="col-sm-3 control-label">新密码</label>
							<div class="col-sm-7">
								<input type="password" class="form-control" id="newpwd">
							</div>
						</div>
						<div class="form-group">
							<label for="confirmpwd" class="col-sm-3 control-label">确认新密码</label>
							<div class="col-sm-7">
								<input type="password" class="form-control" id="confirmpwd">
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-3 col-sm-7">
								<button type="button" class="btn btn-default" id="sub">提交</button>
								<button type="button" class="btn btn-default" id="cancles">取消</button>
								<span style="display: none; color: #FF0000;margin-left: 30px;" class="updatewarn"></span>
							</div>
						</div>
					</form>
				<!--遮挡层-->
				<div class="shadow"></div>
			</div>
		</div>
	</div>
</body>
</html>