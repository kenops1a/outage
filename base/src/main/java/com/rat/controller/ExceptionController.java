package com.rat.controller;

import com.rat.info.JsonResult;
import com.rat.model.BaseExceptionModel;
import com.rat.model.FileModel;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description:
 * @tip: write_bug
 * @author: yaominc
 * @date: 10/4/2022 下午7:42
 */
@ControllerAdvice
public class ExceptionController {

    @ResponseBody
    @ExceptionHandler
    public JsonResult<String> handleException(Exception exception) {
        if (exception.getClass().equals(BaseExceptionModel.class)) {
            return new JsonResult<>(false, exception.getMessage());
        }

        return  new JsonResult<>(false, "系统未知错误");
    }
}
