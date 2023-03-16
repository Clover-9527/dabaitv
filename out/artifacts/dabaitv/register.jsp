<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>注册</title>
    <link rel="stylesheet" href="css/bootstrasp.min.css">
    <script src="js/jquery/jquery.js"> </script>
    <script src="js/bootstrap/bootstrap.js"></script>
    <script src="js/register.js" type="text/javascript"></script>
    <link rel="stylesheet" href="css/index.css">
    <link rel="stylesheet" href="css/pannel.css">

</head>

<body>
<!-- 页面头部分开始 -->
<header>
    <jsp:include page="header.jsp"></jsp:include>
    <!-- 页面头部分结束 -->


<!-- banner部分 -->
<div class="banner ">
    <!--没有给高度 清除浮动-->
    <div class="pannel clearfix ">
        <div class="reg_in">
            <h2 class="r_tittle">大白影院注册</h2>
            <form action="${pageContext.request.contextPath}/userManage?operator=register" method="post" class="text-center" enctype="multipart/form-data">
                <label class="form-inline" >
                    <input type="text" class="form-control center" name="nickname" placeholder="昵称" id="nickname">
                    <span id="nicknameCheck"></span>
                </label>
                <label class="form-inline" style="width: 255px">
                    <span>头像:&nbsp;&nbsp;</span>
                    <input type="file" name="uimg"style="width: 200px!important;"/>
                </label>
                <label class="form-inline" >
                   <input type="text" class="form-control center" name="username" placeholder="用户名" id="username">
                    <span id="usernameCheck"></span>
                </label>
                <label class="form-inline">
                     <input type="password" class="form-control center" name="password" placeholder="设置密码" id="pwd1">
                    <span id="pwdCheck1"></span>
                </label>
                <label class="form-inline">
                    <input type="password" class="form-control center" name="pwd" placeholder="确认密码" id="pwd2" >
                    <span id="pwdCheck"></span>
                </label>
                <label class="form-inline" >
                    <input type="text" class="form-control center phone_text" id="phone" placeholder="手机号" name="phone">
                    <span id="phoneCheck"></span>
                </label>

<div id="regBox">
                <label class="form-inline" >
                     <input type="text" class="form-control center" placeholder="短信验证码" id="dxcode"/>
                    <span id="mes"></span>
                </label>
                    <input type="button" class="btn btn-primary" id="hq" value="发送验证码" onclick="sendCode(this)" />

</div>
                <label class="form-inline" >
                <input type="submit" class="btn btn-success" value="同意协议并注册" />
                </label>
            </form>

        </div>
    </div>
</div>
<!-- banner结束 -->


<jsp:include page="footer.jsp"></jsp:include>
<script src="js/index.js" type="text/javascript"></script>
</body>
</html>

