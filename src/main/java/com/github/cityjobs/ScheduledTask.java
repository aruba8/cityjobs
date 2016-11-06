package com.github.cityjobs;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.github.cityjobs.service.Requester;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class ScheduledTask {


    private static final Logger log = LoggerFactory.getLogger(ScheduledTask.class);

    private final Requester requester;

    @Autowired
    public ScheduledTask(Requester requester) {
        this.requester = requester;
    }


    @Scheduled(fixedDelayString = "${spring.schedule.rate}")
    public void runTask() {
        List<HtmlElement> page = requester.getJobLinks("http://www.winnipeg.ca/hr/");
    }
}
