package com.liqiqi.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//手动控制实体类
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Manual {

    private String manual;//手动控制指令
    private String time;//时间
}
