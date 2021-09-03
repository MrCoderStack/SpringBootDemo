package com.mrcoder.sbspelannotations.controller;

import com.mrcoder.sbspelannotations.aop.annotation.SpelGetParm;
import com.mrcoder.sbspelannotations.model.R;
import com.mrcoder.sbspelannotations.model.User;
import org.springframework.web.bind.annotation.*;


/**
 * @Description: TODO
 */
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping("/param")
    @SpelGetParm(parm = "#user.name")
    public R repeat(@RequestBody User user) {
        return R.success(user);
    }
}
