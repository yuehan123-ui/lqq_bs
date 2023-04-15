package com.liqiqi.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//封装响应数据实体类
@Data
@AllArgsConstructor
@NoArgsConstructor
public class info {

    private String name;//封装对灯光的远程指令
    private String url;//封装对步进电机的远程指令
    private String email;//封装对直流电机的远程指令
}
