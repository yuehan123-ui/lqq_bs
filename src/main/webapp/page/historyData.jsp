<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

<h1 align="center">历史数据解析</h1>

<table class="table table-striped table table-hover">
    <tr>
        <th  >xxx</th>
        <th  >灯工作记录</th>
        <th class=" ">风扇工作记录</th>
        <th class=" ">步进电机工作记录</th>
        <th class=" ">水泵工作记录</th>

    </tr>
    <tr>
        <td class=" ">最近一次开启时间</td>
        <td class=" ">${list[0]}</td>
        <td class=" ">${list[2]}</td>
        <td class=" ">${list[4]}</td>
        <td class=" ">${list[6]}</td>
    </tr>
    <tr>
        <td class=" ">最近一次关闭时间</td>
        <td class=" ">${list[1]}</td>
        <td class=" ">${list[3]}</td>
        <td class=" ">${list[5]}</td>
        <td class=" ">${list[7]}</td>
    </tr>
    <tr>
        <td class=" ">24小时内开启次数</td>
        <td class=" ">${list[8]}</td>
        <td class=" ">${list[9]}</td>
        <td class=" ">${list[10]}</td>
        <td class=" ">${list[11]}</td>
    </tr>



</table>

<h1 align="center">24小时内灌溉记录</h1>

<table class="table table-striped" >

    <tr>
        <td class="text-center">时间记录</td>
    </tr>

    <c:forEach items="${lists}" var="list" varStatus="status">
        <tr>
            <c:forEach items="${list}" var="number" varStatus="status">
                <td align="center">${number}</td>
            </c:forEach>
        </tr>
    </c:forEach>

</table>
<a class="btn btn-info btn-lg btn-block"href="/page/menu.jsp" role="button">返回菜单栏</a>

</body>
</html>
