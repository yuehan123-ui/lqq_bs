<%--
  Created by IntelliJ IDEA.
  User: alive
  Date: 2021/11/1
  Time: 19:32
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
<body style="background: #c4e3f3">




<h1 align="center">欢迎进入远程控制栏</h1>
<h2 align="center">请选择你要进行的操作</h2>


<table class="table table-striped table table-hover">

    <tr>
        <td class=" ">灯光操作</td>
        <td class=" "><a  href="/test/light.do?light=1" role="button">开灯</a></td>
        <td class=" "><a  href="/test/light.do?light=0" role="button">关灯</a></td>
    </tr>
    <tr>
        <td class=" ">步进电机操作</td>
        <td class=" "><a  href="/test/steppingMotor.do?steppingMotor=1" role="button">开步进电机</a></td>
        <td class=" "><a  href="/test/steppingMotor.do?steppingMotor=0" role="button">关步进电机</a></td>
    </tr>
    <tr>
        <td class=" ">直流电机操作</td>
        <td class=" "><a  href="/test/dcMotor.do?dcMotor=1" role="button">开直流电机</a></td>
        <td class=" "><a  href="/test/dcMotor.do?dcMotor=0" role="button">关直流电机</a></td>
    </tr>

    <tr>
        <td class=" ">水泵操作</td>
        <td class=" "><a href="/test/waterPump.do?waterPump=1" role="button">开水泵</a></td>
        <td class=" "><a href="/test/waterPump.do?waterPump=0" role="button">关水泵</a></td>
    </tr>

    <tr>
        <td class=" ">手动控制</td>
        <td class=" "><a  href="/test/manual.do?manual=1" role="button">开启手动控制</a></td>
        <td class=" "><a  href="/test/manual.do?manual=0" role="button">关闭手动控制</a></td>
    </tr>

    <tr>
        <td class=" ">当前是手动控制状态吗</td>
        <td class=" "><a href="/test/reflush.do">刷新手动控制状态${ShowManualStatue}</a></td>
        <td class=" "><a href="/page/menu.jsp" role="button">返回菜单栏</a></td>
    </tr>



</table>




</body>
</html>
