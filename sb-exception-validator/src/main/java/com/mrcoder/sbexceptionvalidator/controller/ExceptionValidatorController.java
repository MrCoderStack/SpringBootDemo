package com.mrcoder.sbexceptionvalidator.controller;

import com.mrcoder.sbexceptionvalidator.common.model.ResponseInfo;
import com.mrcoder.sbexceptionvalidator.model.Person;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@RestController
public class ExceptionValidatorController implements ErrorController {

    @GetMapping("/success")
    public ResponseInfo success() {
        return ResponseInfo.success("success");
    }

    @GetMapping("/fail")
    public ResponseInfo fail() {
        return ResponseInfo.fail("error");
    }

    @PostMapping("/add")
    public ResponseInfo add(@Valid @RequestBody Person person) {
        return ResponseInfo.success("success");
    }



    @RequestMapping("/error")
    public ResponseInfo handleError(HttpServletRequest request) {
        //获取statusCode:401,404,500
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        return ResponseInfo.fail(statusCode);
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
