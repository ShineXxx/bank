<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
    $(document).ready(function () {
        $("#btn_exit").click(function () {
            if (confirm("确认退出？")){
                window.location.href="/logout";
            }
        })
    });

</script>
<style type="text/css">
    body {
        background: url('imges/background.png') no-repeat center center;
        background-size: 100% 100%;
        background-attachment: fixed;
        background-color: #CCCCCC;
    }
</style>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <a class="navbar-brand" href="/index">主页</a>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <li><button type="button" id="btn_exit" class="btn btn-default navbar-btn">退出</button></li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>