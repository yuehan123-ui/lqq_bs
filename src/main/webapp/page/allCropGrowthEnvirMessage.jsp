<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: alive
  Date: 2022/3/11
  Time: 16:37
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
<body>


<h1 align="center">数据库中所有农作物及其已配置好的生长环境</h1>

<table class="table table-striped" >

    <tr>
        <td class="text-center">作物名称</td>
        <td class="text-center">光照阈值</td>
        <td class="text-center">大气湿度阈值</td>
        <td class="text-center">大气温度阈值</td>
        <td class="text-center">土壤湿度阈值</td>
        <td class="text-center">水库安全水位</td>
        <td class="text-center">时间</td>
    </tr>
    <%--<tr>
        <td class="text-center">光照阈值</td>
    </tr>
    <tr>
        <td class="text-center">大气湿度阈值</td>
    </tr>
    <tr>
        <td class="text-center">大气温度阈值</td>
    </tr>
    <tr>
        <td class="text-center">土壤湿度阈值</td>
    </tr>
    <tr>
        <td class="text-center">水库安全水位阈值</td>
    </tr>
    <tr>
        <td class="text-center">建立时间</td>
    </tr>--%>

    <c:forEach items="${lists}" var="list" varStatus="status">
        <tr>
            <c:forEach items="${list}" var="number" varStatus="status">
                <td align="center"><a href="/test/insertSelectedCropGrowthEnvirMessage.do?name=${number}">${number}</a></td>
            </c:forEach>
        </tr>
    </c:forEach>

</table>

</body>
</html>
