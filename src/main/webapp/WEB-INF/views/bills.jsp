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
    <title>账单</title>
    <link href="/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="/dist/jquery-3.3.1.min.js"></script>
    <script src="/dist/js/bootstrap.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.15/css/jquery.dataTables.min.css"></script>
    <script src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript">

        $(function () {
        })

    </script>
</head>
<body>
<jsp:include page="nav.jsp"></jsp:include>
<div class="page-header col-md-6 col-md-offset-3">
    <h2>账单列表：
        <span class="label label-default">操作</span>
    </h2>
</div>
<div class="container">
    <div class="row">
        <div class="col-md-8">
            <div class="jumbotron">
                <table class="table table-hover">
                    <thead>
                    <th>卡号</th>
                    <th>类型</th>
                    <th>时间</th>
                    <th>金额</th>
                    </thead>
                    <tbody>
                    <c:forEach items="${bills}" var="bill">
                        <tr>
                            <td>${bill.cardID}</td>
                            <td>${bill.affairType}</td>
                            <td>${bill.tradeTime}</td>
                            <td>${bill.tradeBalance}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <table class="table table-bordered table-striped" id="dailyTable"></table>
            </div>
        </div>
    </div>
</div>
<div class="container">
    <nav aria-label="Page navigation">
        <ul class="pagination">
            <li><a href="/pagenum?pn=${pageInfo.navigateFirstPage}">FirstPage</a></li>
            <li>
                <a href="/pagenum?pn=${pageInfo.prePage}" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
            <c:forEach items="${pageInfo.navigatepageNums}" var="num">
                <li <c:if test="${pageInfo.pageNum==num}"> class="active"</c:if>>
                    <a href="/pagenum?pn=${num}">${num}</a>
                </li>
            </c:forEach>
            <li>
                <a href="/pagenum?pn=${pageInfo.nextPage}" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
            <li><a href="/pagenum?pn=${pageInfo.navigateLastPage}">LastPage</a></li>
        </ul>
    </nav>
</div>
</body>
</html>
