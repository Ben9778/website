<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>天叶丹云管理系统v1.00</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
	<meta name="author" content="黄滨">
	<meta http-equiv="X-UA-Compatible" content="IE=Edge">
	<link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
	<link rel="shortcut icon" href="http://www.fjtydy.com/picture/client/logo-ico.png">
	<link rel="stylesheet" type="text/css" href="http://www.fjtydy.com/css/admin.css">
	<link rel="stylesheet" type="text/css" href="http://www.fjtydy.com/css/main.css">
	<script type="text/javascript" src="http://www.fjtydy.com/js/jquery-3.6.0.min.js"></script>
	<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="http://www.fjtydy.com/js/admin.js"></script>
	<script type="text/javascript" src="http://www.fjtydy.com/js/main.js"></script>
</head>
<body>
	<!--头部-->
	<div class="header">
		<div>天叶管理系统<i>v1.00</i></div>
		<div>
			<a href="http://www.fjtydy.com" style="color: rgb(255,255,255);text-decoration:none;">官网首页</a>
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
						<span style="padding-left: 5px;color: #4EEE94;">首页</span>
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
						<span style="padding-left: 5px;">新闻管理</span>
					</a>
				</li>
			</ul>
		</div>
		<!--右侧面板-->
		<div class="center-right">
			<!--留言模块-->
			<div class="invite">
				<div class="invite-nav">
					<div class="invite-nav-item">
						<span>首页</span>
						<span>/</span>
						<span>合作咨询</span>
					</div>
				</div>
				<div class="container col-lg-12">
					<div class="btns" style="padding: 5px;margin-left: 20px;">
						<button class="btn btn-primary btn-ms" type="button" id="del">批量删除</button>
						<button class="btn btn-primary btn-ms" type="button" onclick="tableToExcel('item','留言信息表')">导出Excel</button>
					</div>
					<table class="table table-hover" id="item">
						<thead>
							<tr>
								<th><input type="checkbox" name="" class="checkall"></th>
								<th>序号</th>
								<th>姓名</th>
								<th>联系方式</th>
								<th>创建时间</th>
								<th>留言内容</th>
								<th>更新时间</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody class="tb">
							<%--动态查询留言--%>
							<c:forEach items="${inviteList}" var="list">
							<c:if test="${empty list}"></c:if>
							<c:if test="${not empty list}">
								<tr>
									<td>
										<input type="checkbox" name="" class="checks" value="${list.user_id}">
									</td>
									<td></td>
									<td>${list.username}</td>							
									<td>${list.phone}</td>
									<td>
										<fmt:formatDate value="${list.create_time}" pattern="yyyy-MM-dd HH:mm"/>
									</td>
									<c:if test="${list.message!=null}">
										<td>${list.message}</td>
									</c:if>
									<c:if test="${list.message==null}">
										<td></td>
									</c:if>
									<td>
										<fmt:formatDate value="${list.update_time}" type="both" pattern="yyyy-MM-dd HH:mm"/>
									</td>
									<td>
										<button class="btn btn-primary btn-xs edit" type="button">编辑</button>
									</td>
								</tr>
							</c:if>
							</c:forEach>
						</tbody>
					</table>
					<div class="no-rows" style="font-size:16px;text-align:center;">暂无数据！</div>
					<!--编辑框-->
					<form class="form-horizontal" role="form" id="edit-form">
						<input type="hidden" name="user_id" id="user_id">
						<div class="form-group">
							<label for="username" class="col-sm-3 control-label">名字</label>
							<div class="col-sm-7">
								<input type="text" class="form-control" id="username" name="username" placeholder="请输入名字">
							</div>
						</div>
						<div class="form-group">
							<label for="tel" class="col-sm-3 control-label">联系方式</label>
							<div class="col-sm-7">
								<input type="text" class="form-control" id="tel" placeholder="请输入联系方式">
							</div>
						</div>
						<div class="form-group">
							<label for="message" class="col-sm-3 control-label">留言</label>
							<div class="col-sm-7">
								<textarea class="form-control" rows="6" id="texts"></textarea>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-3 col-sm-7">
								<button type="button" class="btn btn-default" id="save">保存</button>
								<button type="button" class="btn btn-default" id="cancle">取消</button>
								<span style="display: none; color: #FF0000;margin-left: 30px;" class="editwarn">姓名和联系方式不能为空!</span>
							</div>
						</div>
					</form>
					<!--删除确认框-->
					<div class="confirm">
						<p style="padding: 5px;">确认删除数据？</p>
						<button type="button" class="btn btn-default" id="yes">是</button>
						<button type="button" class="btn btn-default" id="no">否</button>
					</div>
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
								<span style="display: none; color: #FF0000;margin-left: 10px;" class="updatewarn"></span>
							</div>
						</div>
					</form>
					<!--遮挡层-->
					<div class="shadow"></div>
				</div>
			</div>	
		</div>
	</div>	
	<script>
		function base64 (content) {
			return window.btoa(unescape(encodeURIComponent(content)));
		}

		function tableToExcel(tableID,fileName){
			var table = document.getElementById(tableID);
			var excelContent = table.innerHTML;
			var excelFile = "<html xmlns:o='urn:schemas-microsoft-com:office:office' xmlns:x='urn:schemas-microsoft-com:office:excel' xmlns='http://www.w3.org/TR/REC-html40'>";
			excelFile += "<head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head>";
			excelFile += "<body><table>";
			excelFile += excelContent;
			excelFile += "</table></body>";
			excelFile += "</html>";
			var link = "data:application/vnd.ms-excel;base64," + base64(excelFile);
			var a = document.createElement("a");
			a.download = fileName+".xlsx";
			a.href = link;
			a.click();
		}
	</script>
</body>
</html>