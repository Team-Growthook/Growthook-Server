package com.example.growthookserver.api.cave.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CaveCreateRequestDto {
    @NotBlank
    @Size(max = 7)
    private String name;
    @NotBlank
    @Size(max = 20)
    private String introduction;
    @NotNull
    private Boolean isShared;
}
