package com.devops.joblink.company;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CompanyService {
    List<Company> findAll();
    Company findCompanyById(Long id);
    void createCompany(Company company);
    boolean updateCompany(Long id, Company company);
    boolean deleteCompany(Long id);
}
