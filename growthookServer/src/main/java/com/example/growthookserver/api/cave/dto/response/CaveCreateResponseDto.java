package com.example.growthookserver.api.cave.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CaveCreateResponseDto {
    private Long caveId;

    public static CaveCreateResponseDto of(Long caveId){
        return new CaveCreateResponseDto(caveId);
    }
}
