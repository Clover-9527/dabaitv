<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>收藏列表</title>
  <link rel="stylesheet" href="css/bootstrasp.min.css">
  <script src="js/jquery/jquery.js"> </script>
  <script src="js/jquery/popper.js"></script>
  <script src="js/bootstrap/bootstrap.js"></script>
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
    <div style="display: none;margin: 200px 500px;width:500px " id="collectMessage">
     <h1>您还没有收藏视频!</h1>
    </div>
    <table border="1" class="table table-hover collection_table" id="t">
      <thead>
      <tr>
        <th>剧名</th>
        <th>类型</th>
        <th>评分</th>
        <th>收藏时间</th>
        <th>操作</th>
      </tr>
      </thead>
      <tbody id="cbody">

      </tbody>
    </table>
  </div>
</div>
<!-- banner结束 -->



<!-- footer -->
<jsp:include page="footer.jsp"></jsp:include>
<em id="download"></em>
<script src="js/index.js" type="text/javascript"></script>
</body>
<script type="text/javascript">
  $('.navbar ul li a').eq(6).css('color', '#59be06');

  let collectionList="${collectionList.vid}";
  if(collectionList==null||collectionList==""){
    $.ajax({
      url: "${pageContext.request.contextPath}/collectionManage?operator=showCollections",
      type:'GET',
      dataType:'JSON',
      success:function (data){
        if(data=="0"){
          window.location.replace("login.jsp");
        }
        let cbody=$('#cbody');
        for(let i in data){
          let html="";
          let date = new Date(data[i].collectTime);
          let y = date.getFullYear();
          let m = date.getMonth() + 1;
          m = m < 10 ? ('0' + m) : m;
          let d = date.getDate();
          d = d < 10 ? ('0' + d) : d;
          let h = date.getHours();
          h = h < 10 ? ('0' + h) : h;
          let minute = date.getMinutes();
          minute = minute < 10 ? ('0' + minute) : minute;
          let time= y + '-' + m + '-' + d + ' ' + h + ':' + minute;

          html=  "<tr><td><span style='color: #ff5c38'>"+data[i].v_title+"</span></td>"+
                  "<td>"+data[i].classify+"</td>"+
                  "<td>"+data[i].score+"</td>"+
                  "<td>"+time+"</td>"+
                  "<td><a href='#' onclick="+'play('+data[i].vid+')'+">播放&nbsp;&nbsp;</a>"+
                  "<a href='#' onclick="+'del('+data[i].vid+')'+">删除</a></td></tr>";
          cbody.append(html);
        }

        if(cbody.html().trim()==""){
          $(".table").remove();
          $('#collectMessage').css('display','');
        }

      }
    });

    function play( vid ) {
        location.href="${pageContext.request.contextPath}/videoManage?operator=videoDetails&vid="+vid;
    }

    function del( vid ) {
      if( confirm("确定要删除吗?") ){
        location.href="${pageContext.request.contextPath}/collectionManage?operator=deleteCollection&vid="+vid;
      }
    }
  }


</script>

</html>
