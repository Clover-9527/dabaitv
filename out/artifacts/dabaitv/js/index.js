// //利用排他思想设置导航栏中按钮的颜色
// var btns = document.querySelectorAll(".navbar ul li a");
// // btns[0].style.color = "#59be06";
// for (var i = 0; i < btns.length; i++) {
// 	btns[i].onclick = function() {
// 		//1.先把其他颜色去掉
// 		for (var i = 0; i < btns.length; i++) {
// 			btns[i].style.color = '';
// 		}
// 		//2.让当前元素背景色改为绿色
// 		this.style.color = '#59be06';
// 	}
// }



//监听滚动条离顶部的距离，动态改变导航栏颜色
$(function() {
    var length = $('.navbar ul li').length;
    $(document).scroll(function() {
        var scrop = $(document).scrollTop(); //获取页面滚动条离顶部的距离
        // console.log(scrop);
        for (var i = 0; i < 6; i++) {
            if(scrop<1000){
                $('.navbar ul li a').eq(i).css('color', '');
                $('.navbar ul li a').eq(0).css('color', '#59be06');
            }else if (scrop >= 1000+i*760 && scrop < 1000*(i+1)*760) {
                $('.navbar ul li a').css('color', '');
                $('.navbar ul li a').eq(i+1).css('color', '#59be06');
            } else if (scrop >= 3600) {
                $('.navbar ul li a').eq(5).css('color', '#59be06');
                $('.navbar ul li a').eq(4).css('color', '');
            }
        }
    })
})


// 设置导航栏时间
function getTime() {
    var date = new Date();
    var nowTime = date.getTime(); //获取1970 1 1 0时0分0秒到time时间的毫秒数

    var times = parseInt(nowTime / 1000);
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var dates = date.getDate();
    var day = date.getDay();
    var arr = ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"];
    var hour = date.getHours(); //获取当前小时数(0-23)
    var minutes = date.getMinutes(); //获取当前分钟数(0-59)
    var second = date.getSeconds(); //获取当前秒数(0-59)

    return year + "年" + month + "月" + dates + "日" + hour + "时" + minutes + "分" + second + "秒 " + arr[day];
}
var span = document.querySelector("#time");
var timer = setInterval(function() {
    span.innerText = getTime();
}, 1000);



//转跳播放页面play.html
// var playimg = document.querySelectorAll(".banner-in ul li a");
// for (var i = 0; i < playimg.length; i++) {
//     playimg[i].onclick = function() {
//         this.href = "play.html";
//     }
// }
