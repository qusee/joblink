package com.devops.joblink.company.impl;

import com.devops.joblink.company.Company;
import com.devops.joblink.company.CompanyRepository;
import com.devops.joblink.company.CompanyService;
import com.devops.joblink.job.Job;
import com.devops.joblink.job.JobRepository;
import com.devops.joblink.job.JobService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.jar.JarOutputStream;

@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final JobService jobService;

    public CompanyServiceImpl(CompanyRepository companyRepository, JobService jobService) {
        this.companyRepository = companyRepository;
        this.jobService = jobService;
    }


    @Override
    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    @Override
    public Company findCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public void createCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public boolean updateCompany(Long id, Company company) {
        Optional<Company> companyOptional = companyRepository.findById(id);
        if (companyOptional.isPresent()) {
            Company companyToUpdate = companyOptional.get();
            companyToUpdate.setName(company.getName());
            companyToUpdate.setIndustry(company.getIndustry());
            companyToUpdate.setDescription(company.getDescription());
            companyToUpdate.setLocation(company.getLocation());
            companyToUpdate.setWebsite(company.getWebsite());
            companyRepository.save(companyToUpdate);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteCompany(Long id) {
        Optional<Company> companyOptional = companyRepository.findById(id);
        // First I need to check if there are any jobs that are linked to this company
        if (companyOptional.isPresent()) { // companyRepository.existsById(id);

            Optional<List<Job>> optionalJobs = Optional.ofNullable(companyOptional.get().getJobs());
            if(optionalJobs.isPresent()){
                List<Job> jobs = optionalJobs.get();
                for (Job job : jobs) {
                    jobService.deleteJob(job.getId());
                }
            }
            companyRepository.deleteById(companyOptional.get().getId());
            return true;
        }
        return false;
    }

    @Override
    public boolean isCompanyPresent(Long id) {
        return companyRepository.existsById(id);
    }
}
