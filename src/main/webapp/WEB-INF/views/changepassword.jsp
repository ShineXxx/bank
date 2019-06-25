<%--
  Created by IntelliJ IDEA.
  User: VULCAN
  Date: 2018/12/15
  Time: 19:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>修改密码</title>
    <link href="/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="/dist/jquery-3.3.1.min.js"></script>
    <script src="/dist/js/bootstrap.min.js"></script>
    <script type="text/javascript">

        function formadd() {
            formsubmit()
            return false;
        }

        function formsubmit() {
            var formData = new FormData();
            formData.append('money', $("#InputAmount").val());
            $.ajax({
                type: "POST",//方法类型
                //dataType: "json",//预期服务器返回的数据类型
                url: "/changepassword",//url
                data: formData,
                processData: false,
                contentType: false,
                async: false,
                success: function (result) {
                    if (result.state == "success") {
                        var msg = typeof(result.msg) == "undefined" ? "" : result.msg + " "
                        alert(msg)
                    } else if (result.state == "failed") {
                        var msg = typeof(result.msg) == "undefined" ? "" : result.msg + " "
                        alert(msg)
                    } else if (result.state == "error") {
                        window.location.href = result.address;
                    }
                },
                error: function () {
                    alert("出错了");
                }
            });
        }
    </script>
</head>
<body>
<jsp:include page="nav.jsp"></jsp:include>
<div class="container">
    <div class="page-header col-md-6 col-md-offset-3">
        <h4>修改密码:
            <small>:</small>
        </h4>
    </div>
</div>
<div class="container">
    <form>
        <div class="form-group">
            <label for="exampleInputEmail1">用户名</label>
            <input class="form-control" id="exampleInputEmail1" disabled value="${username}">
        </div>
        <div class="form-group">
            <label for="kahao">卡号</label>
            <input class="form-control" id="kahao" disabled value="${cardid}">
        </div>
        <div class="form-group">
            <label for="password1">密码</label>
            <input type="password" class="form-control" id="password1" placeholder="输入密码">
        </div>
        <div class="form-group">
            <label for="password2">确认密码</label>
            <input type="password" class="form-control" id="password2" placeholder="再次确认">
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
    </form>
</div>

</body>
</html>
