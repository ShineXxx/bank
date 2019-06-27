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
                        window.location.href = result.address;
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
    <h2 class="alert alert-info">存款：
        <span class="label label-default">100的整数倍</span>
    </h2>
</div>
<div class="container">
    <div class="row">
        <div class="col-md-8">
            <div class="jumbotron">
                <form class="form-inline" onsubmit="return formadd()">
                    <div class="form-group">
                        <label class="sr-only" for="InputAmount">Amount (in dollars)</label>
                        <div class="input-group input-group-lg">
                            <div class="input-group-addon">￥</div>
                            <input type="text" class="form-control" id="InputAmount" placeholder="Amount" required>
                            <div class="input-group-addon">.00</div>
                        </div>
                    </div>
                    <br>
                    <br>
                    <br>
                    <br>
                    <div>
                        <button class="btn btn-primary btn-lg btn-block">存款</button>
                    </div>

                </form>
            </div>
        </div>
    </div>

</div>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">存款金额</h4>
            </div>
            <div class="modal-body">
                <h3 id="h3"></h3>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" onclick="formsubmit()">确认</button>
            </div>
        </div>
    </div>
</div>

</body>
</html>
