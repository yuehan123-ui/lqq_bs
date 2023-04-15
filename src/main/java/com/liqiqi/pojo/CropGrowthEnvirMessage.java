package com.liqiqi.pojo;

//农作物生长信息实体类

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CropGrowthEnvirMessage {

    private String cropsName;//作物名称
    private String lightThresholdValue;//光照阈值
    private String humidityThresholdValue;//大气湿度阈值
    private String temperatureThresholdValue;//大气温度阈值
    private String soilThresholdValue;//土壤湿度阈值
    private String highThresholdValue;//水库安全水位
    private String time;//时间
}
