package com.github.cityjobs.service;


import com.github.cityjobs.model.Run;

import java.util.List;

public interface RunService {
    Run saveRun(Run run);

    List<Run> findAllByIsProcessedOrderByIdAsc(boolean isProcessed);
}
