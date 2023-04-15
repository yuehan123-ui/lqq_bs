package com.liqiqi.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//风扇运行状态实体类
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DcMotoActionState {

    private String dcMotoState;//风扇运行状态
    private String time;//时间
}
