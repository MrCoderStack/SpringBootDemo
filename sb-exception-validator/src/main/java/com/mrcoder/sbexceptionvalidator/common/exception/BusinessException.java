package com.mrcoder.sbexceptionvalidator.common.exception;


import com.mrcoder.sbexceptionvalidator.common.model.ResponseInfo;
import com.mrcoder.sbexceptionvalidator.common.status.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Description: 全局业务异常
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = -6842004487143726249L;

    private Integer errCode;

    private String errMsg;

    public BusinessException(String errMsg) {
        super();
        this.errCode = ResponseInfo.FAIL_CODE;
        this.errMsg = errMsg;
    }

    public BusinessException(ErrorCodeEnum errorCodeEnum) {
        super();
        this.errCode = errorCodeEnum.getErrCode();
        this.errMsg = errorCodeEnum.getErrMsg();
    }

}