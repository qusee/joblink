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

    @GetMapping // Get Reviews By Company ID
    public ResponseEntity<List<Review>> findReviewsByCompanyId(@PathVariable Long companyId) {
        boolean companyExists = companyService.isCompanyPresent(companyId);
        if (!companyExists) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(reviewService.getReviewsByCompanyId(companyId));
    }

    @GetMapping("/{id}") // Get Review By ID
    public ResponseEntity<Review> findReviewById(@PathVariable Long id, @PathVariable Long companyId) {
        boolean companyExists = companyService.isCompanyPresent(companyId);
        if (!companyExists) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Review review = reviewService.getReviewById(id);
        if (review == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(review);
    }

    @PostMapping // Post review
    public ResponseEntity<String> addReview(@RequestBody Review review, @PathVariable Long companyId) {
        boolean companyExists = companyService.isCompanyPresent(companyId);
        if (!companyExists) {
            return new ResponseEntity<>("Company does not exist",HttpStatus.NOT_FOUND);
        }
        review.setCompany(new Company(companyId)); // injecting the companyId to the review.
        reviewService.createReview(review);
        return new ResponseEntity<>("Review Submitted", HttpStatus.CREATED);
    }

    @PutMapping("/{id}") // Update Review By ID
    public ResponseEntity<String> updateReview(@RequestBody Review review, @PathVariable Long id, @PathVariable String companyId) {
        Review existingReview = reviewService.getReviewById(id);
        if (existingReview == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if(existingReview.getCompany().getId().equals(Long.parseLong(companyId))){
            boolean updated = reviewService.updateReview(review, id);
            if (updated) {
                return new ResponseEntity<>("Review Updated", HttpStatus.OK);
            }
            return new ResponseEntity<>("Review Not Found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Bad Request or Company Does Not Exists", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}") // Delete Review By ID
    public ResponseEntity<String> deleteReview(@PathVariable Long id, @PathVariable Long companyId) {

        boolean companyExists = companyService.isCompanyPresent(companyId);
        if (!companyExists) {
            return new ResponseEntity<>("Company does not exist",HttpStatus.NOT_FOUND);
        }

        boolean deleted = reviewService.deleteReview(id);
        if (deleted) {
            return new ResponseEntity<>("Review Deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("Review Not Found", HttpStatus.NOT_FOUND);
    }
}
