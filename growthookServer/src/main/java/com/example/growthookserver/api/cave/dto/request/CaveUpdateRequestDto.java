package com.example.growthookserver.api.cave.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CaveUpdateRequestDto {
    @NotBlank
    @Size(max =7)
    private String name;
    @NotBlank
    @Size(max = 20)
    private String introduction;
    @NotNull
    private Boolean isShared;
}
