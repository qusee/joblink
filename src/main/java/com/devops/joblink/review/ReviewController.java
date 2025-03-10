package com.devops.joblink.review;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}/reviews")
public class ReviewController {

    @GetMapping
    public ResponseEntity<String> getReviews(@PathVariable String companyId) {
        return ResponseEntity.ok("Reviews GET ALL for company " + companyId);
    }
}
