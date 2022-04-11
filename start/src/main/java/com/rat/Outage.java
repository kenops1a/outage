package com.rat;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @type: outage
 * @author: yaominc
 * @description: write_bug
 * @date: 2021/12/9 17:04
 */
@SpringBootApplication
@MapperScan("com.rat.mapper")
public class Outage {
    public static void main(String[] args) {
        SpringApplication.run(Outage.class, args);
        System.out.println("启动成功 : )");
    }
}
