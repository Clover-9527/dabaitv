<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>登录</title>

    <link rel="stylesheet" href="css/bootstrasp.min.css">
    <script src="js/jquery/jquery.js"> </script>
    <script src="js/jquery/popper.js"></script>
    <script src="js/bootstrap/bootstrap.js"></script>
    <link rel="stylesheet" href="css/index.css">
    <link rel="stylesheet" href="css/pannel.css">
    <script src="js/login.js" type="text/javascript"></script>
</head>


<body>
<!-- 页面头部分开始 -->

    <jsp:include page="header.jsp"></jsp:include>
<!-- 页面头部分结束 -->



<!-- banner部分 -->
<div class="banner ">
    <!--没有给高度 清除浮动-->
    <div class="pannel">
        <div class="login_in">
            <h2 class="l_tittle">大白影院登录</h2>
            <form action="${pageContext.request.contextPath}/userManage?operator=login" method="post">
                <label class="form-inline" >
            <input type="text" class="form-control" name="username" placeholder="用户名" id="username" value="${pwdInfo.username}">
                        <span id="usernameCheck" style="color: red">
                        <%=request.getAttribute("loginError")!=null?request.getAttribute("loginError"):""%>
                         </span>
                </label>
                <label class="form-inline" >
                    <input type="password" class="form-control" name="password" placeholder="密码" id="password" value="${pwdInfo.password}">
                    <span id="passwordCheck"></span>
                </label>

              <div class="yzm">
                 <input type="text" class="form-control" name="code_form" placeholder="验证码" id="yzm"/>
                <img src="${pageContext.request.contextPath}/userManage?operator=getCode" id="codeImg" onclick="changeCode()" style="margin-left: 10px"/>
                  <span style="margin-left: 10px;margin-top: 10px;color: red" id="yzmCheck">
                        <%=request.getAttribute("errorMsg")!=null?request.getAttribute("errorMsg"):""%>
                    </span>
                <script>
                    function changeCode(){
                        document.getElementById("codeImg").src = "${pageContext.request.contextPath}/userManage?operator=getCode&r=" + Math.random();
                    }
                </script>
              </div>
            <div class="pwdsetting">
                <input type="checkbox" checked="checked" name="pwdremember" style="margin: 5px 5px"/><span >记住密码&nbsp;</span>
                <input type="checkbox" checked="checked" name="autologin" style="margin: 5px 5px"/><span >自动登录&nbsp;</span>
<%--                <a href="#">忘记密码</a>--%>
            </div>
            <div id="l_r_btn">
                <input type="submit" class="btn btn-primary login_btn" value="登录" />
                <input type="button" onclick="location='${pageContext.request.contextPath}/register.jsp'" class="btn btn-dark login_btn" value="注册" />
            </div>
            </form>
        </div>
    </div>
</div>
<!-- banner结束 -->



<!-- 页面底部分开始 -->
<jsp:include page="footer.jsp"></jsp:include>
<!-- 页面底部分结束 -->


</body>
<script src="js/index.js" type="text/javascript"></script>
</html>

