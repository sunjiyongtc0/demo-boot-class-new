package com.sunjy.secret.boot.Job;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Quartz有四个核心概念:
 * Job：是一个接口，只定义一个方法 execute（JobExecutionContext context），在实现接口的 execute 方法中编写所需要定时执行的 Job（任务），JobExecutionContext 类提供了调度应用的一些信息；Job 运行时的信息保存在 JobDataMap 实例中。
 * JobDetail：Quartz 每次调度 Job 时，都重新创建一个 Job 实例，因此它不接受一个 Job 的实例，相反它接收一个 Job 实现类（JobDetail，描述 Job 的实现类及其他相关的静态信息，如 Job 名字、描述、关联监听器等信息），以便运行时通过 newInstance() 的反射机制实例化 Job。
 * rigger：是一个类，描述触发 Job 执行的时间触发规则，主要有 SimpleTrigger 和 CronTrigger 这两个子类。当且仅当需调度一次或者以固定时间间隔周期执行调度，SimpleTrigger 是最适合的选择；而 CronTrigger 则可以通过 Cron 表达式定义出各种复杂时间规则的调度方案：如工作日周一到周五的 15：00 ~ 16：00 执行调度等。
 * Scheduler：调度器就相当于一个容器，装载着任务和触发器，该类是一个接口，代表一个 Quartz 的独立运行容器，Trigger 和 JobDetail 可以注册到 Scheduler 中，两者在 Scheduler 中拥有各自的组及名称，组及名称是 Scheduler 查找定位容器中某一对象的依据，Trigger 的组及名称必须唯一，JobDetail 的组和名称也必须唯一（但可以和 Trigger 的组和名称相同，因为它们是不同类型的）。Scheduler 定义了多个接口方法，允许外部通过组及名称访问和控制容器中 Trigger 和 JobDetail。
 * quartz
 *
 * Job 为作业的接口，为任务调度的对象；JobDetail 用来描述 Job 的实现类及其他相关的静态信息；Trigger 做为作业的定时管理工具，一个 Trigger 只能对应一个作业实例，而一个作业实例可对应多个触发器；Scheduler 做为定时任务容器，是 Quartz 最上层的东西，它提携了所有触发器和作业，使它们协调工作，每个 Scheduler 都存有 JobDetail 和 Trigger 的注册，一个 Scheduler 中可以注册多个 JobDetail 和多个 Trigger
 * */
@Configuration
@EnableScheduling
@Component
public class SchedulerListener {

    @Autowired
    public CronSchedulerJob scheduleJobs;

    @Scheduled(cron="0 38 16 * * ?")
    public void schedule() throws SchedulerException {
//        scheduleJobs.scheduleJobs();
        System.out.println(">>>>>>>>>>>>>>>定时任务开始执行<<<<<<<<<<<<<");
    }

}
