package com.zh.community;

import com.zh.community.util.SensitiveFilter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author :珠代
 * @description :
 * @create :2022-04-07 20:59:00
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class QuartzTests {

    @Autowired
    private Scheduler scheduler;

    @Test
    public void testDeleteJob(){

        try {
            boolean deleteJob = scheduler.deleteJob(new JobKey("alphaJob", "alphaJobGroup"));
            System.out.println(deleteJob);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
