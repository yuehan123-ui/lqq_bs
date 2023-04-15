package com.liqiqi.service.impl;

import com.liqiqi.dao.UserDao;
import com.liqiqi.pojo.*;
import com.liqiqi.service.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@org.springframework.stereotype.Service
public class ServiceImpl implements Service {

    @Autowired
    private UserDao userDao;

    public User findUserByid(Integer userId) {
        return userDao.findUserByid(userId);
    }

    /**
     * 持久化对灯光的指令
     * @param light
     */
    public void insertLight(LongRangeControlLight light) {
        userDao.insertLight(light);
    }

    /**
     * 查询对灯光最后的指令
     * @return
     */
    public List<String> selectLight() {
        List<String> getLight=userDao.selectLight();
        return getLight;
    }

    /**
     * 持久化对步进电机的指令
     * @param getSteppingMotor
     */
    public void insertSteppingMotor(LongRangeControlSteppingmotor getSteppingMotor) {
        userDao.insertSteppingMotor(getSteppingMotor);
    }

    /**
     * 持久化对直流电机的指令
     * @param getDcMotor
     */
    public void insertDcMotor(LongRangeControlDcmotor getDcMotor) {

        userDao.insertDcMotor(getDcMotor);
    }

    /**
     * 持久化对水泵的指令
     * @param getWaterPump
     */
    public void insertWaterPump(LongRangeControlWaterpump getWaterPump) {

        userDao.insertWaterPump(getWaterPump);
    }

    /**
     * 查询对步进电机最后的指令
     * @return
     */
    public List<String> selectSteppingMoto() {
        return userDao.selectSteppingMoto();
    }

    /**
     * 查询对直流电机最后的指令
     * @return
     */
    public List<String> selectDcMoto() {
        return userDao.selectDcMoto();
    }

    /**
     * 持久化农作物生长环境信息
     * @param cropGrowthEnvirMessage
     */
    public void insertCropGrowthEnvirMessage(CropGrowthEnvirMessage cropGrowthEnvirMessage) {
        userDao.insertCropGrowthEnvirMessage(cropGrowthEnvirMessage);
    }

    /**
     * 查询用户选择的农作物和农作物生长环境信息
     * @return
     */
    public List<CropGrowthEnvirMessage> selectNowSelecteCropGrowthEnvirMessage() {
        return userDao.selectNowSelecteCropGrowthEnvirMessage();
    }

    /**
     * 持久化当前选定作物生长环境表
     * @param selectCropGrowthEnvirMessageObject
     */
    public void insertNowSelecteCropGrowthEnvirMessage(CropGrowthEnvirMessage selectCropGrowthEnvirMessageObject) {
        userDao.insertNowSelecteCropGrowthEnvirMessage(selectCropGrowthEnvirMessageObject);
    }

    /**
     * 查询选定农作物生长信息
     * @param getCropName
     * @return
     */
    public CropGrowthEnvirMessage selectCropGrowthEnvirMessage(String getCropName) {
        return userDao.selectCropGrowthEnvirMessage(getCropName);
    }

    /**
     * 查询数据库中所有农作物生长信息
     * @return
     */
    public List<CropGrowthEnvirMessage> selectAllCropGrowthEnvirMessage() {
        return userDao.selectAllCropGrowthEnvirMessage();
    }

    /**
     * 查询对水泵最后的指令
     * @return
     */
    public List<String> selectWaterPump() {
        return userDao.selectWaterPump();
    }

    /**
     * 持久化对手动控制的指令
     * @param getManual
     */
    public void insertManual(Manual getManual) {
        userDao.insertManual(getManual);
    }

    public void test(){
        System.out.println("service///");
    }

    /**
     * 将串口数据持久化到数据库
     * @param serialDataFrom8266
     */
    public void insertDataFrom8266(Getdatafrom8266 serialDataFrom8266) {
        userDao.insertDataFrom8266(serialDataFrom8266);
    }

    /**
     * 获取所有传感器采集到的值
     * @return
     */
    public List<Sensors> selectSensorValue() {
        return userDao.selectSensorValue();
    }

    /**
     * 查询土壤湿度值和时间
     * @return
     */
    public List<Soil> selectSoilTime() {
        return userDao.selectSoilTime();
    }

    /**
     * 查询水泵执行状态记录
     * @return
     */
    public List<PumpActionState> selectPumpActionStatue() {
        return userDao.selectPumpActionStatue();
    }

    /**
     * 查询步进电机执行状态记录
     * @return
     */
    public List<SteppingMotoActionState> selectSteppingMotoActionStatue() {
        return userDao.selectSteppingMotoActionStatue();
    }

    /**
     * 查询风扇执行状态记录
     * @return
     */
    public List<DcMotoActionState> selectDcMotoActionStatue() {
        return userDao.selectDcMotoActionStatue();
    }

    /**
     * 查询灯执行状态记录
     * @return
     */
    public List<LightActionStatue> selectLightActionStatue() {
        return userDao.selectLightActionStatue();
    }

    /**
     *持久化用户
     * @param user
     */
    public void insertUser(User user) {
        userDao.insertUser(user);
    }

    /**
     * 查询所有用户
     * @return
     */
    public List<User> selectUser() {
        return userDao.selectUser();
    }

    /**
     * 查询对手动控制最后的指令
     * @return
     */
    public List<String> selectManual() {
        return userDao.selectManual();
    }
}
