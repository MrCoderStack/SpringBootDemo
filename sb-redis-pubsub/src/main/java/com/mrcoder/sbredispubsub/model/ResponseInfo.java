package com.mrcoder.sbredispubsub.model;


import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Description: 接口返回报文数据封装bean
 */
@Getter
@Setter
@NoArgsConstructor
@JsonSerialize
@JsonInclude(Include.NON_NULL)
public class ResponseInfo implements Serializable {

    private static final long serialVersionUID = -2210269174549937804L;

    // 成功状态码
    public static final Integer SUCCESS_C0DE = Integer.valueOf(0);

    // 失败状态码
    public static final Integer FAIL_CODE = Integer.valueOf(-1);

    // 接口状态码
    private Integer code;

    // 提示消息
    private String message;

    // 接口业务数据
    private Object result;

    public ResponseInfo(Integer code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public ResponseInfo(Integer code, Object result) {
        super();
        this.code = code;
        this.result = result;
    }

    public ResponseInfo(Integer code) {
        super();
        this.code = code;
    }

    public static ResponseInfo success(Object result) {
        return new ResponseInfo(ResponseInfo.SUCCESS_C0DE, result);
    }

    public static ResponseInfo success() {
        return new ResponseInfo(ResponseInfo.SUCCESS_C0DE);
    }

    public static ResponseInfo fail(String errorMsg) {
        return new ResponseInfo(ResponseInfo.FAIL_CODE, errorMsg);
    }


    public static ResponseInfo fail(Integer errCode) {
        if (errCode == SUCCESS_C0DE) {
            return new ResponseInfo(FAIL_CODE);
        }
        return new ResponseInfo(errCode);
    }

    public static ResponseInfo fail(Integer errCode, String errMessage) {
        return new ResponseInfo(errCode, errMessage);
    }
}