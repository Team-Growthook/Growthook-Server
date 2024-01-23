package com.example.growthookserver.api.actionplan.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(staticName = "of")
public class DoingActionPlanGetResponseDto {
    private Long actionPlanId;

    private String content;

    private Boolean isScraped;

    private Long seedId;

    private Boolean hasReview;

}
