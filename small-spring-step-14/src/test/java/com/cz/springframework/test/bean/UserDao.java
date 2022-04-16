package com.cz.springframework.test.bean;

import com.cz.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/** @author ChangZhen */
@Component
public class UserDao {
    private static Map<String, String> hashMap = new HashMap<>();

    static {
        System.out.println("执行：init-method");
        hashMap.put("10001", "张三");
        hashMap.put("10002", "李四");
        hashMap.put("10003", "小明");
    }

    public String queryUserName(String uId) {
        return hashMap.get(uId);
    }
}
