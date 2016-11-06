package com.github.cityjobs.service;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class RequesterImpl implements Requester {


    @Override
    public List<HtmlElement> getJobLinks(String url) {
        List<HtmlElement> jobLinks = null;
        try {

            final WebClient webClient = new WebClient();
            HtmlPage page = webClient.getPage(url);
            List<?> list = page.getByXPath("//a[text()='View Current Job Postings']");
            HtmlElement a = (HtmlElement) list.get(0);
            HtmlPage jobPage = a.click();
            jobLinks = (List<HtmlElement>) jobPage.getBody().getByXPath("//a[contains(@id, 'POSTINGLINK')]");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return jobLinks;
    }
}
