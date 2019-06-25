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
    <h4>主页 :
        <small>  操作 </small>
    </h4>
</div>
<div class="container">
    <div class="row">
        <div class="col-sm-6 col-md-4">
            <div class="thumbnail">
                <img src="/imges/1.png" width="160px" height="160px" alt="...">
                <div class="caption">
                    <h3><a href="/depositmoney.html">存款</a></h3>
                    <p>...</p>
                </div>
            </div>
        </div>
        <div class="col-sm-6 col-md-4">
            <div class="thumbnail">
                <img src="/imges/2.png" alt="..." >
                <div class="caption">
                    <h3><a href="/withdrawmoney.html">取款</a></h3>
                    <p>...</p>
                </div>
            </div>
        </div>
        <div class="col-sm-6 col-md-4">
            <div class="thumbnail">
                <img src="/imges/3.png" alt="..." >
                <div class="caption">
                    <h3><a href="#">转账</a></h3>
                    <p>...</p>
                </div>
            </div>
        </div>

        <div class="col-sm-6 col-md-4">
            <div class="thumbnail">
                <img src="/imges/4.png" alt="..." >
                <div class="caption">
                    <h3><a href="#">兑换</a></h3>
                    <p>...</p>
                </div>
            </div>
        </div>
        <div class="col-sm-6 col-md-4">
            <div class="thumbnail">
                <img src="/imges/4.png" alt="..." >
                <div class="caption">
                    <h3><a href="#">余额查询</a></h3>
                    <p>...</p>
                </div>
            </div>
        </div>
        <div class="col-sm-6 col-md-4">
            <div class="thumbnail">
                <img src="/imges/4.png" alt="..." >
                <div class="caption">
                    <h3><a href="/changepassword.html">修改密码</a></h3>
                    <p>...</p>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
