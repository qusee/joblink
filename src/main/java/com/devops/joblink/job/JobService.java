package com.devops.joblink.job;

import org.springframework.stereotype.Service;

import java.util.List;


public interface JobService {
    List<Job> findAll();
    Job findJobById(Long id);
    void createJob(Job job);
    boolean deleteJob(Long id);
    boolean update(Long id, Job job);
}
