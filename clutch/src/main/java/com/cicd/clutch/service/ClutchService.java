package com.cicd.clutch.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cicd.clutch.entity.Job;
import com.cicd.clutch.enums.JobStatus;
import com.cicd.clutch.repository.ClutchRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class ClutchService {
	
	private final ClutchRepository clutchRepo;
	
	public ClutchService(ClutchRepository clutchRepos) {
		this.clutchRepo=clutchRepos;
	}
	
	public Job createJob(Job job) {
		return clutchRepo.save(job);
	}
	
	public List<Job> getAllJobs(){
		return clutchRepo.findAll();
	}
	@Transactional
	public JobStatus updateClutchStatus(UUID id, JobStatus status) {
	    int updated = clutchRepo.updateStatus(id, status);
	    if (updated == 0) {
	        throw new EntityNotFoundException("Job not found.");
	    }
	    return status;
	}


	

}
