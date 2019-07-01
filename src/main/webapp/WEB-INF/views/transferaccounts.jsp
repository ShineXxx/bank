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
                                    modal.find('.ok').click(function () { callback(true); });
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
                                    modal.find('.ok').click(function () { callback(true); });
                                    modal.find('.cancel').click(function () { callback(false); });
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
                            onReady: function () { },
                            onShown: function (e) { }
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

        function formadd() {
            if ($("#kahao").val().toString().length!=16){
                Ewin.alert("卡号输入有误");
                return false;
            }
            Ewin.confirm({ message: "确认转账金额" + $("#jine").val() + "?" }).on(function (e) {
                if (!e) {
                    return;
                }
                formsubmit()
            });
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
                        swal(msg, {
                            icon: "success",
                        });
//                        window.location.href = result.address;
                    } else if (result.state == "failed") {
                        var msg = typeof(result.msg) == "undefined" ? "" : result.msg + " "
                        swal(msg, {
                            icon: "warning",
                        });
                    } else if (result.state == "error") {
                        window.location.href = '/';
                    }
                },
                error: function () {
                    swal("出错了", {
                        icon: "error",
                    });
                    window.location.href = '/';
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
