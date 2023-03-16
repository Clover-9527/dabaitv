<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <link href="css/bootstrap.css" rel="stylesheet">
  <script src="js/jquery-1.11.3.min.js"></script>
  <script src="js/bootstrap.min.js"></script>

  <style>

    th,td{
      text-align: center;
      text-overflow: ellipsis;overflow: hidden; white-space: nowrap;
    }
    td,th{
      font-size: 14px;
    }
    #d:hover{
      background-color: #f5f5f5;
    }
    #d td{
      line-height: 50px;
    }
  </style>

  <script type="text/javascript">
    function del( uid ) {
      //js内置的函数,直接调用,名字 confirm    函数返回值,真假值,返回是true,确认删除
      if( confirm("确定要删除吗?") ){
        //服务器发送请求,提交主键id删除
        location.href="${pageContext.request.contextPath}/userManage?operator=deleteUser&uid="+uid;
      }
    }
    let username=${user.username};
    function resetpwd( ) {
      if( confirm("确定要重置密码吗?") ){
        alert("密码已重置为:123456");
        location.href="${pageContext.request.contextPath}/userManage?operator=resetPassword&username="+username;
      }
    }
  </script>
</head>
<body>

<div class="container-fluid">
  <table class="table table-bordered" style="table-layout: fixed" id="manageTable">

    <tr class="bg-primary">
      <th>ID</th>
      <th>头像</th>
      <th>用户名</th>
      <th>昵称</th>
      <th>手机号</th>
      <th>操作</th>
    </tr>

    <c:forEach items="${userPageBean.list}" var="user">
      <tr id="d">
        <td>${user.uid}</td>
        <td><img src="${pageContext.request.contextPath}${user.uimg}" width="50" height="50"></img></td>
        <td>${user.username}</td>
        <td>${user.nickname}</td>
        <td>${user.phone}</td>

        <td><a class="btn btn-default btn-sm" href="${pageContext.request.contextPath}/userManage?operator=findByUsername&username=${user.username}">编辑</a>
          <a  class= "btn btn-default btn-sm" href="#" onclick="del(${user.uid})">删除</a>
        <a class= "btn btn-default btn-sm" href="#"  onclick="resetpwd()">重置密码</a></td>
      </tr>
    </c:forEach>
  </table>



  <!--分页工具条-->
  <nav class="text-center">
    <ul class="pagination">
      <%--上一页按钮， 计算方式: 上一页=当前页-1，  超链接发送请求,提交【当前页-1】   按钮当到到第一页的时候,不能再点了--%>
      <c:if test="${userPageBean.currentPage==1}">
        <li class="disabled">
          <a href="#" aria-label="Previous">
            <span aria-hidden="true">&laquo;</span>
          </a>
        </li>
      </c:if>

      <%--不是第一页,按钮可以点击--%>
      <c:if test="${userPageBean.currentPage!=1}">
        <li >
          <a href="${pageContext.request.contextPath}/userManage?operator=queryByPage&currentPage=${userPageBean.currentPage-1}" aria-label="Previous">
            <span aria-hidden="true">&laquo;</span>
          </a>
        </li>
      </c:if>
      <%--
         页码处理
         页码是从1开始,到总页数结束
         总页数在PageBean对象中的变量 totalPage
         循环出现来的
         页码显示的是循环中变量
      --%>
      <c:forEach begin="1" end="${userPageBean.totalPage}" var="i">
        <%--
            点击页码,向服务器发送请求,提交当前页数
            超链接请求,传递参数
            如果页码正好是当前页,不能点击
            循环中的变量,就是页码
            判断: 页码是不是=当前页数  (PageBean对象的属性currentPage)
        --%>
        <c:if test="${userPageBean.currentPage==i}">
          <li class="active"><a href="#">${i}</a></li>
        </c:if>

        <c:if test="${userPageBean.currentPage!=i}">
          <li><a href="${pageContext.request.contextPath}/userManage?operator=queryByPage&currentPage=${i}">${i}</a></li>
        </c:if>

      </c:forEach>



      <%--
       下一页 = 当前页+1
       判断: 如果已经是最后一页,不能点击
       当前页=总页数,就是最后一页
      --%>`

      <c:if test="${userPageBean.currentPage == userPageBean.totalPage}">
        <li class="disabled">
          <a href="#" aria-label="Next">
            <span aria-hidden="true">&raquo;</span>
          </a>
        </li>
      </c:if>

      <c:if test="${userPageBean.currentPage != userPageBean.totalPage}">
        <li>
          <a href="${pageContext.request.contextPath}/userManage?operator=queryByPage&currentPage=${userPageBean.currentPage+1}" aria-label="Next">
            <span aria-hidden="true">&raquo;</span>
          </a>
        </li>
      </c:if>

    </ul>
  </nav>
</div>
</div>
</body>
</html>
</div>
</div>
</body>
</html>
