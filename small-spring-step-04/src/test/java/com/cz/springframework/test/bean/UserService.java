package com.cz.springframework.test.bean;

/**
 * @author ChangZhen
 */
public class UserService {
    private String uId;

    private UserDao userDao;

    public void queryUserInfo(){
        String s = userDao.queryUserName(uId);
        System.out.println(s);
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
