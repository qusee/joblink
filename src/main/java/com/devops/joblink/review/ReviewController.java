package com.devops.joblink.review;

import com.devops.joblink.company.Company;
import com.devops.joblink.company.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}/reviews")
public class ReviewController {
    private final ReviewService reviewService;
    private final CompanyService companyService;


    public ReviewController(ReviewService reviewService, CompanyService companyService) {
        this.reviewService = reviewService;
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<List<Review>> findReviewsByCompanyId(@PathVariable Long companyId) {
        return ResponseEntity.ok(reviewService.getReviewsByCompanyId(companyId));
    }

    @PostMapping
    public ResponseEntity<String> addReview(@RequestBody Review review, @PathVariable String companyId) {
        Company company = companyService.findCompanyById(Long.parseLong(companyId));
        if (company == null) {
            return new ResponseEntity<>("Company does not exist",HttpStatus.NOT_FOUND);
        }
        review.setCompany(new Company(Long.parseLong(companyId)));
        reviewService.createReview(review);
        return new ResponseEntity<>("Review Submitted", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateReview(@RequestBody Review review, @PathVariable Long id, @PathVariable String companyId) {
        if(review.getCompany().getId().equals(Long.parseLong(companyId))){
            boolean updated = reviewService.updateReview(review, id);
            if (updated) {
                return new ResponseEntity<>("Review Updated", HttpStatus.OK);
            }
            return new ResponseEntity<>("Review Not Found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Bad Request", HttpStatus.BAD_REQUEST);
    }
}
