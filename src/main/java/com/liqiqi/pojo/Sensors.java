package com.liqiqi.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//所有传感器实体类
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sensors {
    private String lightValue;//光照值
    private String high;//水位值
    private String humidity;//湿度值
    private String soilValue;//土壤湿度值
    private String time;

}
