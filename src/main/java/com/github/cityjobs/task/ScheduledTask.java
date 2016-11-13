package com.github.cityjobs.task;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.github.cityjobs.model.Job;
import com.github.cityjobs.model.Run;
import com.github.cityjobs.service.JobService;
import com.github.cityjobs.service.Requester;
import com.github.cityjobs.service.RunService;
import com.github.cityjobs.util.JobParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Component
public class ScheduledTask {


    private static final Logger log = LoggerFactory.getLogger(ScheduledTask.class);

    private final Requester requester;

    private final JobService jobService;

    private final JobParser jobParser;

    private final RunService runService;

    @Autowired
    public ScheduledTask(Requester requester, JobService jobService, JobParser jobParser, RunService runService) {
        this.requester = requester;
        this.jobService = jobService;
        this.jobParser = jobParser;
        this.runService = runService;
    }

    @Value("${spring.startpage}")
    private String startPage;


    @Scheduled(fixedDelayString = "${spring.schedule.rate}")
    public void gatheringJobs() {
        Run run = new Run();
        run.setTimeStarted(new Date());
        run.setProcessed(false);
        run = runService.saveRun(run);
        try {
            List<HtmlElement> jobBlocks = requester.getJobBlocks(startPage);
            List<Job> jobs = jobParser.getJobsFromBlock(jobBlocks);
            for (Job job : jobs) {
                job.setRun(run);
            }
            jobService.saveJobs(jobs);

        } catch (Exception e) {
            run.setComment(e.getMessage());
        } finally {
            run.setTimeFinished(new Date());
            runService.saveRun(run);
        }
    }

    @Scheduled(fixedDelayString = "${spring.schedule.ratejobs}")
    public void processJobs() {
        List<Run> runs = runService.findAllByIsProcessedOrderByIdAsc(false);
        if (runs.size()>1){
            runs.forEach(a-> System.out.println(a.getId()));
        } else {
            Run run = runs.get(0);
        }
    }
}
