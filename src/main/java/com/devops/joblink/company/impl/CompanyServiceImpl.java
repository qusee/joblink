package com.devops.joblink.company.impl;

import com.devops.joblink.company.Company;
import com.devops.joblink.company.CompanyRepository;
import com.devops.joblink.company.CompanyService;


import com.devops.joblink.job.JobService;


import com.devops.joblink.review.ReviewService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final JobService jobService;
    private final ReviewService reviewService;

    public CompanyServiceImpl(CompanyRepository companyRepository, JobService jobService, ReviewService reviewService) {
        this.companyRepository = companyRepository;
        this.jobService = jobService;
        this.reviewService = reviewService;
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

        if (companyOptional.isPresent()) { // companyRepository.existsById(id);
            jobService.deleteJobsByCompanyId(id);
            reviewService.dropReviewsByCompanyId(id);
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
