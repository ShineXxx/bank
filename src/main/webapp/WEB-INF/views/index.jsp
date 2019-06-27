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
    <title>主页</title>
    <link href="/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="/dist/jquery-3.3.1.min.js"></script>
    <script src="/dist/js/bootstrap.min.js"></script>
</head>

<body>
<jsp:include page="nav.jsp"></jsp:include>
<div class="page-header col-md-6 col-md-offset-3">
    <h2 class="alert alert-success">主页 </h2><span class="label label-default"></span>
</div>
<div class="container">
    <div class="row">
        <div class="col-sm-6 col-md-4">
            <div class="page-header">
                <h1>
                    <a href="/depositmoney.html">
                        <div class="thumbnail">
                            <div class="caption">
                                存款
                            </div>
                        </div>
                    </a>
                </h1>
            </div>
        </div>
        <div class="col-sm-6 col-md-4">
            <div class="page-header">
                <h1><a href="/withdrawmoney.html">
                    <div class="thumbnail">
                        <div class="caption">取款
                        </div>
                    </div>
                </a></h1>
            </div>
        </div>
        <div class="col-sm-6 col-md-4">
            <div class="page-header">
                <h1>
                    <a href="/transferaccounts.html">
                        <div class="thumbnail">
                            <div class="caption">转账
                            </div>
                        </div>
                    </a>
                </h1>
            </div>
        </div>
        <div class="col-sm-6 col-md-4">
            <div class="page-header">
                <h1>
                    <a href="/currencyexchange.html">
                        <div class="thumbnail">
                            <div class="caption">货币兑换
                            </div>
                        </div>
                    </a>
                </h1>
            </div>
        </div>
        <div class="col-sm-6 col-md-4">
            <div class="page-header">
            <h1><a href="/balance.html">
                <div class="thumbnail">
                    <div class="caption">余额查询
                    </div>
                </div>
            </a></h1>
            </div>
        </div>
        <div class="col-sm-6 col-md-4">
            <div class="page-header">
            <h1><a href="/changepassword.html">
                <div class="thumbnail">
                    <div class="caption">修改密码
                    </div>
                </div>
            </a></h1>
            </div>
        </div>
    </div>
</div>
</body>
</html>
