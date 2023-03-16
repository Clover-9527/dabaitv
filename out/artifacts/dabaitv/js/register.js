//1.验证用户名是否存在
$(function () {
	$("label input").blur(function () {
		//验证昵称是否合法
		if ($("#nickname").val().trim() == "") {
			$("#nicknameCheck").css('color', 'red').html("请输入昵称!");
		} else {
			$("#nicknameCheck").css('color', 'blue').html("✔");
		}

		//验证手机号是否合法
		if ($("#phone").val().trim() == "") {
			$("#phoneCheck").css('color', 'red').html("请输入手机号!");
		} else {
			$.ajax({
				type: 'get',
				url: "/dabaitv/userManage?operator=queryPhone&phone=" + $("#phone").val(),
				dataType: 'text',
				success: function (data) {
					if (data == 1) {
						$("#phoneCheck").css('color', 'blue').html("✔");
					} else {
						$("#phoneCheck").css('color', 'red').html("该手机号已被绑定!");
					}
				}
			});
		}

		//验证用户名是否合法
		if ($("#username").val().trim() == "") {
			$("#usernameCheck").css('color', 'red').html("请输入用户名!");
		} else {
			$.ajax({
				type: 'get',
				url: "/dabaitv/userManage?operator=findName&username=" + $("#username").val(),
				dataType: 'text',
				success: function (data) {
					if (data == 1) {
						$("#usernameCheck").css('color', 'blue').html("✔");
					} else {
						$("#usernameCheck").css('color', 'red').html("该用户名已被注册");
					}
				}
			});
		}

		//验证密码是否合法
		let pwd1 = $('#pwd1').val();
		if ($.trim(pwd1) == "") {
			$('#pwdCheck1').css('color', 'red').html('请输入密码!');
		} else {
			$('#pwdCheck1').css('color', 'blue').html('✔');
		}


		//验证两次输入的密码是否相同
		let pwd2 = $('#pwd2').val();
		if ($.trim(pwd2) == "") {
			$('#pwdCheck').css('color', 'red').html('请输入密码!');
		} else if (pwd1 == pwd2) {
			$('#pwdCheck').css('color', 'blue').html('✔');
		} else {
			$('#pwdCheck').css('color', 'red').html('两次密码不同!');
		}

	});
});


//获取验证码
	var clock = '';
	var nums = 60;
	var btn;

	function sendCode(thisBtn) {
		let box = document.querySelector("#phone").value;
		let mes = document.querySelector("#mes");
		if (box.trim() != '') {
			mes.innerText = "";
			btn = thisBtn;
			btn.disabled = true; //将按钮置为不可点击
			btn.style.width = "115px";
			btn.value = "发送中";
			clock = setInterval(doLoop, 1000); //一秒执行一次
		} else {
			mes.innerText = "请输入手机号!";
		}
	}

	function doLoop() {
		nums--;
		if (nums > 0) {
			btn.value = '重新获取' + "(" + nums + ")";
		} else {
			clearInterval(clock); //清除js定时器
			btn.disabled = false;
			btn.value = '重新获取 ';
			btn.style.width = "100px";
			nums = 60; //重置时间
		}
	}

