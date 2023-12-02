package com.example.growthookserver.api.cave.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CaveCreateRequestDto {
    @NotBlank
    private String name;
    @NotBlank
    private String introduction;
    @NotBlank
    private Boolean isShared;
}
