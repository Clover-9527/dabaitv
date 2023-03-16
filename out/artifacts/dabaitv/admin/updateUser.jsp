<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery-3.3.1.js"></script>
    <script src="js/bootstrap.min.js"></script>

    <style>
      th,td{
        text-align: center;
      }
      td,th{
        font-size: 14px;
      }
    </style>

  </head>
  <body>

  <div class="container">
    <center><h3>用户修改</h3></center>
    <center>
      <form action="${pageContext.request.contextPath}/userManage?operator=updateUser" method="post" enctype="multipart/form-data">
        <input type="hidden" value="${video.vid}" name="vid" >
        <div class="form-group" style="width: 45%" align="left">
          <label for="nickname" >昵称</label>
          <input type="text" class="form-control" id="nickname" name="nickname" value="${user.nickname}">
        </div>
        <div class="form-group" style="width: 45%" align="left">
          <label for="username">用户名</label>
          <input type="text" class="form-control"  id="username"  name="username" value="${user.username}" />
        </div>
        <div class="form-group" style="width: 45%" align="left">
          <label for="uimg">头像</label>
          <input type="file" id="uimg"  name="uimg" value="${user.uimg}" />
        </div>
        <div class="form-group" style="width: 45%" align="left">
          <label for="phone">手机号</label>
          <input type="text" class="form-control"  id="phone"  name="phone" value="${user.phone}" />
        </div>

        <center>
          <button type="submit" id="sub" class="btn btn-primary">提交</button>
          <button type="reset" class="btn btn-default">重置</button>
          <button type="button" class="btn btn-default" onclick="javascript:window.history.go(-1)">返回</button>
        </center>
      </form>
    </center>
  </div>
  </body>
</html>
