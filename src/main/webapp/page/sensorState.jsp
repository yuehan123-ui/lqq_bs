<%--
  Created by IntelliJ IDEA.
  User: alive
  Date: 2021/11/1
  Time: 19:58
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
<body style="background: #d9edf7">

<h1 align="center">以下是传感器的工作状态</h1>

<table class="table table-striped table table-hover">
    <tr>
        <th >传感器id</th>
        <th >传感器名称</th>
        <th >传感器工作状态</th>
        <th >传感器最近传输数据时间</th>
    </tr>
    <tr>
        <td >1</td>
        <td >光照传感器</td>
        <td >${sensorStatue[0]}</td>
        <td >${sensorStatue[1]}</td>
    </tr>
    <tr>
        <td >2</td>
        <td >水位传感器</td>
        <td >${sensorStatue[2]}</td>
        <td >${sensorStatue[3]}</td>
    </tr>
    <tr>
        <td >3</td>
        <td  >温湿度传感器</td>
        <td  >${sensorStatue[4]}</td>
        <td  >${sensorStatue[5]}</td>
    </tr>

    <tr>
        <td  >4</td>
        <td  >土壤湿度传感器</td>
        <td  >${sensorStatue[6]}</td>
        <td  >${sensorStatue[7]}</td>
    </tr>

</table>
<a class="btn btn-info btn-lg btn-block"href="/page/menu.jsp" role="button">返回菜单栏</a>


</body>
</html>
