package com.github.cityjobs.service;

import com.github.cityjobs.model.Job;
import com.github.cityjobs.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;

    @Autowired
    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public void saveJobs(List<Job> jobs) {
        jobs.forEach(jobRepository::save);
    }

}
