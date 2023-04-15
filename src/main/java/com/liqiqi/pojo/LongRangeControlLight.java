package com.liqiqi.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//远程控制灯实体类
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LongRangeControlLight {

    private String longRangeControlLight;//远程控制灯光指令值
    private String time;//时间
}
