package com.liqiqi.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//土壤湿度传感器实体类
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Soil {

    private String soilValue;//土壤湿度值
    private String time;//时间
}
