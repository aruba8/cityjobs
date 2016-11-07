package com.github.cityjobs.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Job implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "job_name", nullable = false)
    private String jobName;

    @Column(name = "department_name",nullable = true)
    private String departmentName;

    @Column(name = "location", nullable = true)
    private String location;

    @Column(name = "date_posted", nullable = false)
    private Date datePosted;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @Column(nullable = true, name = "job_id")
    private int jobId;

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

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(Date datePosted) {
        this.datePosted = datePosted;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }
}
