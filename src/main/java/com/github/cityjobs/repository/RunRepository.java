package com.github.cityjobs.repository;

import com.github.cityjobs.model.Run;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface RunRepository extends Repository<Run, Long> {
    Run save(Run run);

    List<Run> findAllByIsProcessedOrderByIdAsc(Boolean isProcessed);
}
