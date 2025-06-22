package io.github.yajanth.clutch.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.yajanth.clutch.entity.Job;
import io.github.yajanth.clutch.enums.JobStatus;
import io.github.yajanth.clutch.queue.RedisQueuePublisher;
import io.github.yajanth.clutch.service.ClutchService;

@RestController
@RequestMapping("/v1")
public class ClutchController {
	
	private final ClutchService clutchservice;
	private final RedisQueuePublisher redisQueuePublisher;

	public ClutchController(ClutchService clutchservice , RedisQueuePublisher redisQueuePublisher) {
		this.clutchservice = clutchservice;
		this.redisQueuePublisher= redisQueuePublisher; 
	}
	
	@PostMapping("/jobs")
	public ResponseEntity<Job> addJob(@RequestBody Job job){
	    try {
	        clutchservice.createJob(job);
	        String message = job.getJobId().toString();
	        redisQueuePublisher.publish(message);
	        return ResponseEntity.ok(job);
	    } catch (Exception e) {
	        // log error, return meaningful error response
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    }
		
	}
	
	@PatchMapping("jobs/{id}/{status}")
	public ResponseEntity<UUID> updateStatus(@PathVariable UUID id,@PathVariable JobStatus status) {
		 JobStatus status1=clutchservice.updateClutchStatus(id, status);
		return new ResponseEntity<>(id,HttpStatus.OK);
		
	}
	
	@GetMapping("/jobs")
	public List<Job> allJobs(){
		List<Job> jobsList = clutchservice.getAllJobs();
		return jobsList;
	}
	
	@GetMapping("/check")
	public String testPoint() {
		return "---Clutch Jobs is running!!!---";
	}
	
	
	

}
