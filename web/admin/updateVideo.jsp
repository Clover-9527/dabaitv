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
    <center><h3>视频修改</h3></center>
    <center>
      <form action="${pageContext.request.contextPath}/videoManage?operator=updateVideo" method="post" enctype="multipart/form-data">
        <input type="hidden" value="${video.vid}" name="vid" >
        <div class="form-group" style="width: 45%" align="left">
          <label for="v_title" >视频标题</label>
          <input type="text" class="form-control" id="v_title" name="v_title" value="${video.v_title}">
        </div>
        <div class="form-group" style="width: 45%" align="left">
          <label for="v_img">封面</label>
          <input type="text" class="form-control"  id="v_img"  name="v_img" value="${video.v_img}" />
        </div>
        <div class="form-group" style="width: 45%" align="left">
          <label for="score">评分</label>
          <input type="text" class="form-control"  id="score"  name="score" value="${video.score}" />
        </div>

        <div class="form-group" style="width: 45%" align="left">
          <label >类别</label><br>
          <select   name="classify" id="selectInfo">
            <option value="电视剧">电视剧</option>
            <option value="电影">电影</option>
            <option value="综艺">综艺</option>
            <option value="动漫">动漫</option>
          </select>
        </div>

         <div class="form-group" style="width: 45%" align="left">
          <label for="protagonist">主演</label>
          <input type="text" class="form-control"  id="protagonist"  name="protagonist" value="${video.protagonist}">
        </div>
        <div class="form-group" style="width: 45%" align="left">
          <label for="description">描述</label>
          <textarea  style="width: 520px; height: 100px;" class="form-control"  id="description"  name="description"/>${video.description}</textarea>
<%--          <input type="text" class="form-control"  id="description"  name="description" value="">--%>
        </div>
        <div class="form-group" style="width: 45%" align="left">
          <label for="v_url">播放链接</label>
          <input type="text" class="form-control"  id="v_url"  name="v_url" value="${video.v_url}">
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
  <script>

    $("select").find("option[value='${video.classify}']").attr("selected",true);


  </script>
</html>
