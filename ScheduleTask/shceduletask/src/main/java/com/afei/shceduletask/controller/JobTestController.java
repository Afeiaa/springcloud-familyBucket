package com.afei.shceduletask.controller;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobTestController {


    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;


    @GetMapping("/firstJob")
    public String test1() {
        try {
            schedulerFactoryBean.start();
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("xx");
        }
    }

}
