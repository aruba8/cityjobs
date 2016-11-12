package com.github.cityjobs.service;

import com.github.cityjobs.model.Job;

import java.util.List;

public interface JobService {
    void saveJobs(List<Job> jobs);

}
