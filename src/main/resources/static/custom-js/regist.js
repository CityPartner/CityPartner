$(document).ready(function () {

    // alert("123")

    //失去焦点的时候，掩藏样式
    // $('#userPhone').blur(function () {
    //     $("#format_phone").hide();
    // });
    // $('#repwd').blur(function () {
    //     $("#checkPwd").hide();
    // });
    //
    //校验手机号格式

    $("#userPhone").on('input', function (e) {
        var pattern = /^1[34578]\d{9}$/;
        var b = pattern.test($("#userPhone").val());
        if (b == false) {
            $("#format_phone").show();
            $("#getCode").attr('disabled', true);
            $("#getCode").css("background-color", "#ececec");
        }
        if (b == true) {
            console.log("123");
            $("#format_phone").hide();
            $("#getCode").attr('disabled', false);
            $("#getCode").css("background-color", "transparent");
        }
    });
    //判断第二次密码输入的正确性
    $("#repwd").on('input', function (e) {
        var params = {};
        params.pwd = $("#pwd").val();
        params.repwd = $("#repwd").val();
        if (params.pwd == params.repwd) {
            $("#regExpPwd").text("第二次密码输入错误");
            $("#checkPwd").hide();
            $("#regist_login").attr('disabled', false);
            $("#regist_login").css("background-color", "#e64340");

        }
        if (params.pwd != params.repwd) {
            // console.log("123");
            $("#regExpPwd").text("第二次密码输入错误");
            $("#checkPwd").show();
            $("#regist_login").attr('disabled', true);
            $("#regist_login").css("background-color", "#999");

        }
    });

    //校验密码是否6位
    $("#pwd").on('input', function (e) {
        var regExp = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,21}$/;
        var b = regExp.test($("#pwd").val());
        if (b == false) {
            $("#regExpPwd").text("密码格式不对");
            $("#checkPwd").show();
        }
        if (b == true) {

            $("#checkPwd").hide();
        }

    });
    var curCount;//当前剩余秒数
    var InterValObj; //timer变量，控制时间
    function SetRemainTime() {
        if (curCount == 0) {
            window.clearInterval(InterValObj);//停止计时器
            $("#getCode").removeAttr("disabled");//启用按钮
            $("#getCode").text("重新发送验证码");
            /**
             * 清除验证码
             */
            var temp = {};
            temp.phone = $("#userPhone").val();
            // alert(temp.phone);
            $.ajax({
                async: false,
                type: "POST",
                url: "deleteCode",//注意路径
                data: temp,
                dataType: "json",
                success: function (data) {
                    var itemb = data.data;
                    if (itemb == "1") {

                    }
                    if (itemb == "2") {
                        swal({
                            title: "<span style='color:#ef3737;font-size: 26px'>系统异常！<span>",
                            text: "2秒后自动关闭。",
                            timer: 2000,
                            showConfirmButton: true,
                            html: true
                        });
                    }

                },
                error: function (data) {
                    swal({
                        title: "<span style='color:#ef3737;font-size: 26px'>系统异常！<span>",
                        text: "2秒后自动关闭。",
                        timer: 2000,
                        showConfirmButton: true,
                        html: true
                    });

                }
            });

        }
        else {
            curCount--;
            $("#getCode").text(curCount + "秒再获取");
        }

    }

    //获取验证码
    $("#getCode").click(function (e) {
        // alert("123");

        var count = 60; //间隔函数，1秒执行
        curCount = count;
        var params = {};
        params.phone = $("#userPhone").val();
        if (params.phone == "") {

            swal({
                title: "<span style='color:#f6d224;font-size: 26px'>手机号不能为空！<span>",
                text: "2秒后自动关闭。",
                timer: 2000,
                showConfirmButton: true,
                html: true
            });
            // clearInterval(int);
            return;
        }

        //设置获取验证码获取时间
        $("#getCode").attr("disabled", "true");
        $("#getCode").text(curCount + "秒再获取");
        InterValObj = window.setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次


        $.ajax({
            async: false,
            type: "POST",
            url: "MallUserRegistered",//注意路径
            data: params,
            dataType: "json",
            success: function (data) {
                //console.log(JSON.stringify(data,null,4));
                var itm = data.data;
                if (itm == "1") {

                    swal({
                        title: "<span style='color:#6ddb8d;font-size: 26px'>验证码已发送！<span>",
                        text: "2秒后自动关闭。",
                        timer: 2000,
                        showConfirmButton: false,
                        html: true
                    });

                }
                if (itm == "2") {
                    swal({
                        title: "<span style='color:#f6d224;font-size: 26px'>该手机号已被注册！<span>",
                        text: "2秒后自动关闭。",
                        timer: 2000,
                        showConfirmButton: false,
                        html: true
                    });
                }
                if (itm == "3") {
                    swal({
                        title: "<span style='color:#ef3737;font-size: 26px'>验证码获取失败！<span>",
                        text: "2秒后自动关闭。",
                        timer: 2000,
                        showConfirmButton: false,
                        html: true
                    });
                }
                if (itm == "4") {
                    swal({
                        title: "<span style='color:#f6d224;font-size: 26px'>验证码获取频繁！<span>",
                        text: "2秒后自动关闭。",
                        timer: 2000,
                        showConfirmButton: false,
                        html: true
                    });
                }
            },
            error: function (data) {
                //console.log(JSON.stringify(data,null,4));
                swal({
                    title: "<span style='color:#ef3737;font-size: 26px'>验证码获取失败！<span>",
                    text: "2秒后自动关闭。",
                    timer: 2000,
                    showConfirmButton: false,
                    html: true
                });
                //clearInterval(int);
            }
        });


    });


    //注册登录
    $("#but_regist").click(function () {
       var params = {};
        params.userPhone = $("#userPhone").val();
        params.code = $("#code").val();
        params.pwd = $("#pwd").val();
        params.repwd = $("#repwd").val();
        // alert(JSON.stringify(params));
        if (params.userPhone == "" || params.code == "" || params.pwd == "" || params.repwd == "") {

            swal({
                title: "<span style='color:#f6d224;font-size: 26px'>验证码不能为空！<span>",
                text: "2秒后自动关闭。",
                timer: 2000,
                showConfirmButton: true,
                html: true
            });
            return;
        }

       


        // console.log(JSON.stringify(params,null,4));

        $.ajax({
            async: false,
            type: "POST",
            url: "RegistLogin",//注意路径
            data: params,
            dataType: "json",
            success: function (data) {
                var item = data.data;
                if (item == "1") {

                    swal({
                        title: "<span style='color:#6ddb8d;font-size: 26px'>注册成功！<span>",
                        text: "2秒后自动关闭。",
                        timer: 2000,
                        showConfirmButton: false,
                        html: true
                    });
                    window.location.href="index";
                    return;

                }
                if (item == "2") {
                    swal({
                        title: "<span style='color:#ef3737;font-size: 26px'>手机号错误！<span>",
                        text: "2秒后自动关闭。",
                        timer: 2000,
                        showConfirmButton: false,
                        html: true
                    });
                }
                if (item == "3") {
                    swal({
                        title: "<span style='color:#ef3737;font-size: 26px'>验证码错误！<span>",
                        text: "2秒后自动关闭。",
                        timer: 2000,
                        showConfirmButton: false,
                        html: true
                    });
                }
                if (item == "4") {
                    swal({
                        title: "<span style='color:#ef3737;font-size: 26px'>未知错误！<span>",
                        text: "2秒后自动关闭。",
                        timer: 2000,
                        showConfirmButton: false,
                        html: true
                    });
                }
                if (item == "5") {
                    swal({
                        title: "<span style='color:#f6d224;font-size: 26px'>验证码过期！<span>",
                        text: "2秒后自动关闭。",
                        timer: 2000,
                        showConfirmButton: false,
                        html: true
                    });
                }
                if (item == "6") {
                    swal({
                        title: "<span style='color:#f6d224;font-size: 26px'>该手机号已被注册！<span>",
                        text: "2秒后自动关闭。",
                        timer: 2000,
                        showConfirmButton: false,
                        html: true
                    });
                }
            },
            error: function (data) {
                swal({
                    title: "<span style='color:#ef3737;font-size: 26px'>注册失败！<span>",
                    text: "2秒后自动关闭。",
                    timer: 2000,
                    showConfirmButton: true,
                    html: true
                });
            }
        });

    });
});