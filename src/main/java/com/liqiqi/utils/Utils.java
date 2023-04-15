package com.liqiqi.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 自定义封装的工具类
 */
public class Utils {

    /**
     * 返回当前日期的毫秒值字符串形式
     * @return
     */
    public static String getDataTime(){
        return String.valueOf(System.currentTimeMillis());
    }

    /**
     * 计算出两个长整型日期时间差以秒位单位
     * @param smallDataTime
     * @param biggerTime
     * @return
     */
    public static int getSecondsFromDataTime(String smallDataTime,String biggerTime){
        return (int) (Long.parseLong(biggerTime)-Long.parseLong(smallDataTime))/1000;

    }

    /**
     * 分割串口数据返回列表
     * @param dataFromesp8266
     * @return
     */
    public static List<String> analyseDataFromEsp8266(String dataFromesp8266){
        ArrayList<String> list = new ArrayList<String>();
        String[] s = dataFromesp8266.split("s");
        for (String s1 : s) {
            list.add(s1);
        }
        list.remove(0);
        String removeString = list.remove(0);//350011
        String lightValue = removeString.substring(0, removeString.length() - 4);//35
        list.add(0,lightValue);
        String substring = removeString.substring(removeString.length() - 4, removeString.length());//0011
        for (int i = 0; i < substring.length(); i++) {
            String s1 = String.valueOf(substring.charAt(i));
            list.add(s1);
        }


        return list;
    }


    /**
     * 根据long型时间转换成标准时间
     * @param dataTime
     * @return
     */
    public static String getDataFromLong(String dataTime){
        long l = Long.parseLong(dataTime);
        Date date = new Date(l);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年/MM月/dd日 HH时:mm分:ss秒");
        String format = simpleDateFormat.format(date);
        return format;
    }



    /**
     * 测试函数
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(Utils.analyseDataFromEsp8266("s660100s37s19s28s0.02"));
    }
}
