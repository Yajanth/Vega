package io.github.yajanth.clutch.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import io.github.yajanth.clutch.entity.Job;
import io.github.yajanth.clutch.enums.JobStatus;

@Repository
public interface JobRepository extends JpaRepository<Job, UUID> {
	
	List<Job> findAll();
	
	@Modifying
	@Query("UPDATE Job j SET j.jobStatus = :status WHERE j.jobId = :id")
	int updateStatus(@Param("id") UUID id, @Param("status") JobStatus status);
	


}
