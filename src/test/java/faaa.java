import com.alibaba.fastjson.JSON;

import com.liqiqi.pojo.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class faaa {


    public static void main(String[] args) {


        User user = new User();
        user.setUsername("111");
        user.setPassword("222");

        System.out.println(Arrays.asList(user.getUsername(),user.getPassword()));


    }
}
