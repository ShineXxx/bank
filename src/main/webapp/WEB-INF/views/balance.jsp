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
    <title>存款</title>
    <link href="/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="/dist/jquery-3.3.1.min.js"></script>

    <script src="/dist/js/bootstrap.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#InputAmount").change(function () {
                $("#h3").html($("#InputAmount").val())
            });
            $("#btn").click(function () {
                window.location.href = "/getbills.html";
            })
        });

        function formadd() {
            $("#myModal").modal({backdrop: "static"});
            return false;
        }

        function formsubmit() {
            var formData = new FormData();
            formData.append('money', $("#InputAmount").val());
            $.ajax({
                type: "POST",//方法类型
                //dataType: "json",//预期服务器返回的数据类型
                url: "/depositmoney",//url
                data: formData,
                processData: false,
                contentType: false,
                async: false,
                success: function (result) {
                    $("#myModal").modal("hide")
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
                    $("#myModal").modal("hide")
                    alert("出错了");
                }
            });
        }
    </script>
</head>
<body>
<jsp:include page="nav.jsp"></jsp:include>
<div class="page-header col-md-6 col-md-offset-3">
    <h2 class="alert alert-info">余额查询：
        <span class="label label-default">操作</span>
    </h2>
</div>
<div class="container">

    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <div class="jumbotron">
                <label for="yue">余额</label>
                <input class="form-control" id="yue" disabled value="${yue}">
                <label for="type">卡类型</label>
                <input class="form-control" id="type" disabled value="${type}">
                <br>
                <br>
                <br>
                <div>
                    <button class="btn btn-primary btn-large" id="btn">查看账单</button>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
