package com.mrcoder.sbexceptionvalidator.common.status;

import com.mrcoder.sbexceptionvalidator.common.model.ResponseInfo;

/**
 * @Description: 系统错误类型枚举类
 */
public enum ErrorCodeEnum {

    SYSTEM_ERROR(ResponseInfo.FAIL_CODE, "系统异常，请稍后重试"),

    NO_AUTHORITY(ResponseInfo.FAIL_CODE, "无权访问"),

    PARAM_ERROR(ResponseInfo.FAIL_CODE, "参数非法"),

    NOT_LOG(ResponseInfo.FAIL_CODE, "当前用户未登录");


    private Integer errCode;

    private String errMsg;

    private ErrorCodeEnum(Integer errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public Integer getErrCode() {
        return errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

}
