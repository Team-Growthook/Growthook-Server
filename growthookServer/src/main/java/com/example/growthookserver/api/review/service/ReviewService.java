package com.example.growthookserver.api.review.service;

import com.example.growthookserver.api.review.dto.request.ReviewCreateRequestDto;

public interface ReviewService{

  //* 액션 플랜별 리뷰 작성
  void createReview(Long actionPlanId, ReviewCreateRequestDto reviewCreateRequestDto);

}
