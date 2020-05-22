package com.trainingmanagesys.conf.resultCode;

import lombok.Getter;

/**
 * @author RudeCrab
 * @description 响应码枚举
 */
@Getter
public enum ResultCode {

    SUCCESS(1000, "操作成功"),

    FAILED(1001, "响应失败"),

    VALIDATE_FAILED(1002, "参数校验失败"),

    Login_FAILED(1003, "登录失败"),

    //NOTFOUND_FAILED(1004, "请求目标不存在"),

    ERROR(5000, "未知错误");

    private int code;
    private String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
