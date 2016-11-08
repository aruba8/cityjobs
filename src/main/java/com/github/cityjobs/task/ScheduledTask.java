package com.github.cityjobs.task;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.github.cityjobs.model.Job;
import com.github.cityjobs.service.JobService;
import com.github.cityjobs.service.Requester;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    @Value("${spring.startpage}")
    private String startPage;


    @Scheduled(fixedDelayString = "${spring.schedule.rate}")
    public void runTask() {
        List<HtmlElement> jobBlocks = requester.getJobBlocks(startPage);
        List<Job> jobList = new ArrayList<>();
        for (HtmlElement block : jobBlocks) {
            HtmlElement link = (HtmlElement) block.getByXPath(".//a").get(0);
            HtmlElement span = (HtmlElement) block.getByXPath(".//span").get(0);
            String spanText = span.getTextContent();
            String spanParts [] = spanText.split("\\|");
            String depPart = spanParts[0].trim();
            String locPart = spanParts[1].trim();
            String datePart = spanParts[2].trim();

            String depParts [] = depPart.split(":");
            String department = depParts[1].trim();
            String locParts [] = locPart.split(":");
            String location = locParts[1].trim();
            String dateParts [] = datePart.split(":");
            String datePosted = dateParts[1].trim();

            Job job = new Job();
            job.setJobName(link.getTextContent());
            job.setActive(true);
            job.setDatePosted(new Date());
            job.setJobId(jobService.getJobIdFromFullJobName(job.getJobName()));
            job.setLocation(location);
            job.setDepartmentName(department);
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            try {
                job.setDatePosted(sdf.parse(datePosted));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            jobList.add(job);
        }
            jobService.saveJobs(jobList);
    }
}
