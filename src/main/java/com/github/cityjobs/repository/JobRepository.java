package com.github.cityjobs.repository;

import com.github.cityjobs.model.Job;
import org.springframework.data.repository.CrudRepository;

public interface JobRepository extends CrudRepository<Job, Long> {

}
