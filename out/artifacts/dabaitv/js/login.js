$(function () {
    $("#username").blur(function () {
        if ($("#username").val().trim() == "") {
            $("#usernameCheck").css('color', 'red').html("请输入用户名!");
        } else {
            $("#usernameCheck").css('color', 'blue').html("✔");
        }
    });

    $("#password").blur(function () {
        if ($("#password").val().trim() == "") {
            $("#passwordCheck").css('color', 'red').html("请输入密码!");
        } else {
            $("#passwordCheck").css('color', 'blue').html("✔");
        }
    });

    $("#yzm").blur(function () {
        if ($("#yzm").val().trim() == "") {
            $("#yzmCheck").css('color', 'red').html("请输入验证码!");
        } else {
            $.ajax({
                type: 'get',
                url: "/dabaitv/userManage?operator=checkCode&code_form=" + $("#yzm").val(),
                dataType: 'text',
                success: function (data) {
                    if (data == 1) {
                        $("#yzmCheck").css('color', 'blue').html("✔");
                    } else {
                        $("#yzmCheck").css('color', 'red').html("验证码错误!");
                    }
                }
            });
        }
    });

});