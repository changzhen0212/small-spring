package com.cz.springframework.test.bean;

/**
 * @author ChangZhen
 * @date 2021/11/6
 */
public class UserService {

    private String name;

    public void queryUserInfo() {
        System.out.println("查询用户信息");
    }

    public UserService() {
    }

    public UserService(String name) {
        this.name = name;
    }
}
