package com.zenith.demo.quartz;


import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzTest {

    public static void main(String[] args) {
        try {
            //����scheduler
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

            //����һ��Trigger
            Trigger trigger = newTrigger().withIdentity("trigger1", "group1") //����name/group
                .startNow()//һ������scheduler��������Ч
                .withSchedule(simpleSchedule() //ʹ��SimpleTrigger
                .withIntervalInSeconds(1) //ÿ��һ��ִ��һ��
                .repeatForever()) //һֱִ�У����ڵ��ϲ�ͣЪ
                .build();

            //����һ��JobDetail
            JobDetail job = newJob(HelloQuartz.class) //����Job��ΪHelloQuartz�࣬����������ִ���߼�����
                .withIdentity("job1", "group1") //����name/group
                .usingJobData("name", "quartz") //��������
                .build();

            //�����������
            scheduler.scheduleJob(job, trigger);

            //����֮
            scheduler.start();

            //����һ��ʱ���ر�
            //Thread.sleep(10000);
            //scheduler.shutdown(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}