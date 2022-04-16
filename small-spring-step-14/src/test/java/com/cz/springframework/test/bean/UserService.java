package com.cz.springframework.test.bean;

import com.cz.springframework.beans.factory.annotation.Autowired;
import com.cz.springframework.beans.factory.annotation.Value;
import com.cz.springframework.stereotype.Component;

import java.util.Random;

/** @author ChangZhen */
@Component("userService")
public class UserService implements IUserService {

    @Value("${token}")
    private String token;

    @Autowired
    private UserDao userDao;

    @Override
    public String queryUserInfo() {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return userDao.queryUserName("10001") + "，" + token;
    }

    @Override
    public String register(String userName) {
        return null;
    }
}
