package com.rat.info;

import org.omg.CORBA.NO_PERMISSION;

/**
 * @type: outage
 * @author: yaominc
 * @description: write_bug
 * @date: 2021/12/10 10:31
 *  #1表示成功
 *  #1001～1999 区间表示参数错误
 *  #2001～2999 区间表示用户错误
 *  #3001～3999 区间表示接口异常
 */
public enum ResultCode {
    /* 成功 */
    SUCCESS(200, "SUCCESS"),

    /* 默认失败 */
    COMMON_FAIL(999, "FAILD"),

    /* 参数错误 */
    PARAM_NOT_VALID(1001, "参数格式不正确"),
    PARAM_IS_BLANK(1002, "参数不能为空"),
    PARAM_TYPE_ERROR(1003, "参数类型错误"),
    PARAM_IS_REQUIRED(1004, "缺少参数"),

    /* 用户错误 */
    USER_NOT_LOGIN(2001, "用户未登录"),
    USER_ACCOUNT_EXPIRED(2002, "账号失效"),
    USER_PASSWORD_ERROR(2003, "密码错误"),
    USER_PASSWORD_EXPIRED(2004, "密码失效"),
    USER_ACCOUNT_LOCKED(2005, "账号冻结"),
    USER_ACCOUNT_NOT_EXIST(2006, "账号不存在"),
    USER_ACCOUNT_ALREADY_EXIST(2007, "账号已存在"),
    USER_ACCOUNT_EXIT(2008, "账号已登录"),

    /* 业务错误 */
    NO_PERMISSION(3001, "没有权限"),
    ITEM_ALREADY_EXIST(3002, "参数为空"),
    ITEM_NOT_EXIST(3003, "记录为空"),
    ITEM_CAN_NOT_ALTER(3004, "此记录无法修改");
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private Integer code;
    private String message;
    private String test;

    /* ResultCode方法中的参数数量与枚举常量参数数量相同 */

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 根据code获取message
     */
    public static String getMessageByCode(Integer code) {
        /* values()返回一个数组，该数组包含此枚举类型的常量，按声明顺序排序*/
        for (ResultCode rc : values()) {
            if (rc.getCode().equals(code)) {
                return rc.getMessage();
            }
        }
        return null;
    }
}
