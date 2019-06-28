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
                    Ewin.alert("出错了");
                }
            });
            return false;
        }

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
                Ewin.alert("两次密码不一致");
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
                        Ewin.alert(cont);
                    } else if (result.state == "failed") {
                        var user = typeof(result.usercontent) == "undefined" ? "" : result.usercontent + " "
                        var pass = typeof(result.passcontent) == "undefined" ? "" : result.passcontent + " "
                        var cont = typeof(result.msg) == "undefined" ? "" : result.msg + " "
                        $("#content1").html(user + pass + cont)
                    }
                },
                error: function () {
                    $("#myModal").modal("hide")
                    Ewin.alert("出错了");
                }
            });
        }
    </script>
</head>
<body class="text-center">
<div class="container">
    <form class="form-signin" onsubmit="return formadd()">
        <div class="row">
            <div class="col-md-16">
                <div class="page-header">
                    <div class="input-group input-group-lg">
                        <h4 style="color: #c9302c" id="content"></h4>
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
                        <input height="400px" name="password" type="password" id="inputPassword" class="form-control"
                               placeholder="Password"
                               required>
                    </div>
                </div>
            </div>
            <div class="col-md-16">
                <button class="btn btn-primary btn-lg btn-block" type="submit">确认</button>
            </div>
            <div class="col-md-10">
                <h4></h4>
            </div>
        </div>
    </form>
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <button class="btn btn-default btn-block" id="adduser">开户</button>
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
                <h4 class="modal-title" id="myModalLabel">开户</h4>
            </div>
            <h4 style="color: #c9302c" id="content1"></h4>
            <div class="modal-body">
                <form class="form-horizontal" id="form1">
                    <div class="form-group">
                        <label for="kahao" class="col-sm-2 control-label">卡号</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="kahao" placeholder="cardid">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="pass" class="col-sm-2 control-label">密码</label>
                        <div class="col-sm-10">
                            <input type="password" class="form-control" id="pass" placeholder="Password">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="pass1" class="col-sm-2 control-label">重复密码</label>
                        <div class="col-sm-10">
                            <input type="password" class="form-control" id="pass1" placeholder="Password">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="name" class="col-sm-2 control-label">姓名</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="name" placeholder="Name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="identify" class="col-sm-2 control-label">身份证</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="identify" placeholder="Name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="phone" class="col-sm-2 control-label">电话</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="phone" placeholder="Name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="address" class="col-sm-2 control-label">地址</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="address" placeholder="Name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="identify" class="col-sm-2 control-label">卡类型</label>
                        <div class="col-sm-10">
                            <select class="form-control" id="type">
                                <option value="0">借记卡</option>
                                <option value="1">信用卡</option>
                            </select>
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" onclick="signup()">确认</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>
