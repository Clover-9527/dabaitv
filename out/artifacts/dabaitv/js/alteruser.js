
$(function () {
	//昵称是否为空
	$("label input").blur(function () {
			if ($("#nickname").val().trim() == "") {
				$("#nicknameCheck").css('color', 'red').html("请输入昵称!");
			} else {
				$("#nicknameCheck").css('color', 'blue').html("✔");
			}

	 //手机号是否为空
		if ($("#phone").val().trim() == "") {
			$("#phoneCheck").css('color', 'red').html("请输入手机号!");
		} else {
			$("#phoneCheck").css('color', 'blue').html("✔");
		}


	 //密码是否为空
		if ($("#password").val().trim() == "") {
			$("#passwordCheck").css('color', 'red').html("请输入原密码!");
		} else {
			$("#passwordCheck").css('color', 'blue').html("✔");
		}

	//新密码是否为空
	// $("#pwd2").focus(function () {
		if ($("#pwd1").val().trim() == "") {
			$("#pwd1Check").css('color', 'red').html("请输入新密码!");
		} else {
			$("#pwd1Check").css('color', 'blue').html("✔");
		}

		//验证输入的密码是否相同
			let pwd1 = $('#pwd1').val();
			let pwd2 = $('#pwd2').val();
			if ($.trim(pwd1) == "" || $.trim(pwd2) == "") {
				$('#pwdCheck').css('color', 'red').html('请输入确认密码!');
			} else if (pwd1 == pwd2) {
				$('#pwdCheck').css('color', 'blue').html('✔');
			} else {
				$('#pwdCheck').css('color', 'red').html('两次密码不同!');
			}
		});

	});


