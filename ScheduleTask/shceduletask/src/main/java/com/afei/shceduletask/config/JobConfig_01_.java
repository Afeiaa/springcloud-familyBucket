package com.afei.shceduletask.config;



import com.afei.shceduletask.job.jobTest_01_;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// @Configuration
public class JobConfig_01_ {

    @Bean
    public JobDetail jobDetail_01_() {
        return JobBuilder.newJob(jobTest_01_.class)
                .withIdentity("_01_Job", "_01_JobGroup")
                .storeDurably(true)
                .build();
    }

    @Bean
    public Trigger trigger_01_() {
        return TriggerBuilder.newTrigger()
                .withIdentity("_01_Trigger", "_01_TriggerGroup")
                .startNow()
                .forJob("_01_Job", "_01_JobGroup")
                .usingJobData("myFirstJob", "111")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).withRepeatCount(10))
                .build();
    }

}
