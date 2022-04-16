package com.cz.springframework.test.bean;

import java.util.StringJoiner;

/**
 * @author ChangZhen
 * @date 2021/11/6
 */
public class UserService {

    private String name;

    public void queryUserInfo() {
        System.out.println("查询用户信息：" + this.name);
    }

    public UserService() {
        System.out.println("调用了无参构造方法");
    }

    /**
     * UserService 中添加的就是一个有 name 入参的构造方法，方便验证这样的对象是否能被实例化
     */
    public UserService(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", UserService.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .toString();
    }
}
