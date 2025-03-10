package com.devops.joblink.review;

import com.devops.joblink.company.Company;
import jakarta.persistence.Entity;

import java.util.Date;


public class Review {
    private Long id;
    private double rating;
    private String userEmail;
    private String comment;
    private Date reviewDate;
    private String company;



}
