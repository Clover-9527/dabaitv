<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>搜索结果</title>
    <link rel="stylesheet" type="text/css" href="css/index.css"/>
    <link rel="stylesheet" type="text/css" href="css/bootstrasp.min.css">
    <script src="js/jquery/jquery.js"> </script>
    <script src="js/jquery/popper.js"></script>
    <script src="js/bootstrap/bootstrap.js"></script>
    <link rel="stylesheet" type="text/css" href="css/pannel.css" />
</head>
<body>
</head>


<!-- 页面头部分开始 -->
<jsp:include page="header.jsp"></jsp:include>
<!-- 页面头部分结束 -->


<!-- banner部分开始 -->
<div class="banner ">
<div class="banner-in container clearfix"  style="margin-bottom:100px">
    <ul id="searchUL">
        <h1 id="searchMessage" style="display: none">查询0个结果</h1>
        <c:forEach items="${searchList}" var="video">
            <li>
                <a href="${pageContext.request.contextPath}/videoManage?operator=videoDetails&vid=${video.vid}">
                    <img src="${video.v_img}" style="width: 170px;height: 238px">
                    <span>${video.v_title}</span>
                </a>
            </li>
        </c:forEach>
    </ul>

</div>
</div>
<!-- banner结束 -->

<script>
    let list=${searchList};
    if(list="[]"){
        $('#searchMessage').css('display','');
    }

</script>


<!-- 页面尾部分开始 -->
<jsp:include page="footer.jsp"></jsp:include>
<!-- 页面尾部分结束 -->
</body>
<script src="js/index.js"></script>
</html>
