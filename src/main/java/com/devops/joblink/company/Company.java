package com.devops.joblink.company;


import com.devops.joblink.job.Job;
//import com.devops.joblink.review.Review;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @Column(nullable = false)
    private String name;
    private String industry;
    private String description;
    private String location;
    private String website;

    @OneToMany
    private List<Job> jobs;

//    @OneToMany
//    private List<Review> reviews;
//
    public Company(Long id, String name, String industry, String location, String description, String website) {
        this.id = id;
        this.name = name;
        this.industry = industry;
        this.location = location;
        this.description = description;
        this.website = website;
    }

    public Company() {}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }
}
