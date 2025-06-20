package com.cicd.clutch.service;

import org.springframework.stereotype.Service;

import com.cicd.clutch.entity.Job;
import com.cicd.clutch.repository.ClutchRepository;

@Service
public class ClutchService {
	
	
	private final ClutchRepository clutchRepo;
	
	public ClutchService(ClutchRepository clutchRepos) {
		this.clutchRepo=clutchRepos;
	}
	
	public Job createJob(Job job) {
		return clutchRepo.save(job);
	}

}
