package com.mrcoder.sbschedule.job;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   //2.开启定时任务
public class StaticSchedule {
    //3.添加定时任务

    //基于注解@Scheduled默认为单线程
    //开启多个任务时，任务的执行时机会受上一个任务执行时间的影响。
    //这边定时任务1延时10s来演示这个单线程的问题
    //@Scheduled(fixedRate = 5000)
    @Scheduled(cron = "0/5 * * * * ?", zone = "")
    private void configureTasks() {
        System.err.println("执行静态定时任务(单线程)1: " + LocalDateTime.now().toLocalTime());

        try {
            Thread.sleep(1000 * 10);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            System.err.println(ex);
        }
    }

    @Scheduled(cron = "0/5 * * * * ?")
    private void configureTasks2() {
        System.err.println("执行静态定时任务(单线程)2: " + LocalDateTime.now().toLocalTime());
    }
}
