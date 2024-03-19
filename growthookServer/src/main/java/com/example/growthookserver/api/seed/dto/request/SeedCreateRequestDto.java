package com.example.growthookserver.api.seed.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(staticName = "of")
public class SeedCreateRequestDto {
  @NotBlank
  @Size(max = 30)
  private String insight;

  @Size(max = 300)
  private String memo;

  @Size(max = 20)
  private String source;

  private String url;

  @NotNull
  private Integer goalMonth;

}
