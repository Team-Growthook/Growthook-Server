package com.example.growthookserver.api.seed.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(staticName = "of")
public class SeedDetailGetResponseDto {
    private String caveName;
    private String insight;
    private String memo;
    private String source;
    private String url;
    private Boolean isScraped;
    private String lockDate;
    private Long remainingDays;
}
