<%--
  Created by IntelliJ IDEA.
  User: VULCAN
  Date: 2018/12/15
  Time: 17:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>登陆</title>
    <link href="/dist/css/signin.css" rel="stylesheet">
    <link href="/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="/dist/jquery-3.3.1.min.js"></script>
    <script src="/dist/js/bootstrap.min.js"></script>
    <script src="/dist/js/sweetalert.min.js"></script>
    <style type="text/css">
        body {
            background: url('imges/background.png') no-repeat center center;
            background-size: 100% 100%;
            background-attachment: fixed;
            background-color: #CCCCCC;
        }
    </style>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#adduser").click(function () {
                window.location.href = "/signup.html";
            });
        });

        function formadd() {
            var formData = new FormData();
            formData.append('username', $("#inputUser").val());
            formData.append('password', $("#inputPassword").val());
            $.ajax({
                type: "POST",//方法类型
                //dataType: "json",//预期服务器返回的数据类型
                url: "/login",//url
                data: formData,
                processData: false,
                contentType: false,
                async: false,
                success: function (result) {
                    if (result.state == "success") {
                        window.location.href = result.address;
                    } else if (result.state == "failed") {
                        var user = typeof(result.usercontent) == "undefined" ? "" : result.usercontent + " "
                        var pass = typeof(result.passcontent) == "undefined" ? "" : result.passcontent + " "
                        var cont = typeof(result.msg) == "undefined" ? "" : result.msg + " "
                        $("#content").html(user + pass + cont)
                    }
                },
                error: function () {
                    swal("出错了")
                }
            });
            return false;
        }

    </script>
</head>
<body class="text-center">
<div class="container">
    <form class="form-signin" onsubmit="return formadd()">
        <div class="row">
            <div class="jumbotron">
                <h4 style="color: #c9302c" id="content"></h4>
                <div class="col-md-16">
                    <div class="page-header">
                        <div class="input-group input-group-lg">
                            <label for="inputUser" class="sr-only">用户名</label>
                            <input height="400px" name="name" type="text" id="inputUser" class="form-control"
                                   placeholder="Username"
                                   required
                                   autofocus>
                        </div>
                    </div>

                    <div class="page-header">
                        <div class="input-group input-group-lg">
                            <label for="inputPassword" class="sr-only">密码</label>
                            <input height="400px" name="password" type="password" id="inputPassword"
                                   class="form-control"
                                   placeholder="Password"
                                   required>
                        </div>
                    </div>
                </div>
                <div class="col-md-16">
                    <button class="btn btn-primary btn-lg btn-block" type="submit">确认</button>
                </div>
                <div class="col-md-10">
                </div>
            </div>
        </div>
    </form>
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <button class="btn btn-default btn-lg btn-block" id="adduser">开户</button>
        </div>
    </div>
</div>
</body>
</html>
