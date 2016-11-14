package com.github.cityjobs.service;

import com.github.cityjobs.model.Job;
import com.github.cityjobs.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;

    @Autowired
    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> saveJobs(List<Job> jobs) {
        return jobRepository.save(jobs);
    }

    @Override
    public List<Job> findAllJobsByIsActive(boolean isActive) {
        return jobRepository.findAllJobsByIsActive(isActive);
    }

    @Override
    public Job findJobByJobIdAndRunId(Integer jobId, Long runId) {
        return jobRepository.findJobByJobIdAndRunId(jobId, runId);
    }

}
