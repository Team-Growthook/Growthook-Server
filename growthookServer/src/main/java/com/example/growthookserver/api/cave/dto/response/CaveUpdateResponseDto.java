package com.example.growthookserver.api.cave.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CaveUpdateResponseDto {
    private Long id;

    public static CaveUpdateResponseDto of(Long caveId){
        return new CaveUpdateResponseDto(caveId);
    }
}
