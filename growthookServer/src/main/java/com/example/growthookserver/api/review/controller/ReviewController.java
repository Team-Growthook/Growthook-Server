package com.example.growthookserver.api.review.controller;

import com.example.growthookserver.api.review.dto.request.ReviewCreateRequestDto;
import com.example.growthookserver.api.review.dto.response.ReviewDetailGetResponseDto;
import com.example.growthookserver.api.review.service.ReviewService;
import com.example.growthookserver.common.response.ApiResponse;
import com.example.growthookserver.common.response.SuccessStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
@Tag(name = "Review - 리뷰 관련 API", description = "Review API Document")
public class ReviewController {

  private final ReviewService reviewService;
  @PostMapping("actionplan/{actionPlanId}/review")
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(summary = "ReviewPost", description = "리뷰 생성 API입니다.")
  public ApiResponse createReview(@PathVariable("actionPlanId") Long actionPlanId, @Valid @RequestBody ReviewCreateRequestDto reviewCreateRequestDto) {
    reviewService.createReview(actionPlanId, reviewCreateRequestDto);
    return ApiResponse.success(
        SuccessStatus.POST_REVIEW_SUCCESS.getStatusCode(), SuccessStatus.POST_REVIEW_SUCCESS.getMessage());
  }

  @GetMapping("actionplan/{actionPlanId}/review")
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "ReviewGet", description = "리뷰 상세 조회 API입니다.")
  public ApiResponse<ReviewDetailGetResponseDto> getReviewDetail(@PathVariable("actionPlanId") Long actionPlanId) {
    return ApiResponse.success(SuccessStatus.GET_REVIEW_DETAIL, reviewService.getReviewDetail(actionPlanId));
  }
}
