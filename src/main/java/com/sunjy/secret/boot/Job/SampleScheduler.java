package com.sunjy.secret.boot.Job;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 *
 * JobBuilder 无构造函数，只能通过 JobBuilder 的静态方法 newJob(Class jobClass)生成 JobBuilder 实例。
 * withIdentity 方法可以传入两个参数 withIdentity(String name,String group) 来定义 TriggerKey，
 * 也可以不设置，像上文示例中会自动生成一个独一无二的 TriggerKey 用来区分不同的 Trigger。
 *
 *
 *
 * */

@Configuration
public class SampleScheduler {

    @Bean
    public JobDetail sampleJobDetail() {
        // 链式编程,可以携带多个参数,在Job类中声明属性 + setter方法
        return JobBuilder.newJob(SampleJob.class).withIdentity("sampleJob")
                .usingJobData("name","World").storeDurably().build();
    }
/**
 *
 * 关闭执行语句
 * */
//    @Bean
//    public Trigger sampleJobTrigger(){
//        // 每隔两秒执行一次
//        SimpleScheduleBuilder scheduleBuilder =
//                SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).repeatForever();
//        return TriggerBuilder.newTrigger().forJob(sampleJobDetail()).withIdentity("sampleTrigger")
//                .withSchedule(scheduleBuilder).build();
//    }

}
