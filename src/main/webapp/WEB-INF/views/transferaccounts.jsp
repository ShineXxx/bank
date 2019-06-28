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
    <title>转账</title>
    <link href="/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="/dist/jquery-3.3.1.min.js"></script>
    <script src="/dist/js/bootstrap.min.js"></script>
    <script type="text/javascript">

        function formadd() {
            if ($("#kahao").val().toString().length!=16){
                alert("卡号输入有误")
                return false;
            }
            if (confirm("确认转账金额" + $("#jine").val() + "?")) {
                formsubmit()
            }
            return false;
        }

        function formsubmit() {
            var formData = new FormData();
            formData.append('kahao', $("#kahao").val());
            formData.append('jine', $("#jine").val());

            $.ajax({
                type: "POST",//方法类型
                //dataType: "json",//预期服务器返回的数据类型
                url: "/transferaccounts",//url
                data: formData,
                processData: false,
                contentType: false,
                async: false,
                success: function (result) {
                    if (result.state == "success") {
                        var msg = typeof(result.msg) == "undefined" ? "" : result.msg + " "
                        alert(msg)
                        window.location.href = result.address;
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
        <h2 class="alert alert-info">转账:
            <span class="label label-default">操作</span>
        </h2>
    </div>
</div>
<div class="container">
    <div class="row">
        <div class="col-md-8">
            <div class="jumbotron">
                <form onsubmit="return formadd()">
                    <%--<div class="form-group">--%>
                    <%--<label for="exampleInputEmail1">用户名</label>--%>
                    <%--<input class="form-control" id="exampleInputEmail1" disabled value="${username}">--%>
                    <%--</div>--%>
                    <div class="form-group">
                        <label for="kahao">卡号</label>
                        <input class="form-control" id="kahao" required placeholder="输入转账用户">
                    </div>
                    <div class="form-group">
                        <label for="jine">金额</label>
                        <input type="text" class="form-control" required id="jine" placeholder="输入转账金额">
                    </div>
                    <button type="submit" class="btn btn-default">确认</button>
                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>
