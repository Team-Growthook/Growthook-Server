package com.example.growthookserver.api.seed.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(staticName = "of")
public class SeedAlarmGetResponseDto {
    private int seedCount;
    private int daysRemaining;
}
