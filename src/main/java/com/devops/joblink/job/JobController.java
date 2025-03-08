package com.devops.joblink.job;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {
    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<List<Job>> findAll(){
        return ResponseEntity.ok(jobService.findAll());
    }

    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody Job job) {
        jobService.createJob(job);

        return new ResponseEntity<>("Job Created Successfully",HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> findJobById(@PathVariable Long id) {
        Job job = jobService.findJobById(id);
        return (job != null ) ? ResponseEntity.ok(job) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id){
        Job job = jobService.findJobById(id);
        if(job == null){
            return new ResponseEntity<>("Job you are trying to delete is not found", HttpStatus.NOT_FOUND);
        }
        jobService.deleteJob(id);
        return ResponseEntity.ok("Job Deleted Successfully") ;
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
