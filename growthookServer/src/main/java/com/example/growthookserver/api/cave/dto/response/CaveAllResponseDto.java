package com.example.growthookserver.api.cave.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CaveAllResponseDto {
    private Long caveId;
    private String caveName;

    public static CaveAllResponseDto of(Long caveId, String caveName){
        return new CaveAllResponseDto(caveId, caveName);
    }
}
