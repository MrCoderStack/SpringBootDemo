package com.mrcoder.sbspelannotations.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Description: 接口返回报文数据封装bean
 */
@Getter
@Setter
@NoArgsConstructor
@JsonSerialize
@JsonInclude(Include.NON_NULL)
public class R implements Serializable {

    private static final long serialVersionUID = -8403031499088786413L;

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

    public R(Integer code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public R(Integer code, Object result) {
        super();
        this.code = code;
        this.result = result;
    }

    public R(Integer code) {
        super();
        this.code = code;
    }

    public static R success(Object result) {
        return new R(R.SUCCESS_C0DE, result);
    }

    public static R success() {
        return new R(R.SUCCESS_C0DE);
    }

    public static R fail(String errorMsg) {
        return new R(R.FAIL_CODE, errorMsg);
    }


    public static R fail(Integer errCode) {
        if (errCode == SUCCESS_C0DE) {
            return new R(FAIL_CODE);
        }
        return new R(errCode);
    }

    public static R fail(Integer errCode, String errMessage) {
        return new R(errCode, errMessage);
    }
}
