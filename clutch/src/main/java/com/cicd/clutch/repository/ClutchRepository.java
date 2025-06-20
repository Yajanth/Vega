package com.cicd.clutch.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cicd.clutch.entity.Job;

@Repository
public interface ClutchRepository extends JpaRepository<Job, UUID> {
	
	List<Job> findAll();

}
