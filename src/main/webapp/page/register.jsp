<%--
  Created by IntelliJ IDEA.
  User: alive
  Date: 2022/3/10
  Time: 16:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>menu</title>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/jquery.min.js"></script>
    <link href="">



</head>
<body style="background: #ec971f">

<h1 align="center">欢迎来到注册界面</h1><br><br><br><br><br><br>
<div align="center">
    <%--用户名：<input type="text" name="username">
    &nbsp&nbsp&nbsp&nbsp密码：<input type="text" name="password"><br><br><br><br>
    <input type="submit" value="确定登录">
    <br><br><br><br><br><br><br><br>--%>

    <form class="form-horizontal" action="/test/register.do" method="get" >
        <div class="form-group">
            <label for="inputEmail3" class="col-sm-2 control-label">Username</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="inputEmail3" placeholder="Email" name="username">
            </div>
        </div>
        <div class="form-group">
            <label for="inputPassword3" class="col-sm-2 control-label">Password</label>
            <div class="col-sm-10">
                <input type="password" class="form-control" id="inputPassword3" placeholder="Password" name="password">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <div class="checkbox">
                    <label>
                        <input type="checkbox"> Remember me
                    </label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default">submit</button>
            </div>

        </div>
    </form>
        <div class="col-sm-offset-2 col-sm-10">
            <a href="/index.jsp" class="btn btn-default">returnLogin</a>
        </div>












</div>

</body>
</html>
