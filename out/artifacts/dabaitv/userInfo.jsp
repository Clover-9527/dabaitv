<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>个人中心</title>
  <link rel="stylesheet" href="css/bootstrasp.min.css">
  <script src="js/jquery/jquery.js"> </script>
  <script src="js/jquery/popper.js"></script>
  <script src="js/bootstrap/bootstrap.js"></script>
  <script src="js/alteruser.js"></script>
  <link rel="stylesheet" type="text/css" href="css/index.css" />
  <link rel="stylesheet" type="text/css" href="css/pannel.css" />


</head>
<body>
<!-- 页面头部分开始 -->
<header>
  <jsp:include page="header.jsp"></jsp:include>
  <!-- 页面头部分结束 -->
</header>

<!-- banner部分 -->
<div class="banner ">
  <div class="collection_pannel clearfix ">

    <form action="${pageContext.request.contextPath}/userManage?operator=alterUser" style="text-align: center;margin: 50px auto;width: 461px;" method="post" enctype="multipart/form-data">
      <h1 style="margin-right: 70px">修改用户信息</h1>
      <label class="form-inline" >
          <span style="margin-right: 35px"> 用户名:</span> <input type="text" name="username" readonly value="${user.username}">
      </label>
      <label class="form-inline" >
        <span style="margin-right: 50px"> 头像:</span> <input type="file" name="uimg" >
      </label>
      <label class="form-inline" >
        <span style="margin-right: 50px"> 昵称: </span> <input type="text" name="nickname" value="${user.nickname}" id="nickname"><span id="nicknameCheck" style="color: red"></span>
      </label>
       <label class="form-inline" >
         <span style="margin-right: 35px"> 手机号:</span> <input type="text" name="phone" value="${user.phone}" id="phone"><span id="phoneCheck" style="color: red">${phoneCheck}</span>
        </label>
      <label class="form-inline" >
        <span style="margin-right: 35px"> 原密码:</span><input type="password" name="password" id="password"><span id="passwordCheck" style="color: red">${passwordCheck}</span>
      </label>
      <label class="form-inline" >
        <span style="margin-right: 35px"> 新密码: </span><input type="password" name="newPassword" id="pwd1"><span id="pwd1Check" style="color: red">${pwd1Check}</span>
      </label>
      <label class="form-inline" >
        <span> 确认新密码:&nbsp; </span><input type="password" name="newPassword2" id="pwd2"><span id="pwdCheck" style="color: red">${pwd2Check}</span>
      </label>
      <input type="submit" value="修改" style="width: 100px;height: 50px;margin-right: 50px">
    </form>

  </div>
</div>
<!-- banner结束 -->



<!-- footer -->
<jsp:include page="footer.jsp"></jsp:include>
<em id="download"></em>
</body>
<script src="js/index.js" type="text/javascript"></script>

</html>
