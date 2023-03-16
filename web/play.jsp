<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>详情</title>
    <link rel="stylesheet" href="css/bootstrasp.min.css">
    <script src="js/jquery/jquery.js"> </script>
    <script src="js/jquery/popper.js"></script>
    <script src="js/bootstrap/bootstrap.js"></script>
    <link rel="stylesheet" href="css/index.css">
    <link rel="stylesheet" href="css/pannel.css">
    <script src="js/login.js" type="text/javascript"></script>
    <script>
    function addCollect(){
        let user="${user.uid}";
        if(user!=''){
            $.ajax({
                url: "${pageContext.request.contextPath}/collectionManage?operator=addCollection&uid=${user.uid}&vid=${video.vid}",
                type:'GET',
                dataType:'text',
                success:function (data){
                    if(data==1)
                        alert("收藏成功!");
                    else
                        alert("该视频已被收藏！");
                }
            });
        }else{
            alert("请登录！");
        }
    }
    </script>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>


<!-- banner部分 -->
<div class="banner ">
    <div class="playpannel">
        <div class="left_info">
            <div class="imgbox">
                <img src=${video.v_img} style="width:220px;heght:308px">
            </div>
            <div class="info_box">
                <p style="position: relative">
                   <span style="margin-right: 50px;font-size:25px;font-weight: 400;;color: #ff6022;" > ${video.v_title}</span>
                        <em style="font-size: 15px;">${video.classify}</em>
                        <span style="color: #ff6022;font-size: 20px;position: absolute;right: 25px">${video.score}</span>
                </p>
                <p>主　演：${video.protagonist}  </p>
                <p style="overflow: hidden;color: #848494;font-size: 13px;">简　介：${video.description}</p>

                <ul class="playarr">
                    <a href="${video.v_url}"><li>1</li></a>
                    <a href="${video.v_url}"><li>2</li></a>
                    <a href="${video.v_url}"><li>3</li></a>
                    <a href="${video.v_url}"><li>4</li></a>
                    <a href="${video.v_url}"><li>5</li></a>
                    <a href="${video.v_url}"><li>6</li></a>
                    <a href="${video.v_url}"><li>7</li></a>
                    <a href="${video.v_url}"><li>8</li></a>
                    <a href="${video.v_url}"><li>9</li></a>
                    <a href="${video.v_url}"><li>10</li></a>
                    <a href="${video.v_url}"><li>11</li></a>
                    <a href="${video.v_url}"><li>12</li></a>
                    <a href="${video.v_url}"><li>13</li></a>
                    <a href="${video.v_url}"><li>14</li></a>
                    <a href="${video.v_url}"><li>15</li></a>
                    <a href="${video.v_url}"><li>...</li></a>
                </ul>
                <button type="button" class="btn btn-success add"
                        onclick=addCollect()>添加到收藏</button>
            </div>
        </div>
        <div class="right_info">
            <h3 class="top_tittle">热搜榜单</h3>
            <ul class="search_top">
                <li><span class="font_top">1</span><a href="${pageContext.request.contextPath}/videoManage?operator=videoDetails&vid=31">西行纪 第4季</a></li>
                <li><span class="font_top">2</span><a href="${pageContext.request.contextPath}/videoManage?operator=videoDetails&vid=1">特战荣耀</a></li>
                <li><span class="font_top">3</span><a href="${pageContext.request.contextPath}/videoManage?operator=videoDetails&vid=21">乘风破浪 第三季</a></li>
                <li><span>4</span><a href="${pageContext.request.contextPath}/videoManage?operator=videoDetails&vid=3">穿越火线</a></li>
                <li><span>5</span><a href="${pageContext.request.contextPath}/videoManage?operator=videoDetails&vid=2">雪中悍刀行</a></li>
                <li><span>6</span><a href="${pageContext.request.contextPath}/videoManage?operator=videoDetails&vid=25">王牌对王牌 第3季</a></li>
                <li><span>7</span><a href="${pageContext.request.contextPath}/videoManage?operator=videoDetails&vid=15">夜舍</a></li>
                <li><span>8</span><a href="${pageContext.request.contextPath}/videoManage?operator=videoDetails&vid=12">九叔之古棺奇案1</a></li>
                <li><span>9</span><a href="${pageContext.request.contextPath}/videoManage?operator=videoDetails&vid=36">独步逍遥</a></li>
                <li><span>10</span><a href="${pageContext.request.contextPath}/videoManage?operator=videoDetails&vid=22">闪光的乐队</a></li>
            </ul>
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
