package com.liqiqi.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//封装响应数据实体类
@Data
@AllArgsConstructor
@NoArgsConstructor
public class digital_pin {

    private String d1;//存储对水泵远程指令
    private String d2;//存储手动控制状态
    private String d3;//备用

}
