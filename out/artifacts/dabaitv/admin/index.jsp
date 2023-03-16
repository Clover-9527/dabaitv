<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<link href="../css/bootstrap.css" rel="stylesheet">
	<script src="../js/jquery-1.11.3.min.js"></script>
	<script src="../js/bootstrap.min.js"></script>
	<style>
		a,td,th{
			font-size: 15px;
		}

		div a {
			margin-top: 25px;
			line-height: 13px;
		}
	</style>
	<title>Insert title here</title>
</head>
<body>
	<div class="container-fluid">
		<div class="row" >
			<div class="col-md-2 ">
				<img src="../images/logo.png" width="100%" height="82px" style="margin-left: 20px"/>
			</div>
			<div class="col-md-3 col-md-offset-3 "  style="vertical-align: middle">
				<font color="#4169e1" style="font-family: 华文琥珀; font-size: 30px ; line-height: 82px" >大&nbsp;白&nbsp;影&nbsp;视&nbsp;管&nbsp;理&nbsp;系&nbsp;统</font>
			</div>
		</div>
	</div>
	<table class="table table-condensed" style="height: 550px" >
		<tr>
			<td style="width:140px;" valign="top" align="center">
				<div align="right" style="margin-top: 50px">
					<a class="btn btn-info" target="dataFrame" href="${pageContext.request.contextPath}/videoManage?operator=queryByPage">视频管理</a>
					<a class="btn btn-info" target="dataFrame" href="${pageContext.request.contextPath}/userManage?operator=queryByPage">用户管理</a>
				</div>
<%--				<div align="right">--%>
<%--					<a class="btn btn-info" target="dataFrame" href="${pageContext.request.contextPath}/videoManage">null</a>--%>
<%--				</div>--%>
				<div align="right">
					<a class="btn btn-info" target="dataFrame" href="${pageContext.request.contextPath}/userManage?operator=logout" onclick="f()">退出登录</a>
				</div>
			</td>
			<td>
				<iframe name="dataFrame" frameborder="0" height="890px" width="100%" src="welcome.jsp">

				</iframe>
			</td>
		</tr>

	</table>
</body>
<script>
	function f(){
		location.href = "/dabaitv/index.jsp";
	}
</script>
</html>
