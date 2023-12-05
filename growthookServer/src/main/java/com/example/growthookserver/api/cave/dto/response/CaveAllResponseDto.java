package com.example.growthookserver.api.cave.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(staticName = "of")
public class CaveAllResponseDto {
    private Long caveId;
    private String caveName;
}
