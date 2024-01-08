package com.example.growthookserver.api.review.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(staticName = "of")
public class ReviewDetailGetResponseDto {
  private String actionPlan;
  private Boolean isScraped;
  private String content;
  private String reviewDate;
}
