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
      function del( id ) {
        //js内置的函数,直接调用,名字 confirm    函数返回值,真假值,返回是true,确认删除
        if( confirm("确定要删除吗?") ){
          //服务器发送请求,提交主键id删除
          location.href="${pageContext.request.contextPath}/videoManage?operator=delVideo&vid="+id;
        }
      }
    </script>
  </head>
  <body>

    <div class="container-fluid">
      <table class="table table-bordered" style="table-layout: fixed" id="manageTable">
        <tr>
          <td colspan="9">


          <form action="${pageContext.request.contextPath}/videoManage?operator=queryByPage" method="post" align="center" style="margin: 20px 250px 20px 20px">
            <sapn style="margin-left: 20px">视频ID:</sapn>
            <input type="text" name="vid" style="width:100px">
            <sapn style="margin-left: 20px">视频标题:</sapn>
            <input type="text" name="v_title" style="width:150px" >
            <sapn style="margin-left: 20px">视频类别:</sapn>
            <select   name="classify" style="margin-right: 20px">
              <option value=""></option>
              <option value="电视剧">电视剧</option>
              <option value="电影">电影</option>
              <option value="综艺">综艺</option>
              <option value="动漫">动漫</option>
            </select>
              <input type="submit" value="搜索" style="margin-right:20px " class="btn btn-primary">
            <a href="admin/addVideo.jsp" class="btn btn-default">添加</a>
          </form>


          </td>
        </tr>
        <tr class="bg-primary">
          <th>ID</th>
          <th>标题</th>
          <th>封面</th>
          <th>评分</th>
          <th>类别</th>
          <th>主演</th>
          <th>描述</th>
          <th>播放链接</th>
          <th>操作</th>
        </tr>

        <c:forEach items="${pageBean.list}" var="video">
          <tr id="d">
            <td>${video.vid}</td>
            <td>${video.v_title}</td>
            <td><img src="${video.v_img}" width="50" height="50"></img></td>
            <td>${video.score}</td>
            <td>${video.classify}</td>
            <td>${video.protagonist}</td>
            <td>${video.description}</td>
            <td>${video.v_url}</td>

            <td><a class="btn btn-default btn-sm" href="${pageContext.request.contextPath}/videoManage?operator=findByID&vid=${video.vid}">修改</a>
              <a  class= "btn btn-default btn-sm" href="#" onclick="del(${video.vid})">删除</a></td>
          </tr>
        </c:forEach>
      </table>



      <!--分页工具条-->
      <nav class="text-center">
        <ul class="pagination">
          <%--上一页按钮， 计算方式: 上一页=当前页-1，  超链接发送请求,提交【当前页-1】   按钮当到到第一页的时候,不能再点了--%>
          <c:if test="${pageBean.currentPage==1}">
            <li class="disabled">
              <a href="#" aria-label="Previous">
                <span aria-hidden="true">&laquo;</span>
              </a>
            </li>
          </c:if>

          <%--不是第一页,按钮可以点击--%>
          <c:if test="${pageBean.currentPage!=1}">
            <li >
  <a href="${pageContext.request.contextPath}/videoManage?operator=queryByPage&currentPage=${pageBean.currentPage-1}&vid=${v.vid}&v_title=${v.v_title}&classify=${v.classify}" aria-label="Previous">
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
          <c:forEach begin="1" end="${pageBean.totalPage}" var="i">
            <%--
                点击页码,向服务器发送请求,提交当前页数
                超链接请求,传递参数
                如果页码正好是当前页,不能点击
                循环中的变量,就是页码
                判断: 页码是不是=当前页数  (PageBean对象的属性currentPage)
            --%>
            <c:if test="${pageBean.currentPage==i}">
              <li class="active"><a href="#">${i}</a></li>
            </c:if>

            <c:if test="${pageBean.currentPage!=i}">
              <li><a href="${pageContext.request.contextPath}/videoManage?operator=queryByPage&currentPage=${i}&vid=${v.vid}&v_title=${v.v_title}&classify=${v.classify}">${i}</a></li>
            </c:if>

          </c:forEach>


          <c:if test="${pageBean.currentPage == pageBean.totalPage}">
            <li class="disabled">
              <a href="#" aria-label="Next">
                <span aria-hidden="true">&raquo;</span>
              </a>
            </li>
          </c:if>

          <c:if test="${pageBean.currentPage != pageBean.totalPage}">
            <li>
              <a href="${pageContext.request.contextPath}/videoManage?operator=queryByPage&currentPage=${pageBean.currentPage+1}&vid=${v.vid}&v_title=${v.v_title}&classify=${v.classify}" aria-label="Next">
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
