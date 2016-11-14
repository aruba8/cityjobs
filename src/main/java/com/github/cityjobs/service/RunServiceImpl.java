package com.github.cityjobs.service;

import com.github.cityjobs.model.Run;
import com.github.cityjobs.repository.RunRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RunServiceImpl implements RunService {

    private final RunRepository runRepository;

    @Autowired
    public RunServiceImpl(RunRepository runRepository) {
        this.runRepository = runRepository;
    }

    @Override
    public Run saveRun(Run run) {
        return runRepository.save(run);
    }

    @Override
    public List<Run> findAllByIsProcessedOrderByIdAsc(boolean isProcessed) {
        return runRepository.findAllByIsProcessedOrderByIdAsc(isProcessed);
    }


}
