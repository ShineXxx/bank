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
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <script type="text/javascript">
        (function ($) {

            window.Ewin = function () {
                var html = '<div id="[Id]" class="modal fade" role="dialog" aria-labelledby="modalLabel">' +
                    '<div class="modal-dialog modal-sm">' +
                    '<div class="modal-content">' +
                    '<div class="modal-header">' +
                    '<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>' +
                    '<h4 class="modal-title" id="modalLabel">[Title]</h4>' +
                    '</div>' +
                    '<div class="modal-body">' +
                    '<p>[Message]</p>' +
                    '</div>' +
                    '<div class="modal-footer">' +
                    '<button type="button" class="btn btn-default cancel" data-dismiss="modal">[BtnCancel]</button>' +
                    '<button type="button" class="btn btn-primary ok" data-dismiss="modal">[BtnOk]</button>' +
                    '</div>' +
                    '</div>' +
                    '</div>' +
                    '</div>';


                var dialogdHtml = '<div id="[Id]" class="modal fade" role="dialog" aria-labelledby="modalLabel">' +
                    '<div class="modal-dialog">' +
                    '<div class="modal-content">' +
                    '<div class="modal-header">' +
                    '<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>' +
                    '<h4 class="modal-title" id="modalLabel">[Title]</h4>' +
                    '</div>' +
                    '<div class="modal-body">' +
                    '</div>' +
                    '</div>' +
                    '</div>' +
                    '</div>';
                var reg = new RegExp("\\[([^\\[\\]]*?)\\]", 'igm');
                var generateId = function () {
                    var date = new Date();
                    return 'mdl' + date.valueOf();
                }
                var init = function (options) {
                    options = $.extend({}, {
                        title: "操作提示",
                        message: "提示内容",
                        btnok: "确定",
                        btncl: "取消",
                        width: 200,
                        auto: false
                    }, options || {});
                    var modalId = generateId();
                    var content = html.replace(reg, function (node, key) {
                        return {
                            Id: modalId,
                            Title: options.title,
                            Message: options.message,
                            BtnOk: options.btnok,
                            BtnCancel: options.btncl
                        }[key];
                    });
                    $('body').append(content);
                    $('#' + modalId).modal({
                        width: options.width,
                        backdrop: 'static'
                    });
                    $('#' + modalId).on('hide.bs.modal', function (e) {
                        $('body').find('#' + modalId).remove();
                    });
                    return modalId;
                }

                return {
                    alert: function (options) {
                        if (typeof options == 'string') {
                            options = {
                                message: options
                            };
                        }
                        var id = init(options);
                        var modal = $('#' + id);
                        modal.find('.ok').removeClass('btn-success').addClass('btn-primary');
                        modal.find('.cancel').hide();

                        return {
                            id: id,
                            on: function (callback) {
                                if (callback && callback instanceof Function) {
                                    modal.find('.ok').click(function () {
                                        callback(true);
                                    });
                                }
                            },
                            hide: function (callback) {
                                if (callback && callback instanceof Function) {
                                    modal.on('hide.bs.modal', function (e) {
                                        callback(e);
                                    });
                                }
                            }
                        };
                    },
                    confirm: function (options) {
                        var id = init(options);
                        var modal = $('#' + id);
                        modal.find('.ok').removeClass('btn-primary').addClass('btn-success');
                        modal.find('.cancel').show();
                        return {
                            id: id,
                            on: function (callback) {
                                if (callback && callback instanceof Function) {
                                    modal.find('.ok').click(function () {
                                        callback(true);
                                    });
                                    modal.find('.cancel').click(function () {
                                        callback(false);
                                    });
                                }
                            },
                            hide: function (callback) {
                                if (callback && callback instanceof Function) {
                                    modal.on('hide.bs.modal', function (e) {
                                        callback(e);
                                    });
                                }
                            }
                        };
                    },
                    dialog: function (options) {
                        options = $.extend({}, {
                            title: 'title',
                            url: '',
                            width: 800,
                            height: 550,
                            onReady: function () {
                            },
                            onShown: function (e) {
                            }
                        }, options || {});
                        var modalId = generateId();

                        var content = dialogdHtml.replace(reg, function (node, key) {
                            return {
                                Id: modalId,
                                Title: options.title
                            }[key];
                        });
                        $('body').append(content);
                        var target = $('#' + modalId);
                        target.find('.modal-body').load(options.url);
                        if (options.onReady())
                            options.onReady.call(target);
                        target.modal();
                        target.on('shown.bs.modal', function (e) {
                            if (options.onReady(e))
                                options.onReady.call(target, e);
                        });
                        target.on('hide.bs.modal', function (e) {
                            $('body').find(target).remove();
                        });
                    }
                }
            }();
        })(jQuery);
    </script>
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
            if ($("#InputAmount").val() <= 0 || $("#InputAmount").val() > 10000) {
                Ewin.alert("取款金额区间：0~10000");
                $("#InputAmount").val('')
                return false;
            }
            var v = parseFloat($("#InputAmount").val()) % 50;
            if (v != 0) {
                Ewin.alert("输入有误,必须为50的整数倍");
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
                        // window.location.href = result.address;
                        // Ewin.alert("取款成功 账户"+msg);
                        swal("取款成功 ", "账户" + msg, "success");
                    } else if (result.state == "failed") {
                        var msg = typeof(result.msg) == "undefined" ? "" : result.msg + " "
                        // Ewin.alert(msg);
                        swal("提示", msg, "error");
//                       window.location.href = '/index';
                    }
                },
                error: function () {
                    $("#myModal").modal("hide")
                    // Ewin.alert("出错了 ");
                    swal("出错了");
                }
            });
            return false;
        }
    </script>
</head>
<body>
<jsp:include page="nav.jsp"></jsp:include>
<div class="row">
    <div class="page-header col-md-6 col-md-offset-3">
        <h2 class="alert alert-info">取款: (50的整数倍)
            <%--<span class="label label-default"></span>--%>
        </h2>
    </div>
</div>
<div class="container">
    <div class="row">
        <div class="col-md-2 col-md-offset-2">
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
                        <div class="col-md-8 col-md-offset-2">
                            <div class="input-group input-group-lg">
                                <div class="input-group-addon">￥</div>
                                <input type="text" class="form-control" id="InputAmount" placeholder="Amount" required>
                                <div class="input-group-addon">.00</div>
                            </div>
                        </div>
                    </div>
                    <br>
                    <br>
                    <br>
                    <button type="submit" class="btn btn-default btn-lg btn-block">取款</button>
                </form>
            </div>
        </div>
        <div class="col-md-2 ">
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
