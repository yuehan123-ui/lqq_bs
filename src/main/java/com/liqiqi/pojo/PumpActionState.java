package com.liqiqi.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//水泵工作状态实体类
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PumpActionState {

    private String pumpState;//水泵执行状态
    private String time;//时间
}
