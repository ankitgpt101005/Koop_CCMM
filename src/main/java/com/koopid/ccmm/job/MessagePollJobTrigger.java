package com.koopid.ccmm.job;

import static com.koopid.ccmm.utility.Constants.REPEAT_INTERVAL;

import java.util.Date;

import org.quartz.JobDetail;
import org.quartz.SimpleTrigger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

@Configuration
public class MessagePollJobTrigger {

	@Bean(name = "MessagePollJob")
	public JobDetailFactoryBean messagePollJobDetail() {
		JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();
		jobDetailFactory.setJobClass(MessagePollJob.class);
		jobDetailFactory.setName("MessagePollJob");
		jobDetailFactory.setDurability(true);
		return jobDetailFactory;
	}

	@Bean
	public SimpleTriggerFactoryBean messagePollTrigger(@Qualifier("MessagePollJob") JobDetail job) {
		SimpleTriggerFactoryBean trigger = new SimpleTriggerFactoryBean();
		trigger.setJobDetail(job);
		trigger.setRepeatInterval(REPEAT_INTERVAL);
		trigger.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
		trigger.setStartTime(new Date());
		trigger.setName("JobTrigger2");
		return trigger;
	}

}
