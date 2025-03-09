package com.devops.joblink.company;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService{
    @Override
    public List<Company> findAll() {
        return List.of();
    }

    @Override
    public Company findCompanyById(Long id) {
        return null;
    }

    @Override
    public void createCompany(Company company) {

    }

    @Override
    public boolean updateCompany(Long id, Company company) {
        return false;
    }

    @Override
    public boolean deleteCompany(Long id) {
        return false;
    }
}
