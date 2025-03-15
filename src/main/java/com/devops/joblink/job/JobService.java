package com.devops.joblink.job;

import java.util.List;


public interface JobService {
    List<Job> findAll();
    Job findJobById(Long id);
    List<Job> findJobsByCompanyId(Long companyId);
    void createJob(Job job);
    boolean deleteJob(Long id);
    boolean update(Long id, Job job);
    void deleteJobsByCompanyId(Long companyId);
}
