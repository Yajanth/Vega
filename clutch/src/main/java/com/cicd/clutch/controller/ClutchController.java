package com.cicd.clutch.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cicd.clutch.entity.Job;
import com.cicd.clutch.service.ClutchService;

@RestController
@RequestMapping("/v1")
public class ClutchController {
	
	private final ClutchService clutchservice;
	
	public ClutchController(ClutchService clutchservice) {
		this.clutchservice = clutchservice;
	}
	
	@PostMapping("/jobs")
	public ResponseEntity<Job> addJob(@RequestBody Job job){
		clutchservice.createJob(job);
		return ResponseEntity.ok(job);
		
	}
	
	@GetMapping("/check")
	public String testPoint() {
		return "---Clutch Jobs is running!!!---";
	}
	
	
	

}
