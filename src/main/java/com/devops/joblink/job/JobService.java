package com.devops.joblink.job;

import org.springframework.stereotype.Service;

import java.util.List;


public interface JobService {
    public List<Job> findAll();
    public Job findJobById(Long id);
    void createJob(Job job);
    void deleteJob(Long id);

    boolean update(Long id, Job job);
}
