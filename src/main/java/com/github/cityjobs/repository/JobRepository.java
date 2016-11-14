package com.github.cityjobs.repository;

import com.github.cityjobs.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {

    List<Job> findAllJobsByIsActive(boolean isActive);

    Job findJobByJobIdAndRunId(Integer jobId, Long runId);
}
