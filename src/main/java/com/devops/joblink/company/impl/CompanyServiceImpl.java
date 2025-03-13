package com.devops.joblink.company.impl;

import com.devops.joblink.company.Company;
import com.devops.joblink.company.CompanyRepository;
import com.devops.joblink.company.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
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
        if (companyOptional.isPresent()) {
            companyRepository.deleteById(companyOptional.get().getId());
            return true;
        }
        return false;
    }
}
