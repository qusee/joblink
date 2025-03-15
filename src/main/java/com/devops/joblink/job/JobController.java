package com.devops.joblink.job;


import com.devops.joblink.company.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {
    private final JobService jobService;
    private final CompanyService companyService;

    public JobController(JobService jobService, CompanyService companyService) {
        this.jobService = jobService;
        this.companyService = companyService;
    }

    @GetMapping("/companies/{companyId}")
    public ResponseEntity<List<Job>> findJobsByCompanyId(@PathVariable("companyId") long companyId) {
        boolean companyExists = companyService.isCompanyPresent(companyId);
        if (!companyExists) {
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }
        List<Job> jobs = jobService.findJobsByCompanyId(companyId);
        return ResponseEntity.ok(jobs);
    }

    @GetMapping
    public ResponseEntity<List<Job>> findAll(){
        return ResponseEntity.ok(jobService.findAll());
    }

    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody Job job) {
        try{
            boolean isCompanyPresent = companyService.isCompanyPresent(job.getCompany().getId());
            if (!isCompanyPresent) {
                return new ResponseEntity<>("Company Not Found",HttpStatus.NOT_FOUND);
            }
            jobService.createJob(job);
            return new ResponseEntity<>("Job Created Successfully",HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>("Something went wrong",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> findJobById(@PathVariable Long id) {
        Job job = jobService.findJobById(id);
        return (job != null ) ? ResponseEntity.ok(job) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id){
        boolean deleted = jobService.deleteJob(id);
        System.out.println(deleted);
        if(deleted){
            return ResponseEntity.ok("Job Deleted Successfully");
        }
        return new ResponseEntity<>("Job Not Found", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Job> updateJobById(@PathVariable Long id, @RequestBody Job job){
        boolean updated = jobService.update(id, job);
        if(updated){
            Job updatedJob = jobService.findJobById(id);
            return ResponseEntity.ok(updatedJob);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
