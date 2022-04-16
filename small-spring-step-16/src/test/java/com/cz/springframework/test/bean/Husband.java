package com.cz.springframework.test.bean;

/**
 * @author ChangZhen
 */
public class Husband {
    private Wife wife;

    public String queryWife(){
        return "Husband.wife";
    }

    public Wife getWife() {
        return wife;
    }

    public void setWife(Wife wife) {
        this.wife = wife;
    }
}
