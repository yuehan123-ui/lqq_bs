package com.liqiqi.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
//用户实体类
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    private String username;//用户名
    private String password;//密码

}
