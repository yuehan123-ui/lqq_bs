<%--
  Created by IntelliJ IDEA.
  User: alive
  Date: 2022/3/11
  Time: 16:10
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
</head>
<body style="background: #c4e3f3">

<h1 align="center">需要重新配置农作物生长环境吗</h1>
<h2 align="center">请选择相应的操作</h2>
<a class="btn btn-info btn-lg btn-block" href="/test/getAllCropGrowthEnvirMessage.do" role="button">从数据库数据库选定已经配置好的农作物生长环境</a>
<a class="btn btn-info btn-lg btn-block" href="/page/allocationCropGrowthEnvir.jsp" role="button">手动配置农作物生长环境</a>
<a class="btn btn-info btn-lg btn-block" href="/page/menu.jsp" role="button">不需要重新配置跳转菜单</a>


<h2 align="center">当前已选定作物信息生长环境</h2>
<table class="table table-striped">
    <tr>
        <th >作物名称</th>
        <th >光照阈值</th>
        <th >大气湿度阈值</th>
        <th >大气温度阈值</th>
        <th >土壤湿度阈值</th>
        <th >水位阈值</th>
        <th >选定时间</th>

    </tr>
    <tr>
        <td >${list[0]}</td>
        <td >${list[1]}</td>
        <td >${list[2]}</td>
        <td >${list[3]}</td>
        <td >${list[4]}</td>
        <td >${list[5]}</td>
        <td >${list[6]}</td>
    </tr>

</table>

</body>
</html>
