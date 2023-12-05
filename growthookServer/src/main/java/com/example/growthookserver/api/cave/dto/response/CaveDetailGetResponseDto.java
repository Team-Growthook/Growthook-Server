package com.example.growthookserver.api.cave.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CaveDetailGetResponseDto {
    private String caveName;
    private String introduction;
    private String nickname;
    private Boolean isShared;

    public static CaveDetailGetResponseDto of(String caveName,String introduction, String nickname, Boolean isShared){
        return new CaveDetailGetResponseDto(caveName,introduction, nickname, isShared);
    }
}
