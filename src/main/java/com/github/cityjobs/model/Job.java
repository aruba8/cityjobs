package com.github.cityjobs.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Job implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String jobName;

    private String departmentName;

    private String location;

    private Date datePosted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }
}
