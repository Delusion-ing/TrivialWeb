<%--
  Created by IntelliJ IDEA.
  User: wzx
  Date: 2020/9/28
  Time: 15:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>途牛旅游网-登录</title>
    <link rel="stylesheet" type="text/css" href="css/common.css">
    <link rel="stylesheet" type="text/css" href="css/login.css">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <!--导入angularJS文件-->
    <script src="js/angular.min.js"></script>
    <!--导入jquery-->
    <script src="js/jquery-3.3.1.js"></script>
    <script type="text/javascript" >
        function checkUserName(){
            //获取输入框的值
            var username = $("#username").val();

            //正则表达式  定义一个规则，执行test方法，符合规则返回true，否则返回false

            var reg =  /^\w{8,20}$/ ;

            var flag = reg.test(username); //判断

            //如果符合要求，设置输入框边框是正常，否则设置红色

            if(flag){
                $("#username").css("border","");
            }else{
                $("#username").css("border","1px solid red");
            }
            //alert(flag)
            return flag;
        }

        function checkPassword(){
            //判断密码输入框的值是否合法
            var username = $("#password").val();
            var reg =  /^\w{8,20}$/ ;

            var flag = reg.test(username); //判断
            if(flag){
                $("#password").css("border","");//无色框
            }else{
                $("#password").css("border","1px solid red");//红框
            }
            //alert(flag)
            return flag;
        }
        $(function () {
            $("#errorMsg").html("");
            // 判断两个输入框架的是否格式正确
            $("#username").blur(checkUserName);//输入框失去焦点
            // 如果正确，使用ajax发送请求到servlet
            $("#password").blur(checkPassword);

            $("#btn_login").click(function () {
                //要求两个值正确，我们才做提交
                if(checkUserName()&&checkPassword()){
                    var un = $("#username").val()
                    var pw = $("#password").val()
                    //alert(un+pw)
                    //写提交
                    $.ajax({
                        url:"loginServlet",
                        async:true,
                        data:"username="+un+"&password="+pw,
                        type:"post",
                        dataType:"json",
                        success:function (data) {
                           // alert(data)  {"code":1,"data":"登录成功"}
                            if(1 == data.code){
                                //跳转到主页 index.html
                                $("#errorMsg").html("");
                                window.location="index.html"
                            }else{
                                //显示在界面上
                                $("#errorMsg").html(data.data);
                            }
                        },
                        error:function () {
                            alert("服务器发生了错误")
                        }
                    });
                }
            })


        })



    </script>
</head>

<body>
<!--引入头部-->
<div id="header">

</div>
<!-- 头部 end -->
<section id="login_wrap">
    <div class="fullscreen-bg" style="background: url(images/login_bg.png);height: 532px;">

    </div>
    <div class="login-box">
        <div class="title">
            <img src="images/login_logo.png" alt="">
            <span>欢迎登录途牛旅游账户</span>
        </div>
        <div class="login_inner">

            <!--登录错误提示消息-->
            <div id="errorMsg" class="alert alert-danger" ></div>
            <form id="loginForm" action="" method="post" accept-charset="utf-8">
                <input type="hidden" name="action" value="login"/>
                <input id="username" name="username" type="text" placeholder="请输入账号" autocomplete="off">
                <input id="password" name="password" type="text" placeholder="请输入密码" autocomplete="off">
                <div class="verify">
                    <input name="check" type="text" placeholder="请输入验证码" autocomplete="off">
                    <span><img src="checkCode" alt="" onclick="changeCheckCode(this)"></span>
                    <script type="text/javascript">
                        //图片点击事件
                        function changeCheckCode(img) {
                            img.src="checkCode?"+new Date().getTime();
                        }
                    </script>
                </div>

                <div class="submit_btn" >
                    <button id="btn_login" type="button">登录</button>
                    <div class="auto_login">
                        <input type="checkbox" name="" class="checkbox">
                        <span>自动登录</span>
                    </div>
                </div>
            </form>
            <div class="reg">没有账户？<a href="javascript:;">立即注册</a></div>
        </div>
    </div>
</section>
<!--引入尾部-->
<div id="footer">

</div>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="js/jquery-1.11.0.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>
<!--导入布局js，共享header和footer-->
<script type="text/javascript" src="js/include.js"></script>
</body>
</html>