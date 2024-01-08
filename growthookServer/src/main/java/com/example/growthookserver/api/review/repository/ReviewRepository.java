package com.example.growthookserver.api.review.repository;

import com.example.growthookserver.api.cave.domain.Cave;
import com.example.growthookserver.api.review.domain.Review;
import com.example.growthookserver.common.exception.NotFoundException;
import com.example.growthookserver.common.response.ErrorStatus;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {

  Optional<Review> findReviewByActionPlanId(Long actionPlanId);

  default Review findReviewByActionPlanIdOrThrow(Long actionPlanId) {
    return findReviewByActionPlanId(actionPlanId)
        .orElseThrow(() -> new NotFoundException(ErrorStatus.NOT_FOUND_REVIEW.getMessage()));
  }

}
