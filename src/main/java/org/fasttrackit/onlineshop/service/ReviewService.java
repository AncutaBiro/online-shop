package org.fasttrackit.onlineshop.service;

import org.fasttrackit.onlineshop.persistence.ReviewRepository;
import org.fasttrackit.onlineshop.transfer.review.ReviewResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

//    public Page<ReviewResponse> getReviews(long productId, Pageable pageable) {
//        reviewRepository.findByProductId()
//    }

}
