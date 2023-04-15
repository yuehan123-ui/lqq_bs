package com.liqiqi.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//灯运行状态实体类
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LightActionStatue {

    private String lightState;//灯状态
    private String time;//时间
}
