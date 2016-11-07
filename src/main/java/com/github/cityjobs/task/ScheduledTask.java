package com.github.cityjobs.task;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.github.cityjobs.model.Job;
import com.github.cityjobs.service.JobService;
import com.github.cityjobs.service.Requester;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class ScheduledTask {


    private static final Logger log = LoggerFactory.getLogger(ScheduledTask.class);

    private final Requester requester;

    private final JobService jobService;

    @Autowired
    public ScheduledTask(Requester requester, JobService jobService) {
        this.requester = requester;
        this.jobService = jobService;
    }


    @Scheduled(fixedDelayString = "${spring.schedule.rate}")
    public void runTask() {
        List<HtmlElement> jobBlocks = requester.getJobBlocks("${spring.startpage}");
        for (HtmlElement block : jobBlocks) {
            HtmlElement link = (HtmlElement) block.getByXPath(".//a").get(0);
            Job job = new Job();
            job.setJobName(link.getTextContent());
            job.setActive(true);
            job.setDatePosted(new Date());
            job.setJobId(jobService.getJobIdFromFullJobName(job.getJobName()));
            System.out.println();
        }
    }
}
