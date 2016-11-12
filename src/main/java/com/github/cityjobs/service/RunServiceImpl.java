package com.github.cityjobs.service;

import com.github.cityjobs.model.Run;
import com.github.cityjobs.repository.RunRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RunServiceImpl implements RunService{

    private final RunRepository runRepository;

    @Autowired
    public RunServiceImpl(RunRepository runRepository) {
        this.runRepository = runRepository;
    }

    @Override
    public void saveRun(Run run) {
        runRepository.save(run);
    }
}
