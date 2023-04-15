package com.liqiqi.pojo;
//8266上传数据实体类

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Getdatafrom8266 {

    private String lightValue;//光照值
    private String humidity;//大气湿度
    private String temperature;//大气温度
    private String soilValue;//土壤湿度
    private String high;//水位
    private String lightState;//灯状态
    private String pumpState;//水泵状态
    private String dcMotoState;//直流电机状态
    private String steppingMotoState;//步进电机状态
    private String time;//时间

}
