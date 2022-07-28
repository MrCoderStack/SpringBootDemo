package com.mrcoder.sbdistributedlockannotations.controller;

import com.mrcoder.sbdistributedlockannotations.annotation.DistributedLock;
import com.mrcoder.sbdistributedlockannotations.model.R;
import com.mrcoder.sbdistributedlockannotations.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: 分布式锁自定义注解使用案例
 */
@Slf4j
@RestController
public class DistributedLockController {


    /**
     * 测试二：lockKey动态值【SpEL表达式】 + 常量
     */
    @DistributedLock(lockKey = "'userId-' + #userId", expireTime = 100)
    @GetMapping("/lock2/{userId}")
    public R lock2(@PathVariable("userId") Long userId) {
        for (int i = 0; i < 1000000; i++) {
            log.info("waiting......");
        }
        return R.success();
    }


    /**
     * 测试二：lockKey动态值【SpEL表达式】 + 常量
     */
    @DistributedLock(lockKey = "'userId-' + #user.id", expireTime = 100)
    @PostMapping("/lock2")
    public R lock2(@RequestBody User user) {
        for (int i = 0; i < 1000000; i++) {
            log.info("waiting......");
        }
        return R.success();
    }
}
