package com.afei.shceduletask.config;

import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class JobManager {

    @Resource
    private StdSchedulerFactory quartzSchedule;



}
