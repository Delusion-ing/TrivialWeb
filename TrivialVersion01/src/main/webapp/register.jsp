<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>注册</title>
    <link rel="stylesheet" type="text/css" href="css/common.css">
    <link rel="stylesheet" href="css/register.css">
    <!--导入jquery-->
    <script src="js/jquery-3.3.1.js"></script>
    <script type="text/javascript" >

        function checkUsername(){
            //判断一下账号输入是否合法
           var  username = $("#username").val();
           var  reg =  /^\w{8,20}$/ ;
           var flag = reg.test(username);
           if(flag){
               //不提示
               $("#username").css("border","");
           }else{
               //提示
               $("#username").css("border","1px solid red");
           }
           return flag;
        }

        function checkPassword(){
            //判断一下密码输入是否合法
            var  password = $("#password").val();
            var  reg =  /^\w{8,20}$/ ;
            var flag = reg.test(password);
            if(flag){
                //不提示
                $("#password").css("border","");
            }else{
                //提示
                $("#password").css("border","1px solid red");
            }
            return flag;
        }

        function checkEmail(){
            //判断一下密码输入是否合法
            var  email = $("#email").val();
            var  reg =   /^\w+@\w+\.\w+$/  ;
            var flag = reg.test(email);
            if(flag){
                //不提示
                $("#email").css("border","");
            }else{
                //提示
                $("#email").css("border","1px solid red");
            }
            return flag;
        }

        $(function () {
            //账号密码 手机号 邮箱 正则判断
            //格式正确不作提示，如果错误将边框改成红色
            $("#username").blur(checkUsername)
            $("#password").blur(checkPassword)
            $("#email").blur(checkEmail)

            $("#registerForm").submit(function () {
                //ajax提交
                if(checkUsername()&&checkPassword()&&checkEmail()){
                    //使用jquery
                    $.post("registerServlet",$("#registerForm"). serialize(),
                        function(data){
                           //{code:1,data:成功}

                            if(data.code == 1){
                                window.location="register_ok.jsp";
                            }else {
                                $("#errorMsg").html(data.data);
                            }
                        },"json");
                }
                return false;
            })
        });
    </script>
</head>
<body>
<!--引入头部-->
<div id="header">
    <%@include file="header.jsp"%>
</div>
<!-- 头部 end -->
<div class="rg_layout">
    <div class="rg_form clearfix">
        <div class="rg_form_left">
            <p>新用户注册</p>
            <p>USER REGISTER</p>
        </div>
        <div class="rg_form_center">
            <div id="errorMsg" class="alert alert-danger" ></div>
            <!--注册表单-->
            <form id="registerForm">

                <!--提交处理请求的标识符-->
                <input type="hidden" name="action" value="register">
                <table style="margin-top: 25px;">
                    <tr>
                        <td class="td_left">
                            <label for="username">用户名</label>
                        </td>
                        <td class="td_right">
                            <input type="text" id="username" name="username" placeholder="请输入账号">
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left">
                            <label for="password">密码</label>
                        </td>
                        <td class="td_right">
                            <input type="text" id="password" name="password" placeholder="请输入密码">
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left">
                            <label for="email">Email</label>
                        </td>
                        <td class="td_right">
                            <input type="text" id="email" name="email" placeholder="请输入Email">
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left">
                            <label for="name">姓名</label>
                        </td>
                        <td class="td_right">
                            <input type="text" id="name" name="name" placeholder="请输入真实姓名">
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left">
                            <label for="telephone">手机号</label>
                        </td>
                        <td class="td_right">
                            <input type="text" id="telephone" name="telephone" placeholder="请输入您的手机号">
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left">
                            <label for="sex">性别</label>
                        </td>
                        <td class="td_right gender">
                            <input type="radio" id="sex" name="sex" value="男" checked> 男
                            <input type="radio" name="sex" value="女"> 女
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left">
                            <label for="birthday">出生日期</label>
                        </td>
                        <td class="td_right">
                            <input type="date" id="birthday" name="birthday" placeholder="年/月/日">
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left">
                            <label for="check">验证码</label>
                        </td>
                        <td class="td_right check">
                            <input type="text" id="check" name="check" class="check">
                            <img src="checkCode" height="32px" alt="" onclick="changeCheckCode(this)">
                            <script type="text/javascript">
                                //图片点击事件
                                function changeCheckCode(img) {
                                    img.src="checkCode?"+new Date().getTime();
                                }
                            </script>
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left">
                        </td>
                        <td class="td_right check">
                            <input type="submit" class="submit" value="注册">
                            <span id="msg" style="color: #ff0000;"></span>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
        <div class="rg_form_right">
            <p>
                已有账号？
                <a href="#">立即登录</a>
            </p>
        </div>
    </div>
</div>
<!--引入尾部-->
<div id="footer">
    <%@include file="footer.jsp"%>
</div>
<!--导入布局js，共享header和footer-->


</body>
</html>