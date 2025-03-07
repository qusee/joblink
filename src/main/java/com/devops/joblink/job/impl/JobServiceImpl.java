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
        Job updatedJob = findJobById(id);
        if(updatedJob != null){
            jobs.remove(updatedJob); // remove the old job
            job.setId(id); // set the new job's ID to this one
            jobs.add(job);
            return true;
        }
        return false;
    }
}
