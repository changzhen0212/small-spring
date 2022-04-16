package com.cz.springframework.test.bean;

import cn.hutool.core.bean.BeanException;
import com.cz.springframework.beans.factory.DisposableBean;
import com.cz.springframework.beans.factory.InitializingBean;

import java.util.StringJoiner;

/** @author ChangZhen */
public class UserService implements InitializingBean, DisposableBean {
    private String uId;

    private String company;

    private String location;

    private UserDao userDao;

    public String queryUserInfo() {
        return userDao.queryUserName(uId);
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", UserService.class.getSimpleName() + "[", "]")
                .add("uId='" + uId + "'")
                .add("company='" + company + "'")
                .add("location='" + location + "'")
                .add("userDao=" + userDao)
                .toString();
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("执行：UserService.destroy");
    }

    @Override
    public void afterPropertiesSet() throws BeanException {
        System.out.println("执行：UserService.afterPropertiesSet");
    }
}
