package com.rat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @type: outage
 * @author: yaominc
 * @description: write_bug
 * @date: 2021/12/9 17:04
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class Outage {
    public static void main(String[] args) {
        SpringApplication.run(Outage.class, args);
        System.out.println("启动成功 : )");
    }
}
