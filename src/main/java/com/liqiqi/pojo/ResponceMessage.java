package com.liqiqi.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//封装响应数据
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponceMessage {

    private info info;//灯光、步进电机、直流电机
    private digital_pin digital_pin;//水泵、手动控制
    private analog_pin analog_pin;//备用
}
