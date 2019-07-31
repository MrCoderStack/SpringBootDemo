package com.mrcoder.sbschedule.job;

import org.springframework.scheduling.annotation.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@EnableScheduling //1.开启定时任务
@EnableAsync      //2.开启多线程，开启后多个定时任务不会互相影响

public class ThreadSchedule {
    @Async
    @Scheduled(fixedDelay = 1000) //间隔1秒
    public void first() {
        System.err.println("执行定时任务(多线程)1 : " + LocalDateTime.now().toLocalTime() + "线程 : " + Thread.currentThread().getName());
        try {
            Thread.sleep(1000 * 10);
        } catch (InterruptedException ex) {

            Thread.currentThread().interrupt();
            System.err.println(ex);
        }
    }

    @Async
    @Scheduled(fixedDelay = 2000)
    public void second() {
        System.err.println("执行定时任务(多线程)2 : " + LocalDateTime.now().toLocalTime() + "线程 : " + Thread.currentThread().getName());
    }
}