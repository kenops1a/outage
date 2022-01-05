package com.rat.controller;

import java.awt.event.ActionListener;
import java.util.function.*;

/**
 * @author: yaoMinC
 * @description: write_bug
 * @date: 2021/12/31 16:07
 */

public class LambdaTestController {

    /**
     * 执行方法
     * @param lamInterface lambda函数测试接口
     */
    public static void execute(LambdaTestInterface lamInterface) {

        lamInterface.doSomeWork();
    }

    public static void main(String[] args) {

        // 创建接口对象
        // invoke doSomeWork using Annonymous class
        execute(new LambdaTestInterface() {
            @Override
            public void doSomeWork() {
                System.out.println("worker invoked using Anonymous class");
            }
        });

        // lambda表达式创建接口对象
        // invoke doSomeWork using lambda expression
        execute(() -> System.out.println("worker invoked using Anonymous class"));

    }
}
