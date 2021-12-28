package com.rat.controller;

import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.rat.model.UserModel;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @type: outage
 * @author: yaominc
 * @description: write_bug
 * @date: 2021/12/14 15:06
 */

public class Test implements Serializable {

    private static final String TEST_JSON = "what's";
    private static final UserModel USER_MODEL = new UserModel();

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private Date date2;
    @org.junit.Test
    public void formatDate() {
        date = new Date();
        date2 = new Date();
        System.out.println(date2);
        System.out.println(JSONUtil.toJsonStr(date));
    }

    @org.junit.Test
    public void soutTime() {
        System.out.println(new Date());
        System.out.println(System.currentTimeMillis());
        System.out.println(stampToDate(System.currentTimeMillis()));
    }

    public void finalTest() {
        USER_MODEL.setEmail("fasdfs");
    }

    public static String stampToDate(long time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date(time * 1000L));
    }

    @org.junit.Test
    public void Calendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        System.out.println(calendar.getTime());
        calendar.add(Calendar.YEAR, -1);
        System.out.println(calendar.getTime());
    }
}
