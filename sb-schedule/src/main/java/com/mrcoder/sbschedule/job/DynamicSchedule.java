package com.mrcoder.sbschedule.job;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.config.TriggerTask;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;


@Component
//1.主要用于标记配置类，兼备Component的效果。
@Configuration
//2.开启定时任务
@EnableScheduling
public class DynamicSchedule implements SchedulingConfigurer {
    @Mapper
    public interface CronMapper {
        @Select("select cron from crontab limit 1")
        public String getCron();
    }

    //注入mapper
    @Autowired
    @SuppressWarnings("all")
    CronMapper cronMapper;

    /**
     * 执行定时任务.
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {

        //多线程支持，线程池创建10个线程
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(10);
        taskScheduler.initialize();
        taskRegistrar.setTaskScheduler(taskScheduler);

        //定时任务1: 每1s执行一次（每执行一次睡眠10s）
        taskRegistrar.addFixedRateTask(
                new Runnable() {
                    //添加任务内容
                    @Override
                    public void run() {
                        System.err.println("执行动态定时任务1: " + LocalDateTime.now().toLocalTime());
                        //为了验证多线程不阻塞，这边睡眠 10s
                        try {
                            Thread.sleep(1000 * 10);
                        } catch (InterruptedException ex) {
                            Thread.currentThread().interrupt();
                            System.err.println(ex);
                        }
                    }
                }
                , 1000);

        //定时任务2: 读取mysql中的执行cron公式
        TriggerTask triggrtTask = new TriggerTask(
                //1.添加任务内容(Runnable),这边使用"拉姆达表达式"
                () -> System.err.println("执行动态定时任务2: " + LocalDateTime.now().toLocalTime()),
                //2.设置执行周期(Trigger)
                triggerContext -> {
                    //2.1 从数据库获取执行周期
                    String cron = cronMapper.getCron();
                    //2.2 合法性校验.
                    if (StringUtils.isEmpty(cron)) {
                        // Omitted Code ..
                    }
                    //2.3 返回执行周期(Date)
                    return new CronTrigger(cron).nextExecutionTime(triggerContext);
                }
        );
        taskRegistrar.addTriggerTask(triggrtTask);
    }
}