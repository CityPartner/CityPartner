$(document).ready(function () {

    //手机号格式判断
    $("#loginPhone").on('input', function (e) {
        var pattern = /^1[34578]\d{9}$/;
        var b = pattern.test($("#loginPhone").val());
        if (b == false) {

            $("#format_phone").text("手机格式错误");
            $("#format_phone").show();
            $("#login").attr('disabled', true);
            $("#login").css("background-color", "#ececec");

        }
        if (b == true) {
            // console.log("123");

            var regExp = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,21}$/;
            var ba = regExp.test($("#pwd").val());
            if (ba == false) {
                $("#format_pwd").text("密码格式不对");
                $("#format_pwd").show();

                $("#login").attr('disabled', true);
                $("#login").css("background-color", "#ececec");
            }
            if (ba == true) {

                $("#login").attr('disabled', false);
                $("#login").css("background-color", "#2d9cff");
            }
            $("#format_phone").hide();


        }
    });

    //校验密码是否6位，不能全是数字或者字母
    $("#pwd").on('input', function (e) {
        var regExp = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,21}$/;
        var b = regExp.test($("#pwd").val());
        if (b == false) {
            $("#format_pwd").text("密码格式不对");
            $("#format_pwd").show();

            $("#login").attr('disabled', true);
            $("#login").css("background-color", "#ececec");
        }
        if (b == true) {

            var pattern = /^1[34578]\d{9}$/;
            var ba = pattern.test($("#loginPhone").val());
            if (ba == false) {

                $("#format_phone").text("手机格式错误");
                $("#format_phone").show();
                $("#login").attr('disabled', true);
                $("#login").css("background-color", "#ececec");

            }
            if (ba == true) {
                // console.log("123");
                $("#format_phone").hide();
                $("#login").attr('disabled', false);
                $("#login").css("background-color", "#2d9cff");

            }

            $("#format_pwd").hide();
            // $("#login").attr('disabled', false);
            // $("#login").css("background-color", "#e21323");
        }

    });

    //登录验证
    $("#login").click(function () {
        var param = {};
        param.phone = $("#loginPhone").val();
        param.pwd = $("#pwd").val();
        // alert(param.pwd);
        if (param.phone == ""|| param.pwd == ''){
            swal({
                title: "<span style='color:#f6d224;font-size: 26px'>手机号、密码不能为空！<span>",
                text: "2秒后自动关闭。",
                timer: 2000,
                showConfirmButton: true,
                html: true
            });
            $("#loading_login").css("display","none");
            return;
        }

        $.ajax({
            async: false,
            type: "POST",
            url: "/login",//注意路径
            data: param,
            dataType: "json",
            success: function (data) {

                var itm = data;

                if (itm == "1") {
                    swal({
                        title: "<span style='color:#6ddb8d;font-size: 26px'>登陆成功，正在跳转！<span>",
                        text: "2秒后自动关闭。",
                        timer: 2000,
                        showConfirmButton: false,
                        html: true
                    });
                    function jump(count) {
                        window.setTimeout(function(){
                            count--;
                            if(count > 0) {
                                $('#num').attr('innerHTML', count);
                                jump(count);
                            } else {
                                window.location.href="/project/my";
                                return;
                            }
                        }, 1000);
                    }
                    jump(2);

                }
                if (itm == "2") {
                    swal({
                        title: "<span style='color:#f6d224;font-size: 26px'>该用户不存在，请注册！<span>",
                        text: "2秒后自动关闭。",
                        timer: 2000,
                        showConfirmButton: true,
                        html: true
                    });
                }
                if (itm == "3") {
                    swal({
                        title: "<span style='color:#ef3737;font-size: 26px'>密码错误！<span>",
                        text: "2秒后自动关闭。",
                        timer: 2000,
                        showConfirmButton: true,
                        html: true
                    });
                }
                if (itm == "4") {
                    swal({
                        title: "<span style='color:#ef3737;font-size: 26px'>系统异常！<span>",
                        text: "2秒后自动关闭。",
                        timer: 2000,
                        showConfirmButton: true,
                        html: true
                    });
                }

            },error:function (data) {
                swal({
                    title: "<span style='color:#ef3737;font-size: 26px'>系统异常！<span>",
                    text: "2秒后自动关闭。",
                    timer: 2000,
                    showConfirmButton: false,
                    html: true
                });

            }
        });
        $("#loading_login").css("display","none");
    });

});