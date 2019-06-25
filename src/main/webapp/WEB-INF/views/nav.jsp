<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
    $(document).ready(function () {
        $('input[type=radio][name=inlineRadioOptions]').change(function () {
            $("#input_type").val(this.value);
        });
    });
</script>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/index">MainPage</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">Link <span class="sr-only">(current)</span></a></li>
                <li><a href="#">Link</a></li>
            </ul>
            <%--<form class="navbar-form navbar-left" action="/searchresults" method="post">--%>
                <%--<div class="form-group">--%>
                    <%--<input type="text" class="form-control" placeholder="Search" name="condit">--%>
                <%--</div>--%>
                <%--<input type="hidden" name="type" value="0" id="input_type">--%>
                <%--<button type="submit" class="btn btn-default">查找</button>--%>
            <%--</form>--%>
            <%--<label class="radio-inline">--%>
                <%--<input type="radio" name="inlineRadioOptions" id="inlineRadio2" value="0" checked="checked"> 作者--%>
            <%--</label>--%>
            <%--<label class="radio-inline">--%>
                <%--<input type="radio" name="inlineRadioOptions" id="inlineRadio1" value="1"> 标题--%>
            <%--</label>--%>
            <%--<label class="radio-inline">--%>
                <%--<input type="radio" name="inlineRadioOptions" id="inlineRadio3" value="2"> 内容--%>
            <%--</label>--%>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/userinfo?userid=${userinfo.userid}"
                       class="btn btn-primary btn-lg disabled" role="button">
                    <img src="/imges/android-chrome-192x192.png" width="20px" height="20px" class="img-circle"></a>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false">
                        <span class="glyphicon glyphicon-user" aria-hidden="true"></span> ${userinfo.name} <span
                            class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">Action</a></li>
                        <li><a href="#">Another action</a></li>
                        <li><a href="#">Something else here</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="#">Separated link</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="/logout">退出</a></li>
                    </ul>
                </li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>