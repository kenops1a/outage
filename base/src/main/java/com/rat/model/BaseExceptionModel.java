package com.rat.model;

import lombok.Data;

/**
 * @description:
 * @tip: write_bug
 * @author: yaominc
 * @date: 10/4/2022 下午7:47
 */
@Data
public class BaseExceptionModel extends RuntimeException{

    private Integer code;

    private String message;

    private Object data;

    public BaseExceptionModel(String message) {
        this.code = 500;
        this.message = message;
        this.data = null;
    }
}
