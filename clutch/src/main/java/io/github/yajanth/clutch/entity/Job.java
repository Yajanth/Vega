package io.github.yajanth.clutch.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import io.github.yajanth.clutch.enums.JobStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@Table(name="jobs")
public class Job {

	@Id
	@Column(name="job_id",nullable = false, updatable = false)
	private UUID jobId;
	
	@Column(name="TASK_TYPE")
	private String task_type;
	
	@Column(name="PAYLOAD")
	private String payload;
	
	@Enumerated(EnumType.STRING)
	@Column(name="JOB_STATUS")
	private JobStatus jobStatus = JobStatus.PENDING ;
	
	@Column(name="CREATED_AT")
	private LocalDateTime createdAt;
	
	@Column(name="UPDATED_AT")
	private LocalDateTime updatedAt;
	
	@PrePersist
	public void prePersist() {
	if(this.jobId == null) {
		this.jobId = UUID.randomUUID();	
	}
    this.createdAt = LocalDateTime.now();
    this.updatedAt = this.createdAt;
	}
	
	@PreUpdate
	public void entityUpdate() {
	    this.updatedAt = LocalDateTime.now();
	    
	}
	
}
