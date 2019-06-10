package com.mrcoder.sbexceptionvalidator.common.exception;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import com.mrcoder.sbexceptionvalidator.common.model.ResponseInfo;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @Description: 全局异常处理器
 */
@ControllerAdvice
public class GlobalExpectionHandler {

    /**
     * 异常捕获处理
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseInfo expectionHandler(HttpServletRequest request, Exception e) {

        if (e instanceof MethodArgumentNotValidException) { // JavaBean参数校验异常

            MethodArgumentNotValidException exception = (MethodArgumentNotValidException) e;
            List<ObjectError> allErrors = exception.getBindingResult().getAllErrors(); // 取出错误信息
            ObjectError error = allErrors.get(0); // 只返回第一个错误信息即可
            return ResponseInfo.fail(error.getDefaultMessage());

        } else if (e instanceof ConstraintViolationException) { // Controller方法参数校验异常

            // 错误异常
            String message = ((ConstraintViolationException) e).getConstraintViolations().iterator().next().getMessage();
            return ResponseInfo.fail(message);

        } else if (e instanceof BusinessException) {

            // 自定义业务异常
            BusinessException exception = (BusinessException) e;
            return ResponseInfo.fail(exception.getErrMsg());

        } else {
            // 系统异常，打印错误日志
            e.printStackTrace();
            return ResponseInfo.fail("系统异常，请稍后重试");

        }
    }
}