package com.example.growthookserver.api.review.repository;

import com.example.growthookserver.api.review.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {

}
