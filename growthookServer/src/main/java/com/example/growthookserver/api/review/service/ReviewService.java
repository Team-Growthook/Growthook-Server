package com.example.growthookserver.api.review.service;

import com.example.growthookserver.api.review.dto.request.ReviewCreateRequestDto;
import com.example.growthookserver.api.review.dto.response.ReviewDetailGetResponseDto;

public interface ReviewService{

  //* 액션 플랜별 리뷰 작성
  void createReview(Long actionPlanId, ReviewCreateRequestDto reviewCreateRequestDto);

  //* 리뷰 내용 상세 조회
  ReviewDetailGetResponseDto getReviewDetail(Long actionPlanId);
}
