<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>大白影视</title>
  <link rel="stylesheet" type="text/css" href="css/index.css"/>
  <link rel="stylesheet" type="text/css" href="css/bootstrasp.min.css">
  <script src="js/jquery/jquery.js"> </script>
  <script src="js/jquery/popper.js"></script>
  <script src="js/bootstrap/bootstrap.js"></script>
  <link rel="stylesheet" type="text/css" href="css/pannel.css" />

</head>


<!-- 页面头部分开始 -->
  <jsp:include page="header.jsp"></jsp:include>
  <!-- 页面头部分结束 -->


<!-- banner部分 -->

<div class="banner ">
  <!-- 侧边二维码部分 -->
  <div class="qr_box">
    <div class="span_box">
      <span>扫码访问</span><br>
    </div>
    <div class="img_box">
      <img src="images/QR_Code.png" width="117px">
    </div>
  </div>



  <!-- 轮播图 -->
  <div class="clearfix ">
    <!-- 添加data-interval="1000"属性可控制轮播图展示时间 -->
    <div id="demo" class="carousel slide" data-ride="carousel">
      <!-- 指示符 -->
      <div class="carousel-indicators">
        <li data-target="#demo" data-slide-to="0" class="active"></li>
        <li data-target="#demo" data-slide-to="1"></li>
        <li data-target="#demo" data-slide-to="2"></li>
        <li data-target="#demo" data-slide-to="3"></li>
      </div>

      <!-- 轮播图片 -->
      <div class="carousel-inner">
        <div class="carousel-item active">
          <a href="${pageContext.request.contextPath}/videoManage?operator=videoDetails&vid=5"><img src="images/lb1.jpg"></a>
        </div>
        <div class="carousel-item">
          <a href="${pageContext.request.contextPath}/videoManage?operator=videoDetails&vid=11"><img src="images/lb2.jpg"></a>
        </div>
        <div class="carousel-item">
          <a href="${pageContext.request.contextPath}/videoManage?operator=videoDetails&vid=4"><img src="images/lb3.jpg"></a>
        </div>
        <div class="carousel-item">
          <a href="${pageContext.request.contextPath}/videoManage?operator=videoDetails&vid=2"><img src="images/lb4.jpg"></a>
        </div>
      </div>

      <!-- 左右切换按钮 -->
      <a class="carousel-control-prev" href="#demo" data-slide="prev">
        <span class="carousel-control-prev-icon"></span>
      </a>
      <a class="carousel-control-next" href="#demo" data-slide="next">
        <span class="carousel-control-next-icon"></span>
      </a>

    </div>

    <div style="border:2px dashed #2d3757;text-align:center;font-size:18px;height:60px;line-height:60px;width:100%;">
      看最新的影视动漫尽在【<font color="#FF0000"><b>大白影视</b></font>】
    </div>

  </div>

  <!-- 视频列表部分 -->
  <!--没有给高度 清除浮动-->
  <div class="banner-in container clearfix">
    <h2 class="font-b">热门推荐</h2>
    <ul id="hotUL">

    </ul>

  </div>


  <em id="tv"></em>
  <div class="banner-in container clearfix">
    <h2 class="font-b">电视剧</h2>
    <ul id="tvUL">

    </ul>

  </div>

  <em id="movie"></em>
  <div class="banner-in container  clearfix">
    <h2 class="font-b">电影</h2>
    <ul id="movieUL">

    </ul>

  </div>


  <em id="variety"></em>
  <div class="banner-in container  clearfix">
    <h2 class="font-b">综艺</h2>
    <ul id="varietyUL">

    </ul>

  </div>


  <em id="cartoon"></em>
  <div class="banner-in container  clearfix">
    <h2 class="font-b">动漫</h2>
    <ul id="cartoonUL">

    </ul>

  </div>

</div>
<!-- banner结束 -->

<jsp:include page="footer.jsp"></jsp:include>
<em id="download"></em>
</body>
<script src="js/index.js"></script>




<script type="text/javascript">
  // 如果直接访问没有数据，提交查新请求，刷新页面
  let videoList="${videoList}";
  if(videoList==null || videoList==""){
    $.ajax({
      url: "${pageContext.request.contextPath}/videoManage?operator=showall",
      type:'GET',
      dataType:'JSON',
      success:function (data){
        // let videoArr=JSON.parse(data);
        let videoArr=data;

        //随机播放(热门推荐)
        let arr=[];
        for(let index=0;index<10;index++){
            let j = Math.floor((Math.random()*40));
            if(arr.indexOf(j)==-1){
              arr.push(j);
            }else{
              index--;
            }
        }
        for(let i=0;i<10;i++){
          let htoul=$('#hotUL');
          let hothtml="";
          hothtml= "<li><a href=${pageContext.request.contextPath}/videoManage?operator=videoDetails&vid="+videoArr[arr[i]].vid+"> <img style="+'width:170px;height:238px;'+"+ src="+videoArr[arr[i]].v_img+"><span>"+videoArr[arr[i]].v_title+"</span></a></li>";
          htoul.append(hothtml);
        }




        let tvarr=[];
        let moviearr=[];
        let varietyarr=[];
        let cartoonarr=[];
        for(let i in videoArr){
          if(videoArr[i].classify=="电视剧"){
            tvarr.push(videoArr[i]);
          }else if(videoArr[i].classify=="电影"){
            moviearr.push(videoArr[i]);
          }else if(videoArr[i].classify=="综艺"){
            varietyarr.push(videoArr[i]);
          }else if(videoArr[i].classify=="动漫"){
            cartoonarr.push(videoArr[i]);
          }
        }

        for (let i=0;i<10;i++){
          let tvul=$('#tvUL');
          let tvhtml="";
          tvhtml=  "<li><a href=${pageContext.request.contextPath}/videoManage?operator=videoDetails&vid="+tvarr[i].vid+"> <img src="+tvarr[i].v_img+"><span>"+tvarr[i].v_title+"</span></a></li>"
          tvul.append(tvhtml);

          let movieul=$('#movieUL');
          let moviehtml="";
          moviehtml=  "<li><a href=${pageContext.request.contextPath}/videoManage?operator=videoDetails&vid="+moviearr[i].vid+"> <img src="+moviearr[i].v_img+"><span>"+moviearr[i].v_title+"</span></a></li>"
          movieul.append(moviehtml);

          let varietyul=$('#varietyUL');
          let varietyhtml="";
          varietyhtml=  "<li><a href=${pageContext.request.contextPath}/videoManage?operator=videoDetails&vid="+varietyarr[i].vid+"> <img src="+varietyarr[i].v_img+"><span>"+varietyarr[i].v_title+"</span></a></li>"
          varietyul.append(varietyhtml);

          let cartoonul=$('#cartoonUL');
          let cartoonhtml="";
          cartoonhtml=  "<li><a href=${pageContext.request.contextPath}/videoManage?operator=videoDetails&vid="+cartoonarr[i].vid+"> <img src="+cartoonarr[i].v_img+"><span>"+cartoonarr[i].v_title+"</span></a></li>"
          cartoonul.append(cartoonhtml);
        }
      }
    });

    // 第一次进入页面刷新一次，仅一次
    if (location.href.indexOf("#") == -1) {
      //在当前页面地址加入"#"，使下次不再进入此判断
      location.href = location.href + "#";
      location.reload();
    }

    if (location.href.indexOf("##") == -1) {
      location.href = location.href + "#";
      location.reload();
    }
  }

</script>

</html>
