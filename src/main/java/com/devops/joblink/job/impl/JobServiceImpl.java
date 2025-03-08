package com.devops.joblink.job.impl;

import com.devops.joblink.job.Job;
import com.devops.joblink.job.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobServiceImpl implements JobService {
    private List<Job> jobs = new ArrayList<>();
    private Long nextId = 1L;

    @Override
    public List<Job> findAll() {
        return jobs;
    }

    @Override
    public Job findJobById(Long id) {
        return jobs
                .stream()
                .filter(job -> job.getId().equals(id))
                .findFirst().orElse(null);
    }


    @Override
    public void createJob(Job job) {
        job.setId(nextId++);
        jobs.add(job);
    }

    @Override
    public void deleteJob(Long id) {
        jobs.removeIf(job -> job.getId().equals(id));
    }

    @Override
    public boolean update(Long id, Job job) {


        for (Job i_job : jobs) {
            if (i_job.getId().equals(id)) {
                i_job.setTitle(job.getTitle());
                i_job.setDescription(job.getDescription());
                i_job.setMinSalary(job.getMinSalary());
                i_job.setMaxSalary(job.getMaxSalary());
                i_job.setLocation(job.getLocation());
                return true;
            }
        }

        return false;
    }
}
