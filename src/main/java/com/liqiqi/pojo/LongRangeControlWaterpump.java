package com.liqiqi.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//水泵远程指令实体类
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LongRangeControlWaterpump {

    private String longRangeControlWaterpump;//对水泵的远程控制指令
    private String time;//时间
}
