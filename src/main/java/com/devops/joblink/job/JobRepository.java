package com.devops.joblink.job;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {

    List<Job> findJobsByCompany_Id(Long companyId);
}
