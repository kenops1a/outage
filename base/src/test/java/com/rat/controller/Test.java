package com.rat.controller;

import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.io.Serializable;
import java.util.Date;

/**
 * @type: outage
 * @author: yaominc
 * @description: write_bug
 * @date: 2021/12/14 15:06
 */

public class Test implements Serializable {
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
    }
}
