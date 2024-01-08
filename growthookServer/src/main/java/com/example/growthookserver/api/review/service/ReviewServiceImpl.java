package com.example.growthookserver.api.review.service;

import com.example.growthookserver.api.actionplan.domain.ActionPlan;
import com.example.growthookserver.api.actionplan.repository.ActionPlanRepository;
import com.example.growthookserver.api.review.domain.Review;
import com.example.growthookserver.api.review.dto.request.ReviewCreateRequestDto;
import com.example.growthookserver.api.review.repository.ReviewRepository;
import com.example.growthookserver.api.seed.domain.Seed;
import com.example.growthookserver.api.seed.repository.SeedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewServiceImpl implements ReviewService {

  private final ReviewRepository reviewRepository;
  private final ActionPlanRepository actionPlanRepository;

  @Override
  @Transactional
  public void createReview(Long actionPlanId, ReviewCreateRequestDto reviewCreateRequestDto) {
    ActionPlan actionPlan = actionPlanRepository.findActionPlanByIdOrThrow(actionPlanId);
    Review review = Review.builder()
        .content(reviewCreateRequestDto.getContent())
        .actionPlan(actionPlan)
        .build();
    reviewRepository.save(review);
  }
}
