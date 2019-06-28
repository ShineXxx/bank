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
    <title>取款</title>
    <link href="/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="/dist/jquery-3.3.1.min.js"></script>
    <script src="/dist/js/bootstrap.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#InputAmount").change(function () {
                $("#h3").html($("#InputAmount").val())
            });
            $("#btn1").click(function () {
                $("#InputAmount").val(100)
                $("#h3").html($("#InputAmount").val())
            });
            $("#btn2").click(function () {
                $("#InputAmount").val(500)
                $("#h3").html($("#InputAmount").val())
            });
            $("#btn3").click(function () {
                $("#InputAmount").val(1000)
                $("#h3").html($("#InputAmount").val())
            });
            $("#btn4").click(function () {
                $("#InputAmount").val(2000)
                $("#h3").html($("#InputAmount").val())
            });
            $("#btn5").click(function () {
                $("#InputAmount").val(5000)
                $("#h3").html($("#InputAmount").val())
            });
            $("#btn6").click(function () {
                $("#InputAmount").val(10000)
                $("#h3").html($("#InputAmount").val())
            });
        });

        function formadd() {
            if ($("#InputAmount").val()<=0){
                alert("输入有误,输入金额不能为负")
                $("#InputAmount").val('')
                return false;
            }
            var v=parseFloat($("#InputAmount").val())%50;
            if (v!=0){
                alert("输入有误,必须为50的整数倍")
                $("#InputAmount").val('')
                return false;
            }
            $("#myModal").modal({backdrop: "static"});
            return false;
        }

        function formsubmit() {
            var formData = new FormData();
            formData.append('money', $("#InputAmount").val());
            $.ajax({
                type: "POST",//方法类型
                //dataType: "json",//预期服务器返回的数据类型
                url: "/withdrawmoney",//url
                data: formData,
                processData: false,
                contentType: false,
                async: false,
                success: function (result) {
                    $("#myModal").modal("hide")
                    if (result.state == "success") {
                        var msg = typeof(result.msg) == "undefined" ? "" : result.msg + " "
                        window.location.href = result.address;
                        alert("取款成功 账户"+msg)
                    } else if (result.state == "failed") {
                        var msg = typeof(result.msg) == "undefined" ? "" : result.msg + " "
                        alert(msg)
//                        window.location.href = result.address;
                    }
                },
                error: function () {
                    $("#myModal").modal("hide")
                    alert("出错了 ");
                }
            });
            return false;
        }
    </script>
</head>
<body>
<jsp:include page="nav.jsp"></jsp:include>
<div class="page-header col-md-6 col-md-offset-3">
    <h2 class="alert alert-info">取款:   (50的整数倍)
        <%--<span class="label label-default"></span>--%>
    </h2>
</div>
<div class="container">
    <div class="row">
        <div class="col-md-4">
            <div>
                <h1>
                    <a href="javascript:void(0);" id="btn1">
                        <div class="thumbnail">
                            <div class="caption">
                                100
                            </div>
                        </div>
                    </a>
                </h1>
            </div>
            <div>
                <h1>
                    <a href="javascript:void(0);" id="btn2">
                        <div class="thumbnail">
                            <div class="caption">
                                500
                            </div>
                        </div>
                    </a>
                </h1>
            </div>
            <div>
                <h1>
                    <a href="javascript:void(0);" id="btn3">
                        <div class="thumbnail">
                            <div class="caption">
                                1000
                            </div>
                        </div>
                    </a>
                </h1>
            </div>
        </div>
        <div class="col-md-4">
            <div class="page-header">
                <form class="form-inline" onsubmit="return formadd()">
                    <div class="form-group">
                        <label class="sr-only" for="InputAmount">Money (in dollars)</label>
                        <div class="input-group input-group-lg">
                            <div class="input-group-addon">￥</div>
                            <input type="text" class="form-control" id="InputAmount" placeholder="Amount" required>
                            <div class="input-group-addon">.00</div>
                        </div>
                    </div>
                    <br>
                    <br>
                    <br>
                    <button type="submit" class="btn btn-default btn-lg btn-block">取款</button>
                </form>
            </div>
        </div>
        <div class="col-md-4 ">
            <div>
                <h1>
                    <a href="javascript:void(0);" id="btn4">
                        <div class="thumbnail">
                            <div class="caption">
                                2000
                            </div>
                        </div>
                    </a>
                </h1>
            </div>
            <div>
                <h1>
                    <a href="javascript:void(0);" id="btn5">
                        <div class="thumbnail">
                            <div class="caption">
                                5000
                            </div>
                        </div>
                    </a>
                </h1>
            </div>
            <div>
                <h1>
                    <a href="javascript:void(0);" id="btn6">
                        <div class="thumbnail">
                            <div class="caption">
                                10000
                            </div>
                        </div>
                    </a>
                </h1>
            </div>
        </div>
    </div>
    <!-- Modal -->
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel">确认取款金额</h4>
                </div>
                <div class="modal-body">
                    <h3 id="h3"></h3>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" onclick="formsubmit()">确认</button>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
