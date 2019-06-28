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
    <title>货币兑换</title>
    <link href="/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="/dist/jquery-3.3.1.min.js"></script>
    <script src="/dist/js/bootstrap.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            //输入框改变事件
            $("#InputAmount").change(function () {
                $("#h3").html($("#InputAmount").val())
            });
            //货币类型改变事件
            $("#currencytype").change(function () {
                $("#biaoji").html("要兑换的货币类型" + $("#currencytype option:selected").val())
            });
            //更新输入框值
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


        //表单提交
        function formadd() {
            if ($("#InputAmount").val() <= 0) {
                alert('金额不能为负数或0')
                $("#InputAmount").val('')
                return false;
            }
            if ($("#InputAmount").val() < 100) {
                alert('兑换金额不得低于100')
                $("#InputAmount").val('')
                return false;
            }
            if ($("#InputAmount").val() > 10000) {
                alert('兑换金额不得高于10000')
                $("#InputAmount").val('')
                return false;
            }
            if (!confirm('确认兑换'+$("#currencytype option:selected").html()+$("#InputAmount").val())){
                return false
            }
            $("#h3").html('兑换'+$("#InputAmount").val()+$("#currencytype option:selected").html())
            formsubmit()
            return false;
        }

        function sleep(numberMillis) {
            var now = new Date();
            var exitTime = now.getTime() + numberMillis;
            while (true) {
                now = new Date();
                if (now.getTime() > exitTime)
                    return;
            }
        }

        function formsubmit() {
            $(".progress-bar").css("width","0%").async
            $("#myModal").modal("toggle");
            var formData = new FormData();
            formData.append('money', $("#InputAmount").val());
            formData.append('currencytype', $("#currencytype option:selected").val());
            $.ajax({
                type: "POST",//方法类型
                //dataType: "json",//预期服务器返回的数据类型
                url: "/currencyexchange",//url
                data: formData,
                processData: false,
                contentType: false,
                async: true,
                success: function (result) {
                    // $("#myModal").modal("hide");
                    if (result.state == "success") {
                        var msg = typeof(result.msg) == "undefined" ? "" : result.msg + " "
                        $(".progress-bar").css("width","10%").async
                        $(".progress-bar").css("width","20%").async
                        $(".progress-bar").css("width","50%").async
                        $(".progress-bar").css("width","80%").async
                        $(".progress-bar").css("width","100%").async
                        $("#h3").html("成功：账户支出"+msg+"人民币")

                    } else if (result.state == "failed") {
                        $(".progress-bar").css("width","0%").async
                        var msg = typeof(result.msg) == "undefined" ? "" : result.msg + " "
                        $("#h3").html(msg)
//                        window.location.href = result.address;
                    }
                },
                error: function () {
                    // $("#myModal").modal("hide").async
                    $(".progress-bar").css("width","0%").async
                    $("#h3").html("联网失败 ")
                }
            });
        }
    </script>
</head>
<body>
<jsp:include page="nav.jsp"></jsp:include>
<div class="page-header col-md-6 col-md-offset-3">
    <h2 class="alert alert-info">兑换页面
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
                            <div id="fuhao" class="input-group-addon">￥</div>
                            <input type="text" class="form-control" id="InputAmount" placeholder="Amount" required>
                            <div class="input-group-addon">.00</div>
                        </div>
                    </div>
                    <br>
                    <br>
                    <div class="form-group">
                        <div class="input-group input-group-lg alert alert-success">
                            <label for="InputAmount"><h4 id="biaoji">要兑换的货币类型</h4></label>
                            <select id="currencytype" class="form-control">
                                <option value="USD">美元</option>
                                <option value="GBP">英镑</option>
                                <option value="EUR">欧元</option>
                                <option value="JPY">日元</option>
                                <option value="RUB">俄罗斯卢布</option>
                            </select>
                        </div>
                    </div>
                    <br>
                    <br>
                    <button type="submit" class="btn btn-default btn-lg btn-block">确认兑换</button>
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
                    <h4 class="modal-title" id="myModalLabel">进行中</h4>
                </div>
                <div class="modal-body">
                    <h3 id="h3"></h3>
                    <div class="showback">
                        <h4><i class="fa fa-angle-right"></i> 进度 </h4>
                        <div class="progress progress-striped active">
                            <div class="progress-bar" role="progressbar" aria-valuenow="" aria-valuemin="0" aria-valuemax="100" style="width: 0%">
                                <span class="sr-only">0% Complete</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
