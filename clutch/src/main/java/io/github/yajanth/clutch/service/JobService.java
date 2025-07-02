package io.github.yajanth.clutch.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import io.github.yajanth.clutch.entity.Job;
import io.github.yajanth.clutch.enums.JobStatus;
import io.github.yajanth.clutch.repository.JobRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class JobService {
	
	private final JobRepository jobRepo;
	
	public JobService(JobRepository clutchRepos) {
		this.jobRepo = clutchRepos;
	}
	
	public Job createJob(Job job) {
		return jobRepo.save(job);
	}
	
	public List<Job> getAllJobs(){
		return jobRepo.findAll();
	}
	@Transactional
	public JobStatus updateJobStatus(UUID id, JobStatus status) {
	    int updated = jobRepo.updateStatus(id, status);
	    if (updated == 0) {
	        throw new EntityNotFoundException("Job not found.");
	    }
	    return status;
	}


	

}
