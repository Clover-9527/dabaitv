/*模拟数据，数组集合*/

        var mov = [
        	["雪中悍刀行", "电视剧", 8.9],
        	["不老奇事", "电影", 7.8],
        	["第一炉香", "电影", 7.7],
			["埃博拉前线", "电视剧", 9.2],
			["令人心动的offer 第3季", "综艺", 8.2]
        ];
        //获取到tbody控件，便于后边添加移除操作
        var tbody = document.querySelector("tbody");
        //按评分排序
        function s(a, b) {
        	return a[2] - b[2];
        }
        mov.sort(s);
        mov.reverse(s);

        //遍历循环，添加行
        for (var i = 0; i < mov.length; i++) {
        	//创建行元素
        	var tr = document.createElement("tr");
        	//遍历每组对象，添加每行单元格的添加
        	for (var j = 0; j < mov[i].length; j++) {
        		var td = document.createElement("td");
        		td.innerHTML = mov[i][j];
        		tr.appendChild(td);
        	}
        	//创建单元格，添加删除标签
        	var del = document.createElement("td")
        	del.innerHTML = '<a href="javascript:;" onclick= delTr(this)>删除</a>'
        	tr.appendChild(del)
        	//添加行节点
        	tbody.appendChild(tr);
        }

        //获取所有的a标签，添加点击事件进行删除
        var dels = document.querySelectorAll("a");
        // for (var i = 0; i < dels.length; i++) {
        //     console.log(dels[i]);
        //     dels[i].addEventListener("click",function(){
        //         this.parentElement.parentElement.remove();
        //     });
        // }
        function delTr(obj) {
        	obj.parentElement.parentElement.remove();
        }

var coll=document.querySelector('.collect');
coll.style.color='#59be06';