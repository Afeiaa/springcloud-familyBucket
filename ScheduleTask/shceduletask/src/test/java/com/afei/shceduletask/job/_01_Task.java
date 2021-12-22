package com.afei.shceduletask.job;

import com.afei.shceduletask.util.DFUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

public class _01_Task implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("ScheduleTask execute: " + DFUtil.format(new Date()) + Thread.currentThread().getName().toString());
    }
}
