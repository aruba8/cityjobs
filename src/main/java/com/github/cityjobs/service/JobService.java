package com.github.cityjobs.service;

import com.github.cityjobs.model.Job;

import java.util.Collection;
import java.util.List;

public interface JobService {
    List<Job> saveJobs(List<Job> jobs);
    List<Job> findAllJobsByIsActive(boolean isActive);
    Job findJobByJobIdAndRunId(Integer jobId, Long runId);

}
