package com.liqiqi.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//步进电机运行状态实体类
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SteppingMotoActionState {

    private String steppingMotoState;//步进电机运行状态
    private String time;//时间
}
