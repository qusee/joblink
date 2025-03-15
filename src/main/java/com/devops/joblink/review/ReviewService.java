package com.devops.joblink.review;

import java.util.List;

public interface ReviewService {
    List<Review> getReviewsByCompanyId(Long companyId);
    Review getReviewById(Long id);
    void createReview(Review review);
    boolean updateReview(Review review, Long id);
    boolean deleteReview(Long id);
    void dropReviewsByCompanyId(Long companyId);
}
