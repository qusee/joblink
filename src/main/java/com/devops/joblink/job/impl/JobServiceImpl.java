package com.devops.joblink.job.impl;


import com.devops.joblink.job.Job;
import com.devops.joblink.job.JobRepository;
import com.devops.joblink.job.JobService;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {
    JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public Job findJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public List<Job> findJobsByCompanyId(Long companyId) {
        return jobRepository.findJobsByCompany_Id(companyId);
    }


    @Override
    public void createJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public boolean deleteJob(Long id) {
        Optional<Job> job = jobRepository.findById(id);
        if (job.isPresent()) {
            jobRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Long id, Job job) {

        Optional<Job> jobOptional = jobRepository.findById(id);
        if (jobOptional.isPresent()) {
            Job i_job = jobOptional.get();
            i_job.setTitle(job.getTitle());
            i_job.setDescription(job.getDescription());
            i_job.setMinSalary(job.getMinSalary());
            i_job.setMaxSalary(job.getMaxSalary());
            i_job.setLocation(job.getLocation());
            jobRepository.save(i_job);
            return true;
        }
        return false;
    }

    @Override
    public void deleteJobsByCompanyId(Long companyId) {
        List<Job> optionalJobs = findJobsByCompanyId(companyId);
        jobRepository.deleteAllById(optionalJobs.stream().map(Job::getId).collect(Collectors.toList()));
    }
}
