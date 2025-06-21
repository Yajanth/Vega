package com.cicd.clutch.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cicd.clutch.entity.Job;
import com.cicd.clutch.enums.JobStatus;

@Repository
public interface ClutchRepository extends JpaRepository<Job, UUID> {
	
	List<Job> findAll();
	
	@Modifying
	@Query("UPDATE Job j SET j.jobStatus = :status WHERE j.jobId = :id")
	int updateStatus(@Param("id") UUID id, @Param("status") JobStatus status);
	


}
