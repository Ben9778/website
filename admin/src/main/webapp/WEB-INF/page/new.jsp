<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>新闻管理</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
	<meta name="author" content="黄滨">
	<meta http-equiv="X-UA-Compatible" content="IE=Edge">
	<link rel="shortcut icon" href="http://www.fjtydy.com/picture/client/logo-ico.png">
	<link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="http://www.fjtydy.com/css/new.css">
	<link rel="stylesheet" type="text/css" href="http://www.fjtydy.com/css/main.css">
	<script type="text/javascript" src="http://www.fjtydy.com/js/jquery-3.6.0.min.js"></script>
	<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="http://www.fjtydy.com/js/main.js"></script>
	<script src="https://cdn.tiny.cloud/1/y8651dajf7q0we3m6qeuprlbqi3m2mhyo0hgdsw2k2df56ec/tinymce/6/tinymce.min.js" referrerpolicy="origin"></script>
	<script type="text/javascript" src="http://www.fjtydy.com/js/new.js"></script>
	<script type="text/javascript" src="http://www.fjtydy.com/js/tinymce.js"></script>
</head>
<body>
	<!--头部-->
	<div class="header">
		<div>天叶管理系统<i>v1.00</i></div>
		<div>
			<a href="http://www.fjtydy.com" style="color: rgb(255,255,255);text-decoration:none;" target="_blank">官网首页</a>
		</div>
		<div>欢迎您管理员</div>
		<div class="updatepwd" style="cursor: pointer;">修改密码</div>
		<div class="loginout">
			<a href="http://www.fjtydy.com/tydyLogin" style="color: rgb(255,255,255);text-decoration:none;">
				<img src="http://www.fjtydy.com/picture/data/logout.png" class="icon"><span>退出登陆</span>
			</a>
		</div>
	</div>
	<!--中间-->
	<div class="center">
		<!--左侧导航-->
		<div class="center-left">
			<ul>
				<li style="letter-spacing: 12px;">
					<a href="http://www.fjtydy.com/adminSys" style="color:rgb(255,255,255);text-decoration: none;">
						<img src="http://www.fjtydy.com/picture/data/home.png" style="width: 20px;height: 20px;">
						<span style="padding-left: 5px;">首页</span>
					</a>
				</li>
				<li>
					<a href="http://www.fjtydy.com/queryProducts" style="color:rgb(255,255,255);text-decoration: none;">
						<img src="http://www.fjtydy.com/picture/data/product.png" style="width: 20px;height: 20px;">
						<span style="padding-left: 5px;">产品管理</span>
					</a>
				</li>
				<li>
					<a href="http://www.fjtydy.com/queryNew" style="color:rgb(255,255,255);text-decoration: none;">
						<img src="http://www.fjtydy.com/picture/data/new.png" style="width: 20px;height: 20px;">
						<span style="padding-left: 5px;color: #4EEE94;">新闻管理</span>
					</a>
				</li>
			</ul>
		</div>
		<!--右侧面板-->
		<div class="center-right" style="background: #AEEEEE;">
			<!--新闻模块-->
			<div class="new">
				<!--导航-->
				<div class="new-nav">
					<div class="new-nav-item">
						<span>首页</span>
						<span>/</span>
						<span>新闻管理</span>
					</div>
				</div>
				<!--按钮选择-->
				<div class="new-btn" style="margin-top:15px; margin-left:45px;">
					<button class="btn btn-primary btn-ms" type="button" id="del">批量删除</button>
					<button class="btn btn-primary btn-ms" type="button" id="insert">添加新闻</button>
				</div>
				<!--新闻内容-->
				<div class="new-body">
					<div class="no-new" style="text-align: center;font-size: 16px;">暂无新闻内容！</div>
					<c:forEach items="${newList}" var="list">
					<c:if test="${empty list}"></c:if>
					<c:if test="${not empty list}">
						<div class="new-body-item">
							<img src="http://www.fjtydy.com/${list.imgPath}">
							<p>${list.dates}</p>
							<h4>${list.title}</h4>
							<p>${list.introduce}</p>
							<p style="width:90%; position:absolute; bottom:0;">
								<span class="edit">编辑</span>
								<a href="http://www.fjtydy.com/${list.pagePath}" class="preview">预览</a>
								<input type="checkbox" value="${list.new_id}" class="checks">
							</p>
							<textarea style="display: none;">${list.content}</textarea>
						</div>
					</c:if>
					</c:forEach>
				</div>
			</div>		
		</div>
			<!--操作新闻-->
			<form id="insert-form">
				<div class="inputs">
					标题  <input type="text" name="title" class="title">
				</div>
				<div class="inputs">
					简述  <input type="text" name="introduce"  class="introduce">
				</div>
				<div class="inputs">
					日期  <input type="date" name="dates" class="dates">
				</div>
				<div style="margin-left: 68px;">
					<input type="file" name="imgPath" accept="image/*" class="imgPath" style="visibility: hidden;">
					新闻主图 <img src="http://www.fjtydy.com/picture/data/add1.png" class="picture">
					<input type="hidden" class="new_id">
				</div>
				<div class="contents">
					文章内容 <textarea name="content" class="content"></textarea>
				</div>
				<div class="btns">
					<span class="publish" style="display: none;">发布</span>
					<span class="save" style="display: none;">保存</span>
					<span class="cancels">取消</span>
				</div>
				<div class="insertwarn" style="text-align: center; color: red; display: none; ">以上内容不能为空！</div>
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
	<div class="backTop">
		<a href="#del">
			<img src="http://www.fjtydy.com/picture/client/back-top.png" title="回到顶部">
		</a>
	</div>
</body>
</html>