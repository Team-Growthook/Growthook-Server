package com.example.growthookserver.api.cave.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    private Boolean isShared;
}
