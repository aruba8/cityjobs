package com.github.cityjobs.service;

import com.gargoylesoftware.htmlunit.html.HtmlElement;

import java.util.List;

public interface Requester {
    List<HtmlElement> getJobLinks(String url);
}
