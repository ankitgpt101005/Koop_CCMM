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
public class AgentQueuedJobTrigger {

	@Bean(name = "AgentQueuedJob")
	public JobDetailFactoryBean agentQueuedJobDetail() {
		JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();
		jobDetailFactory.setJobClass(AgentQueuedJob.class);
		jobDetailFactory.setName("AgentQueuedJob");
		jobDetailFactory.setDurability(true);
		return jobDetailFactory;
	}

	@Bean
	public SimpleTriggerFactoryBean agentQueuedTrigger(@Qualifier("AgentQueuedJob") JobDetail job) {
		SimpleTriggerFactoryBean trigger = new SimpleTriggerFactoryBean();
		trigger.setJobDetail(job);
		trigger.setRepeatInterval(REPEAT_INTERVAL);
		trigger.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
		trigger.setStartTime(new Date());
		trigger.setName("JobTrigger1");
		return trigger;
	}

}
