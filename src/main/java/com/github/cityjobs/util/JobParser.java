package com.github.cityjobs.util;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.github.cityjobs.model.Job;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class JobParser {

    public List<Job> getJobsFromBlock(List<HtmlElement> jobBlocks) {
        List<Job> jobList = new ArrayList<>();
        for (HtmlElement block : jobBlocks) {
            jobList.add(getJobFromBlock(block));
        }
        return jobList;
    }

    public Job getJobFromBlock(HtmlElement block) {
        HtmlElement link = (HtmlElement) block.getByXPath(".//a").get(0);
        HtmlElement span = (HtmlElement) block.getByXPath(".//span").get(0);
        String spanText = span.getTextContent();
        String spanParts[] = spanText.split("\\|");
        String department = null;
        String location = null;
        String datePosted = null;
        for (String part : spanParts) {
            if (part.contains("Department")) {
                department = part.split(":")[1].trim();
            } else if (part.contains("Location")) {
                location = part.split(":")[1].trim();
            } else if (part.contains("Posted Date")) {
                datePosted = part.split(":")[1].trim();
            }
        }
        Job job = new Job();
        job.setJobName(link.getTextContent());
        job.setActive(true);
        job.setDatePosted(new Date());
        job.setJobId(getJobIdFromFullJobName(job.getJobName()));
        job.setLocation(location);
        job.setDepartmentName(department);
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        job.setDateAdded(new Date());
        try {
            job.setDatePosted(sdf.parse(datePosted));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return job;
    }


    private int getJobIdFromFullJobName(String fullJobName) {
        int nameLength = fullJobName.length();
        String idString = fullJobName.substring(nameLength - 6, nameLength);
        return Integer.valueOf(idString);
    }

}
