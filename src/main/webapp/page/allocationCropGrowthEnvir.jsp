<%--
  Created by IntelliJ IDEA.
  User: alive
  Date: 2022/3/11
  Time: 16:26
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
<body style="background: antiquewhite">
<h1 align="center">请录入农作物生长环境信息</h1><br><br><br><br><br><br>
<div align="center">


    <form class="form-horizontal" action="/test/insertCropGrowthEnvirMessage.do" method="get" >

        <div class="form-group">
            <label for="inputEmail3" class="col-sm-2 control-label">作物名称</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="inputEmail0" placeholder="cropsName" name="cropsName">
            </div>
        </div>

        <div class="form-group">
            <label for="inputEmail3" class="col-sm-2 control-label">农作物所需光照强度</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="inputEmail3" placeholder="lightThresholdValue" name="lightThresholdValue">
            </div>
        </div>

        <div class="form-group">
            <label for="inputPassword3" class="col-sm-2 control-label">农作物所需大气湿度</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="inputPassword3" placeholder="humidityThresholdValue" name="humidityThresholdValue">
            </div>
        </div>

        <div class="form-group">
            <label for="inputEmail3" class="col-sm-2 control-label">农作物所需大气温度</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="inputEmail6" placeholder="temperatureThresholdValue" name="temperatureThresholdValue">
            </div>
        </div>

        <div class="form-group">
            <label for="inputEmail3" class="col-sm-2 control-label">农作物所需土壤湿度</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="inputEmail4" placeholder="soilThresholdValue" name="soilThresholdValue">
            </div>
        </div>

        <div class="form-group">
            <label for="inputEmail3" class="col-sm-2 control-label">水库安全水位</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="inputEmail5" placeholder="highThresholdValue" name="highThresholdValue">
            </div>
        </div>



        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default">确认添加</button>
            </div>

        </div>
    </form>




</div>

</body>
</html>
