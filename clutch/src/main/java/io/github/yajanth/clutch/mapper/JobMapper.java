package io.github.yajanth.clutch.mapper;

import io.github.yajanth.clutch.entity.Job;
import io.github.yajanth.clutch.entity.JobRequest;

public class JobMapper {
	
	public static JobRequest toJobRequest(Job job) {
		JobRequest jobRequest = new JobRequest(job.getJobId(),
											job.getPayload(),
											job.getJobQueue());
		return jobRequest;
	}

}
