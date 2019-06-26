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
    <title>Login</title>
    <link href="/dist/css/signin.css" rel="stylesheet">
    <link href="/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="/dist/jquery-3.3.1.min.js"></script>
    <script src="/dist/js/bootstrap.min.js"></script>
    <style type="text/css">
        body {
            background: url('/imges/bg.jpg') no-repeat center center;
            background-size: 100% 100%;
            background-attachment: fixed;
            background-color: #CCCCCC;
        }
    </style>
    <script type="text/javascript">

        $(document).ready(function () {
            $("#canclebtn").click(function () {
                window.location.href = "/";
            });
        });
        function signup() {
            var formData = new FormData();
            formData.append('id', $("#kahao").val());
            formData.append('pass', $("#pass").val());
            formData.append('pass1', $("#pass1").val());
            formData.append('name', $("#name").val());
            formData.append('identify', $("#identify").val());
            formData.append('phone', $("#phone").val());
            formData.append('address', $("#address").val());
            formData.append('type', $("#type option:selected").val());
            if (!($("#pass").val() == $("#pass1").val())) {
                alert("两次密码不一致")
                return false;
            }
            $.ajax({
                type: "POST",//方法类型
                //dataType: "json",//预期服务器返回的数据类型
                url: "/signup",//url
                data: formData,
                processData: false,
                contentType: false,
                async: false,
                success: function (result) {
                    if (result.state == "success") {
                        $("#myModal").modal("hide")
                        var cont = typeof(result.msg) == "undefined" ? "" : result.msg + " "
                        alert(cont)
                        window.location.href =result.address
                    } else if (result.state == "failed") {
                        var user = typeof(result.usercontent) == "undefined" ? "" : result.usercontent + " "
                        var pass = typeof(result.passcontent) == "undefined" ? "" : result.passcontent + " "
                        var cont = typeof(result.msg) == "undefined" ? "" : result.msg + " "
                        $("#content1").html(user + pass + cont)
                    }
                },
                error: function () {
                    $("#myModal").modal("hide")
                    alert("出错了");
                }
            });
            return false;
        }
    </script>
</head>
<body class="text-center">
<div class="container">
    <form class="form-horizontal" onsubmit="return signup()" id="form1">
        <div class="form-group">
            <label for="kahao" class="col-sm-2 control-label">卡号</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="kahao" disabled value="${cardid}">
            </div>
        </div>
        <div class="form-group">
            <label for="pass" class="col-sm-2 control-label">密码</label>
            <div class="col-sm-10">
                <input type="password" class="form-control" id="pass" required placeholder="Password">
            </div>
        </div>
        <div class="form-group">
            <label for="pass1" class="col-sm-2 control-label">重复密码</label>
            <div class="col-sm-10">
                <input type="password" class="form-control" id="pass1" required  placeholder="Password">
            </div>
        </div>
        <div class="form-group">
            <label for="name" class="col-sm-2 control-label">姓名</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="name" required  placeholder="Name">
            </div>
        </div>
        <div class="form-group">
            <label for="identify" class="col-sm-2 control-label">身份证</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="identify" required  placeholder="Identify">
            </div>
        </div>
        <div class="form-group">
            <label for="phone" class="col-sm-2 control-label">电话</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="phone" required  placeholder="phone">
            </div>
        </div>
        <div class="form-group">
            <label for="address" class="col-sm-2 control-label">地址</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="address" required  placeholder="address">
            </div>
        </div>
        <div class="form-group">
            <label for="identify" class="col-sm-2 control-label">卡类型</label>
            <div class="col-sm-10">
                <select class="form-control" id="type">
                    <option value="借记卡">借记卡</option>
                    <option value="信用卡">信用卡</option>
                </select>
            </div>
        </div>
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <button type="button" class="btn btn-default" id="canclebtn">取消</button>
                <button type="submit" class="btn btn-primary">开户</button>
            </div>
        </div>
    </form>
</div>
</div>
</body>
</html>
