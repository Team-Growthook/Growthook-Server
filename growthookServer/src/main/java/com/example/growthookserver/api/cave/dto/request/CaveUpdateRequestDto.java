package com.example.growthookserver.api.cave.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CaveUpdateRequestDto {
    private String name;
    private String introduction;
    private Boolean isShared;
}
