package com.liqiqi.dao;

import com.liqiqi.pojo.*;

import java.util.List;

public interface UserDao {

    User findUserByid(Integer userId);

    //持久化对灯光指令
    void insertLight(LongRangeControlLight longRangeControlLight);

    //查询对灯光最后的指令
    List<String> selectLight();

    //持久化对步进电机的指令
    void insertSteppingMotor(LongRangeControlSteppingmotor getSteppingMotor);

    //持久化对直流电机的指令
    void insertDcMotor(LongRangeControlDcmotor getDcMotor);

    //持久化对水泵的指令
    void insertWaterPump(LongRangeControlWaterpump getWaterPump);

    //查询对步进电机最后的指令
    List<String> selectSteppingMoto();

    //查询对直流电机最后的指令
    List<String> selectDcMoto();

    //查询对水泵最后的指令
    List<String> selectWaterPump();

    //持久化手动控制指令
    void insertManual(Manual getManual);

    //查询对手动控制最后的指令
    List<String> selectManual();

    //将串口数据持久化到数据库
    void insertDataFrom8266(Getdatafrom8266 serialDataFrom8266);

    //查询所有用户
    List<User> selectUser();

    //持久化用户
    void insertUser(User user);

    //查询灯执行状态记录
    List<LightActionStatue> selectLightActionStatue();

    //查询风扇运行状态记录
    List<DcMotoActionState> selectDcMotoActionStatue();

    //查询步进电机执行状态记录
    List<SteppingMotoActionState> selectSteppingMotoActionStatue();

    //查询水泵执行状态记录
    List<PumpActionState> selectPumpActionStatue();

    //查询土壤湿度值和时间
    List<Soil> selectSoilTime();

    //获取所有传感器采集到的值
    List<Sensors> selectSensorValue();

    //查询数据库中所有农作物生长信息
    List<CropGrowthEnvirMessage> selectAllCropGrowthEnvirMessage();

    //查询选定农作物生长环境信息
    CropGrowthEnvirMessage selectCropGrowthEnvirMessage(String getCropName);

    //持久化当前选定作物环境表
    void insertNowSelecteCropGrowthEnvirMessage(CropGrowthEnvirMessage selectCropGrowthEnvirMessageObject);

    //查询用户选择的农作物和农作物生长环境信息
    List<CropGrowthEnvirMessage> selectNowSelecteCropGrowthEnvirMessage();

    //持久化农作物生长环境信息
    void insertCropGrowthEnvirMessage(CropGrowthEnvirMessage cropGrowthEnvirMessage);
}
