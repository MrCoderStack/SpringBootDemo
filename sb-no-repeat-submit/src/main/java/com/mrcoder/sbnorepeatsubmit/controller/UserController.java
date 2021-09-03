package com.mrcoder.sbnorepeatsubmit.controller;

import com.mrcoder.sbnorepeatsubmit.aop.annotation.NoRepeatSubmit;
import com.mrcoder.sbnorepeatsubmit.model.R;
import org.springframework.web.bind.annotation.*;


/**
 * @Description: TODO
 */
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping("/repeat")
    @NoRepeatSubmit(lockKey = "testKey")
    public R repeat() {
        try {
            Thread.sleep(60 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return R.success();
    }
}
