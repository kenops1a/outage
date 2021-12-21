package com.rat.info;

import com.rat.info.JsonResult;
/**
 * @type: outage
 * @author: yaominc
 * @description: write_bug
 * @date: 2021/12/10 11:36
 */
public class ResultTool {
    // JsonResult泛型的原生态类型使用, 即不指明参数类型, 例如List list = new ArrayList(),
    // 该用法不推荐
    public static<T> JsonResult<T> success() {
        return new JsonResult<T>(true);
    }
    public static<T> JsonResult<T> success(T data) {
        return new JsonResult<T>(true, data);
    }
    public static<T> JsonResult<T> faild() {
        return new JsonResult<T>(false);
    }
    public static<T> JsonResult<T> faild(T data) {
        return new JsonResult<T>(false, data);
    }
    public static<T> JsonResult<T> faild(ResultCode resultEnum) {
        return new JsonResult<T>(false,resultEnum);
    }
}
