package com.kaviddiss.bootquartz;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;

import com.kaviddiss.bootquartz.job.SampleJob;

@SpringApplicationConfiguration(classes = Application.class)
public class ApplicationTest extends AbstractTransactionalTestNGSpringContextTests {
	@Autowired
	private Scheduler scheduler;

	@Test
	public void test() throws Exception {

		JobDetail jobDetail = JobBuilder.newJob(SampleJob.class)
				.storeDurably(true)
				.build();

		Trigger trigger = TriggerBuilder.newTrigger()
				.forJob(jobDetail)
				.startNow()
				.build();

		scheduler.scheduleJob(jobDetail, trigger);

		Thread.sleep(5000);
	}
}
