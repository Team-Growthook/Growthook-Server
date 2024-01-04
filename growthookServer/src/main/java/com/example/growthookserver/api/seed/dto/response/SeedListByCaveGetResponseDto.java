package com.example.growthookserver.api.seed.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(staticName = "of")
public class SeedListByCaveGetResponseDto {
  private Long seedId;
  private String insight;
  private Long remainingDays;
  private Boolean isLocked;
  private Boolean isScraped;
  private Boolean hasActionPlan;
}
