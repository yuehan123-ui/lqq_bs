package com.liqiqi.controller;


import com.alibaba.fastjson.JSON;
import com.liqiqi.pojo.*;
import com.liqiqi.utils.Utils;
import com.sun.tools.internal.xjc.reader.Util;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/test")
public class ServiceController {

    String serialDataFrom8266;//存储从esp8266上传数据

    String light;//查询到的最灯光最后发出的指令
    String steppingMoto;//查询到的对步进电机最后发出的指令
    String dcMoto;//查询到的对直流电机最后发出的指令
    String waterPUmp;//查询到的对水泵最后发出的指令
    String manual;//手动控制最后的状态

    @Autowired
    private com.liqiqi.service.Service service;

    /**
     * 持久化串口传递数据返回响应信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/test")
    public void test(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //查询开灯指令
        List<String> lightList = service.selectLight();
        for (String s : lightList) {
            light=s;
        }
        //查询开步进电机指令
        List<String> steppingMotoList = service.selectSteppingMoto();
        for (String s : steppingMotoList) {
            steppingMoto=s;
        }
        //查询开直流电机指令
        List<String> dcMotoList = service.selectDcMoto();
        for (String s : dcMotoList) {
            dcMoto=s;
        }
        //查询开水泵指令
        List<String> waterPumpList = service.selectWaterPump();
        for (String s : waterPumpList) {
            waterPUmp=s;
        }


        //查询 光照、湿度、温度、土壤湿度、水位阈值

        List<CropGrowthEnvirMessage> cropGrowthEnvirMessages = service.selectNowSelecteCropGrowthEnvirMessage();
        CropGrowthEnvirMessage cropGrowthEnvirMessage = cropGrowthEnvirMessages.get(cropGrowthEnvirMessages.size() - 1);

        String lightThreshold=cropGrowthEnvirMessage.getLightThresholdValue();
        String humidityThreshold=cropGrowthEnvirMessage.getHumidityThresholdValue();
        String temperatureThreshold=cropGrowthEnvirMessage.getTemperatureThresholdValue();
        String soilThreshold=cropGrowthEnvirMessage.getSoilThresholdValue();
        String highThreshold=cropGrowthEnvirMessage.getHighThresholdValue();

        String threshold=lightThreshold+","+humidityThreshold+","+temperatureThreshold+","+soilThreshold+","+highThreshold;


        //将灯光、步进电机、直流电机最后接收到的指令封装到对象中
        info info = new info();//name:灯光 email:步进电机 url:直流电机
        info.setName(light);
        info.setEmail(steppingMoto);
        info.setUrl(dcMoto);

        //将水泵、手动控制最后接收到的指令封装到对象中
        digital_pin digital_pin = new digital_pin();//d1:水泵 d2:手动控制 d3:阈值
        digital_pin.setD1(waterPUmp);
        digital_pin.setD2(manual);
        digital_pin.setD3(threshold);

        //备用封装对象
        analog_pin analog_pin = new analog_pin();
        analog_pin.setA0("a0");

        //将获取到的指令封装到响应信息对象中并将对象转换成json格式
        ResponceMessage responceMessage = new ResponceMessage();
        responceMessage.setInfo(info);
        responceMessage.setDigital_pin(digital_pin);
        responceMessage.setAnalog_pin(analog_pin);
        String s = JSON.toJSONString(responceMessage);
        response.getWriter().write(s);

        //获取8266上传数据
        serialDataFrom8266=request.getParameter("chuankou");

        if (serialDataFrom8266.equals("000")){
            System.out.println("数据下发成功");
        }else {
            System.out.println("esp8266上传数据成功");
            System.out.println("串口数据是"+request.getParameter("chuankou"));

            //获取时间
            String dataTime = Utils.getDataTime();

            //解析串口数据
            //[66,      37,         19,         28,         0.02,       0,      1,      0,              0]
            //0          1          2            3             4        5       6       7               8  index
            //光照值      湿度       温度         土壤湿度        水位   灯状态  水泵状态  直流电机状态      步进电机状态
            List<String> analyseDataFromEsp8266List = Utils.analyseDataFromEsp8266(serialDataFrom8266);

            String lightValue = analyseDataFromEsp8266List.get(0);
            String humidity = analyseDataFromEsp8266List.get(1);
            String temperature = analyseDataFromEsp8266List.get(2);
            String soilValue = analyseDataFromEsp8266List.get(3);
            String high = analyseDataFromEsp8266List.get(4);
            String lightState = analyseDataFromEsp8266List.get(5);
            String pumpState = analyseDataFromEsp8266List.get(6);
            String dcMotoState = analyseDataFromEsp8266List.get(7);
            String steppingMotoState = analyseDataFromEsp8266List.get(8);

            //将解析后的数据和时间封装到对象中并持久化
            Getdatafrom8266 getdatafrom8266 = new Getdatafrom8266();

            getdatafrom8266.setLightValue(lightValue);
            getdatafrom8266.setHumidity(humidity);
            getdatafrom8266.setTemperature(temperature);
            getdatafrom8266.setSoilValue(soilValue);
            getdatafrom8266.setHigh(high);
            getdatafrom8266.setLightState(lightState);
            getdatafrom8266.setPumpState(pumpState);
            getdatafrom8266.setDcMotoState(dcMotoState);
            getdatafrom8266.setSteppingMotoState(steppingMotoState);
            getdatafrom8266.setTime(dataTime);

            service.insertDataFrom8266(getdatafrom8266);
            System.out.println("串口数据存入数据库");
        }


    }

    /**
     * 将灯光指令持久到数据库
     * @param request
     * @param response
     */
    @RequestMapping("/light")
    public void light(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取对灯光下达的指令
        String getLight = request.getParameter("light");

        //获取时间的长整型字符串表现形式
        String dataTime = Utils.getDataTime();

        //将对灯光下达的指令和下达指令的时间封装到对象中并持久化
        LongRangeControlLight longRangeControlLight = new LongRangeControlLight();
        longRangeControlLight.setLongRangeControlLight(getLight);
        longRangeControlLight.setTime(dataTime);
        service.insertLight(longRangeControlLight);

        //查询对手动控制最后的指令
        List<String> manualList=service.selectManual();
        for (String s : manualList) {
            manual=s;
        }

        //向页面跳转并携带手动控制指令
        request.setAttribute("ShowManualStatue",manual);
        request.getRequestDispatcher("/page/longRangeControl.jsp").forward(request,response);

    }

    /**
     * 将步进电机指令持久到数据库
     */
    @RequestMapping("/steppingMotor")
    public void steppingMotor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取对步进电机的指令
        String getSteppingMotor = request.getParameter("steppingMotor");

        //获取时间的长整型字符串表现形式
        String dataTime = Utils.getDataTime();

        //将对步进电机的指令和时间封装到对象并持久化到数据库中
        LongRangeControlSteppingmotor longRangeControlSteppingmotor = new LongRangeControlSteppingmotor();
        longRangeControlSteppingmotor.setLongRangeControlSteppingmotor(getSteppingMotor);
        longRangeControlSteppingmotor.setTime(dataTime);
        service.insertSteppingMotor(longRangeControlSteppingmotor);

        //查询对手动控制最后的指令
        List<String> manualList = service.selectManual();
        for (String s : manualList) {
            manual=s;
        }

        //跳转页面并携带手动控制状态信息
        request.setAttribute("ShowManualStatue",manual);
        request.getRequestDispatcher("/page/longRangeControl.jsp").forward(request,response);
    }

    /**
     * 将直流电机指令持久到数据库
     */
    @RequestMapping("/dcMotor")
    public void dcMotor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取对直流电机下发的指令
        String getDcMotor = request.getParameter("dcMotor");

        //获取时间
        String dataTime = Utils.getDataTime();

        //将获取到的指令和时间封装到实体类中并持久化
        LongRangeControlDcmotor longRangeControlDcmotor = new LongRangeControlDcmotor();
        longRangeControlDcmotor.setLongRangeControlDcmotor(getDcMotor);
        longRangeControlDcmotor.setTime(dataTime);
        service.insertDcMotor(longRangeControlDcmotor);

        //获取远程控制的状态
        List<String> manualList = service.selectManual();
        for (String s : manualList) {
            manual=s;
        }

        //跳转页面并携带远程控制状态
        request.setAttribute("ShowManualStatue",manual);
        request.getRequestDispatcher("/page/longRangeControl.jsp").forward(request,response);
    }

    /**
     * 将水泵指令持久到数据库
     */
    @RequestMapping("/waterPump")
    public void waterPump(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取对水泵的远程控制指令
        String getWaterPump = request.getParameter("waterPump");

        //获取时间
        String dataTime = Utils.getDataTime();

        //将远程控制指令和时间封装到对象中并持久化
        LongRangeControlWaterpump longRangeControlWaterpump = new LongRangeControlWaterpump();
        longRangeControlWaterpump.setLongRangeControlWaterpump(getWaterPump);
        longRangeControlWaterpump.setTime(dataTime);
        service.insertWaterPump(longRangeControlWaterpump);

        //获取手动控制状态
        List<String> manualList = service.selectManual();
        for (String s : manualList) {
            manual=s;
        }

        //跳转页面并携带手动控制状态
        request.setAttribute("ShowManualStatue",manual);
        request.getRequestDispatcher("/page/longRangeControl.jsp").forward(request,response);
    }

    /**
     * 持久化手动控制指令
     */
    @RequestMapping("/manual")
    public void manual(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取对手动控制下发的指令
        String getManual = request.getParameter("manual");

        //获取时间
        String dataTime = Utils.getDataTime();

        //将获得的指令和时间封装到对象中并持久化
        Manual manual = new Manual();
        manual.setManual(getManual);
        manual.setTime(dataTime);
        service.insertManual(manual);

        //获取手动控制状态
        List<String> manualList = service.selectManual();
        for (String s : manualList) {
            this.manual =s;
        }

        //跳转页面并携带手动控制状态
        request.setAttribute("ShowManualStatue", this.manual);
        request.getRequestDispatcher("/page/longRangeControl.jsp").forward(request,response);
    }

    /**
     * index页面reflush按钮
     */
    @RequestMapping("/reflush")
    public void reflush(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //查询手动控制状态
        List<String> manualList = service.selectManual();
        for (String s : manualList) {
            manual=s;
        }

        //跳转页面并携带手动控制状态
        request.setAttribute("ShowManualStatue",manual);
        request.getRequestDispatcher("/page/longRangeControl.jsp").forward(request,response);
    }

    /**
     * 登录校验
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/loginIn")
    public void loginIn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取用户名密码
        String usernameFromPage = request.getParameter("username");
        String passwordFromPage = request.getParameter("password");

        //从数据库中查询所有用户
        List<User> selectUserList = service.selectUser();

        //检验用户名与密码
        for (User user : selectUserList) {
            if(user.getUsername().equals(usernameFromPage)){
                if (user.getPassword().equals(passwordFromPage)){

                    //查询用户选择的农作物和农作物生长环境信息
                    List<CropGrowthEnvirMessage> selectNowSelecteCropGrowthEnvirMessageList= service.selectNowSelecteCropGrowthEnvirMessage();
                    CropGrowthEnvirMessage cropGrowthEnvirMessage = selectNowSelecteCropGrowthEnvirMessageList.get(selectNowSelecteCropGrowthEnvirMessageList.size() - 1);

                    System.out.println(cropGrowthEnvirMessage);
                    //将查询到的结果封装到list中
                    ArrayList<String> list = new ArrayList<String>();
                    list.add(cropGrowthEnvirMessage.getCropsName());
                    list.add(cropGrowthEnvirMessage.getLightThresholdValue());
                    list.add(cropGrowthEnvirMessage.getHumidityThresholdValue());
                    list.add(cropGrowthEnvirMessage.getTemperatureThresholdValue());
                    list.add(cropGrowthEnvirMessage.getSoilThresholdValue());
                    list.add(cropGrowthEnvirMessage.getHighThresholdValue());
                    list.add(Utils.getDataFromLong(cropGrowthEnvirMessage.getTime()));

                    //登录成功跳转菜单页面,携带已设置的农作物生长环境信息
                    request.setAttribute("list",list);
                    request.getRequestDispatcher("/page/chooseAllocation.jsp").forward(request,response);
                }
                break;
            }
        }

        //登录失败跳转loginerror页面
        request.getRequestDispatcher("/page/loginerror.jsp").forward(request,response);

    }

    /**
     * 用户注册
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/register")
    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取用户名和密码
        String usernameFromPage = request.getParameter("username");
        String passwordFromPage = request.getParameter("password");

        //将用户名和密码封装到对象中
        User user = new User();
        user.setUsername(usernameFromPage);
        user.setPassword(passwordFromPage);

        //持久化对象
        service.insertUser(user);

        //返回到注册界面
        request.getRequestDispatcher("/page/register.jsp").forward(request,response);

    }

    @RequestMapping("/historyData")
    public void historyData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        //灯状态
        List<LightActionStatue> selectLightActionList = service.selectLightActionStatue();
        ArrayList<String> list = new ArrayList<String>();

        String lightEndStartTime = null;
        String lightEndEndTime = null;
        int lightFlag=-1;
        int lightCount=0;
        String dataTime = String.valueOf(System.currentTimeMillis());

        for (LightActionStatue lightActionStatue : selectLightActionList) {
            String lightNoteStatue = lightActionStatue.getLightState();
            String time = lightActionStatue.getTime();
            if(lightNoteStatue.equals("0")){
                lightEndEndTime=time;
                lightFlag=0;
            }else {
                if (lightFlag==0){
                    lightEndStartTime=time;
                    int secondDifferent = Utils.getSecondsFromDataTime(lightEndEndTime, dataTime);
                    if(secondDifferent<=24*60*60){
                        lightCount++;
                    }

                    lightFlag=1;
                }

            }
        }

        //风扇 DcMoto

        List<DcMotoActionState> selectDcMotoActionList = service.selectDcMotoActionStatue();

        String dcMotoEndStartTime = null;
        String dcMotoEndEndTime = null;
        int dcMotoFlag=-1;
        int dcMotoCount=0;
        for (DcMotoActionState dcMotoActionStatue : selectDcMotoActionList) {
            String dcMotoNoteStatue =dcMotoActionStatue.getDcMotoState();
            String time = dcMotoActionStatue.getTime();
            if(dcMotoNoteStatue.equals("0")){
                dcMotoEndEndTime=time;
                dcMotoFlag=0;
            }else {
                if (dcMotoFlag==0){
                    dcMotoEndStartTime=time;
                    int secondDifferent = Utils.getSecondsFromDataTime(lightEndEndTime, dataTime);
                    if(secondDifferent<=24*60*60){
                        dcMotoCount++;
                    }
                    dcMotoFlag=1;
                }

            }
        }


        //步进 SteppingMoto steppingMoto
        List<SteppingMotoActionState> selectSteppingMotoActionList = service.selectSteppingMotoActionStatue();

        String steppingMotoEndStartTime = null;
        String steppingMotoEndEndTime = null;
        int steppingMotoFlag=-1;
        int steppingMotoCount=0;
        for (SteppingMotoActionState steppingMotoActionStatue : selectSteppingMotoActionList) {
            String steppingMotoNoteStatue = steppingMotoActionStatue.getSteppingMotoState();
            String time = steppingMotoActionStatue.getTime();
            if(steppingMotoNoteStatue.equals("0")){
                steppingMotoEndEndTime=time;
                steppingMotoFlag=0;
            }else {
                if (steppingMotoFlag==0){
                    steppingMotoEndStartTime=time;
                    int secondDifferent = Utils.getSecondsFromDataTime(lightEndEndTime, dataTime);
                    if(secondDifferent<=24*60*60){
                        steppingMotoCount++;
                    }
                    steppingMotoFlag=1;
                }

            }
        }


        //水泵 Pump pump

        List<PumpActionState> selectPumpActionList = service.selectPumpActionStatue();

        String pumpEndStartTime = null;
        String pumpEndEndTime = null;
        int pumpFlag=-1;
        int pumpCount=0;
        for (PumpActionState pumpActionStatue : selectPumpActionList) {
            String pumpNoteStatue = pumpActionStatue.getPumpState();
            String time = pumpActionStatue.getTime();
            if(pumpNoteStatue.equals("0")){
                pumpEndEndTime=time;
                pumpFlag=0;
            }else {
                if (pumpFlag==0){
                    pumpEndStartTime=time;
                    int secondDifferent = Utils.getSecondsFromDataTime(lightEndEndTime, dataTime);
                    if(secondDifferent<=24*60*60){
                        pumpCount++;
                    }
                    pumpFlag=1;
                }

            }
        }


        //24小时内灌溉时间 Soil soil
        List<Soil> selectSoilTimeList = service.selectSoilTime();
        ArrayList<String> timeList = new ArrayList<String>();
        String soilEndEndTime = null;
        int soilFlag=-1;
        for (Soil soil : selectSoilTimeList) {
            String soilNoteStatue = soil.getSoilValue();
            String time = soil.getTime();
            if(Integer.parseInt(soilNoteStatue)>50){
                soilFlag=0;
                soilEndEndTime=time;
                System.out.println(soilNoteStatue);
            }else {
                if (soilFlag==0){
                    int secondDifferent = Utils.getSecondsFromDataTime(soilEndEndTime, dataTime);
                    System.out.println(secondDifferent);
                    if(secondDifferent<=240*60*60){
                        timeList.add(Utils.getDataFromLong(time));
                    }
                    soilFlag=1;
                }

            }
        }

        list.add(Utils.getDataFromLong(lightEndStartTime));
        list.add(Utils.getDataFromLong(lightEndEndTime));

        list.add(Utils.getDataFromLong(dcMotoEndStartTime));
        list.add(Utils.getDataFromLong(dcMotoEndEndTime));

        list.add(Utils.getDataFromLong(steppingMotoEndStartTime));
        list.add(Utils.getDataFromLong(steppingMotoEndEndTime));

        list.add(Utils.getDataFromLong(pumpEndStartTime));
        list.add(Utils.getDataFromLong(pumpEndEndTime));

        list.add(String.valueOf(lightCount));
        list.add(String.valueOf(dcMotoCount));
        list.add(String.valueOf(steppingMotoCount));
        list.add(String.valueOf(pumpCount));




        request.setAttribute("list",list);
        request.setAttribute("lists",timeList);
        request.getRequestDispatcher("/page/historyData.jsp").forward(request,response);








    }

    /**
     * 传感器状态信息处理
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/sensorStatue")
    public void sensorStatue(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Sensors> selectSensorsValueList = service.selectSensorValue();
        Sensors selectSensor = selectSensorsValueList.get(selectSensorsValueList.size() - 1);

        int lightValue = Integer.parseInt(selectSensor.getLightValue());
        Double high = Double.parseDouble(selectSensor.getHigh());
        Long humidity = Long.parseLong(selectSensor.getHumidity());
        Double soilValue = Double.parseDouble(selectSensor.getSoilValue());

        ArrayList<String> sensorStatueList = new ArrayList<String>();

        if (lightValue>0&&lightValue<100){
            sensorStatueList.add("工作正常");
            sensorStatueList.add(Utils.getDataFromLong(selectSensor.getTime()));
        }else{
            sensorStatueList.add("工作异常");
            sensorStatueList.add(Utils.getDataFromLong(selectSensor.getTime()));
        }

        if(high>0&&high<10){
            sensorStatueList.add("工作正常");
            sensorStatueList.add(Utils.getDataFromLong(selectSensor.getTime()));
        }else{
            sensorStatueList.add("工作异常");
            sensorStatueList.add(Utils.getDataFromLong(selectSensor.getTime()));
        }

        if(humidity>0&&humidity<100){
            sensorStatueList.add("工作正常");
            sensorStatueList.add(Utils.getDataFromLong(selectSensor.getTime()));
        }else {
            sensorStatueList.add("工作异常");
            sensorStatueList.add(Utils.getDataFromLong(selectSensor.getTime()));
        }

        if(soilValue>0&&soilValue<100){
            sensorStatueList.add("工作正常");
            sensorStatueList.add(Utils.getDataFromLong(selectSensor.getTime()));
        }else{
            sensorStatueList.add("工作异常");
            sensorStatueList.add(Utils.getDataFromLong(selectSensor.getTime()));
        }


        request.setAttribute("sensorStatue",sensorStatueList);
        request.getRequestDispatcher("/page/sensorState.jsp").forward(request,response);




    }

    /**
     * 查询所有农作物生长环境
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/getAllCropGrowthEnvirMessage")
    public void getAllCropGrowthEnvirMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        //查询数据库中所有农作物生长信息
        List<CropGrowthEnvirMessage> getAllCropGrowthEnvirMessageLsit=service.selectAllCropGrowthEnvirMessage();



        //存储集合的集合
        ArrayList<ArrayList<String>> listBig = new ArrayList<ArrayList<String>>();

        //封装成集合套集合形式
        for (CropGrowthEnvirMessage cropGrowthEnvirMessage : getAllCropGrowthEnvirMessageLsit) {

            //存储一个对象所有信息的集合
            ArrayList<String> listSmall = new ArrayList<String>();
            listSmall.add(cropGrowthEnvirMessage.getCropsName());
            listSmall.add(cropGrowthEnvirMessage.getLightThresholdValue());
            listSmall.add(cropGrowthEnvirMessage.getHumidityThresholdValue());
            listSmall.add(cropGrowthEnvirMessage.getTemperatureThresholdValue());
            listSmall.add(cropGrowthEnvirMessage.getSoilThresholdValue());
            listSmall.add(cropGrowthEnvirMessage.getHighThresholdValue());
            listSmall.add(Utils.getDataFromLong(cropGrowthEnvirMessage.getTime()));

            listBig.add(listSmall);
        }



        //转发 allCropGrowthEnvirMessage 页面携带所有农作物生长信息
        request.setAttribute("lists",listBig);
        request.getRequestDispatcher("/page/allCropGrowthEnvirMessage.jsp").forward(request,response);

    }

    /**
     * 持久化选定农作物和生长环境状态到选定农作物生长状态表
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/insertSelectedCropGrowthEnvirMessage")
    public void insertSelectedCropGrowthEnvirMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取选定农作物的名称
        String getCropName = request.getParameter("name");
        System.out.println(getCropName);

        //查询选定作物生长信息
        CropGrowthEnvirMessage selectCropGrowthEnvirMessageObject= service.selectCropGrowthEnvirMessage(getCropName);

        //将信息持久化到当前选定环境表
        service.insertNowSelecteCropGrowthEnvirMessage(selectCropGrowthEnvirMessageObject);

        //将选定作物信息封装到集合中
        ArrayList<String> list = new ArrayList<String>();
        list.add(selectCropGrowthEnvirMessageObject.getCropsName());
        list.add(selectCropGrowthEnvirMessageObject.getLightThresholdValue());
        list.add(selectCropGrowthEnvirMessageObject.getHumidityThresholdValue());
        list.add(selectCropGrowthEnvirMessageObject.getTemperatureThresholdValue());
        list.add(selectCropGrowthEnvirMessageObject.getSoilThresholdValue());
        list.add(selectCropGrowthEnvirMessageObject.getHighThresholdValue());
        list.add(Utils.getDataFromLong(selectCropGrowthEnvirMessageObject.getTime()));

        //跳转页面携带当前选定的作物全部生长信息 chooseAllocation
        request.setAttribute("list",list);
        request.getRequestDispatcher("/page/chooseAllocation.jsp").forward(request,response);



    }


    /**
     * 持久化用户设定的农作物以及生长环境信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/insertCropGrowthEnvirMessage")
    public void insertCropGrowthEnvirMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

       //获取用户设定的农作物名称、光照阈值、大气湿度阈值、大气温度阈值、土壤湿度阈值、水库安全水位、时间
        String cropsName = request.getParameter("cropsName");
        String lightThresholdValue = request.getParameter("lightThresholdValue");
        String humidityThresholdValue = request.getParameter("humidityThresholdValue");
        String temperatureThresholdValue = request.getParameter("temperatureThresholdValue");
        String soilThresholdValue = request.getParameter("soilThresholdValue");
        String highThresholdValue = request.getParameter("highThresholdValue");

        //获取时间
        String dataTime = Utils.getDataTime();

        //将获取到的信息封装到对象中
        CropGrowthEnvirMessage cropGrowthEnvirMessage = new CropGrowthEnvirMessage();
        cropGrowthEnvirMessage.setCropsName(cropsName);
        cropGrowthEnvirMessage.setLightThresholdValue(lightThresholdValue);
        cropGrowthEnvirMessage.setHumidityThresholdValue(humidityThresholdValue);
        cropGrowthEnvirMessage.setTemperatureThresholdValue(temperatureThresholdValue);
        cropGrowthEnvirMessage.setSoilThresholdValue(soilThresholdValue);
        cropGrowthEnvirMessage.setHighThresholdValue(highThresholdValue);
        cropGrowthEnvirMessage.setTime(dataTime);


        //将对象持久化到农作物环境生长信息表、已选定农作物环境生长信息表
        service.insertCropGrowthEnvirMessage(cropGrowthEnvirMessage);
        service.insertNowSelecteCropGrowthEnvirMessage(cropGrowthEnvirMessage);

        //将获取到的信息封装到列表中
        ArrayList<String> list = new ArrayList<String>();
        list.add(cropGrowthEnvirMessage.getCropsName());
        list.add(cropGrowthEnvirMessage.getLightThresholdValue());
        list.add(cropGrowthEnvirMessage.getHumidityThresholdValue());
        list.add(cropGrowthEnvirMessage.getTemperatureThresholdValue());
        list.add(cropGrowthEnvirMessage.getSoilThresholdValue());
        list.add(cropGrowthEnvirMessage.getHighThresholdValue());
        list.add(Utils.getDataFromLong(cropGrowthEnvirMessage.getTime()));

        //跳转页面并携带信息
        request.setAttribute("list",list);
        request.getRequestDispatcher("/page/chooseAllocation.jsp").forward(request,response);



    }

    /**
     * 查询用户选定的农作物    menu-->chooseAllocation
     * @param
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/selectedCrop")
    public void selectedCrop(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //查询用户选定的农作物
        List<CropGrowthEnvirMessage> cropGrowthEnvirMessages = service.selectNowSelecteCropGrowthEnvirMessage();
        CropGrowthEnvirMessage cropGrowthEnvirMessage = cropGrowthEnvirMessages.get(cropGrowthEnvirMessages.size() - 1);

        //将获取到的信息封装到列表中
        ArrayList<String> list = new ArrayList<String>();
        list.add(cropGrowthEnvirMessage.getCropsName());
        list.add(cropGrowthEnvirMessage.getLightThresholdValue());
        list.add(cropGrowthEnvirMessage.getHumidityThresholdValue());
        list.add(cropGrowthEnvirMessage.getTemperatureThresholdValue());
        list.add(cropGrowthEnvirMessage.getSoilThresholdValue());
        list.add(cropGrowthEnvirMessage.getHighThresholdValue());
        list.add(Utils.getDataFromLong(cropGrowthEnvirMessage.getTime()));

        //跳转页面并携带信息
        request.setAttribute("list",list);
        request.getRequestDispatcher("/page/chooseAllocation.jsp").forward(request,response);



    }










}
