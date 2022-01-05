package com.rat.service.impl;

import com.rat.service.MyLambdaInterface;

import java.sql.SQLOutput;

/**
 * @type: outage
 * @author: yaoMinC
 * @description: 测试lambda函数
 * @date: 2021/12/31 15:56
 */
public class LambdaInterfaceTest {
    public static void main(String[] args) {

        MyLambdaInterface aBlockOfCode = (String s) -> System.out.println(s);

        MyLambdaInterface aBlockOfCode2 = System.out::println;
    }
}
