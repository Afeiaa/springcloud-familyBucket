package com.afei.shceduletask.job;


import com.afei.shceduletask.service.HelloService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class jobTest_01_ extends QuartzJobBean {
    @Autowired
    HelloService helloService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println(helloService.toString("定时任务执行中....."));
    }
}
