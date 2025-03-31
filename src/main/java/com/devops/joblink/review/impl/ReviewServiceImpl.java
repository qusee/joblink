package com.devops.joblink.review.impl;


import com.devops.joblink.review.Review;
import com.devops.joblink.review.ReviewRepository;
import com.devops.joblink.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> getReviewsByCompanyId(Long companyId) {
        return reviewRepository.findReviewsByCompanyId(companyId);
    }

    @Override
    public Review getReviewById(Long id) {
        return reviewRepository.findById(id).orElse(null);
    }

    @Override
    public void createReview(Review review) {
        reviewRepository.save(review);
    }

    @Override
    public boolean updateReview(Review review, Long id) {
        Optional<Review> optionalReview = reviewRepository.findById(id);
        if (optionalReview.isPresent()) {
            Review existingReview = optionalReview.get();
            existingReview.setTitle(review.getTitle());
            existingReview.setDescription(review.getDescription());
            existingReview.setRating(review.getRating());
            reviewRepository.save(existingReview);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteReview(Long id, Long companyId) {
        if (reviewRepository.existsById(id)) {
            Review review = getReviewById(id);
            reviewRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public void dropReviewsByCompanyId(Long companyId) {
        List<Review> optionalReviews = getReviewsByCompanyId(companyId);
        reviewRepository.deleteAllById(optionalReviews.stream().map(Review::getId).collect(Collectors.toList()));
    }
}
