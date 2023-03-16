<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta charset="utf-8">
    <title>Title</title>

</head>
<body>
<header>

    <nav>
        <!-- logo部分 -->
        <div class="logo" onclick="location='index.jsp'">
            <img src="images/logo.png" class="logoimg">
        </div>
        <!-- 导航栏部分 用li -->
        <div class="navbar">
            <ul>
                <li><a href="${pageContext.request.contextPath}/index.jsp">首页</a></li>
                <li><a href="${pageContext.request.contextPath}/index.jsp#tv">电视剧</a></li>
                <li><a href="${pageContext.request.contextPath}/index.jsp#movie">电影</a></li>
                <li><a href="${pageContext.request.contextPath}/index.jsp#variety">综艺</a></li>
                <li><a href="${pageContext.request.contextPath}/index.jsp#cartoon">动漫</a></li>
                <li><a href="${pageContext.request.contextPath}/index.jsp#download">软件下载</a></li>
                <li><a href="${pageContext.request.contextPath}/collectionManage?operator=loginCheck">收藏列表</a></li>
            </ul>
        </div>

        <!-- 搜索框部分 -->
        <div class="search">
            <form >
                <a class="nav-cuts" href="#">搜索</a>
                <input type="text" placeholder="请输入关键词" class="search_input">
                <input type="button" value="" onclick=search()>
            </form>
        </div>

        <!-- 显示时间部分 -->
        <div class="time">
            <span id="time"></span>
        </div>

        <!-- 注册登录部分 -->
        <div class="info">
            <a href="${pageContext.request.contextPath}/userInfo.jsp">
                <img src="${pageContext.request.contextPath}${user.uimg}" width="50" height="50" style="display:none;border-radius:50%"  class="userimg">
            </a>
            <a href="${pageContext.request.contextPath}/userManage?operator=loginCheck" class="dz">登录</a>
            <a href="${pageContext.request.contextPath}/register.jsp" class="dz">注册</a>
        </div>

    </nav>

<%--    检测登录状态--%>
    <script>
        let user= "${user.uid}";
        if(user!=""){
            $(".dz").remove();
            $(".userimg").css("display","")

            $(".info").append('<span>欢迎您, '+"${user.nickname}"+"</span><a id='out'>注销</a>");
            $(".info a").css("color","green");
            $("#out").attr("href","${pageContext.request.contextPath}/userManage?operator=logout");
        }else{
                $.ajax({
                    url: "${pageContext.request.contextPath}/userManage?operator=loginCheck",
                    type:'GET',
                    dataType:'JSON',
                    success:function (data){ }
                });

        }

        function search(){
           let v_title= $(".search_input").val();
           if(v_title.trim()=="")
               alert("请输入标题!");
           else
                window.location.href="${pageContext.request.contextPath}/videoManage?operator=searchVideo&v_title="+v_title;
        }
    </script>
</header>
</body>

</html>


