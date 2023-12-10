package com.example.growthookserver.api.seed.controller;

import com.example.growthookserver.api.cave.dto.request.CaveCreateRequestDto;
import com.example.growthookserver.api.cave.dto.response.CaveCreateResponseDto;
import com.example.growthookserver.api.seed.dto.request.SeedCreateRequestDto;
import com.example.growthookserver.api.seed.dto.response.SeedCreateResponseDto;
import com.example.growthookserver.api.seed.service.SeedService;
import com.example.growthookserver.common.response.ApiResponse;
import com.example.growthookserver.common.response.SuccessStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
@Tag(name = "Seed - 인사이트 관련 API", description = "Seed API Document")
public class SeedController {

  private final SeedService seedService;

  @PostMapping("cave/{caveId}/seed")
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(summary = "SeedPost", description = "씨앗 생성 API입니다.")
  public ApiResponse<SeedCreateResponseDto> createSeed(@PathVariable("caveId") Long caveId, @Valid @RequestBody SeedCreateRequestDto seedCreateRequestDto) {
    return ApiResponse.success(
        SuccessStatus.POST_SEED_SUCCESS, seedService.createSeed(caveId, seedCreateRequestDto));
  }

  @DeleteMapping("/seed/{seedId}")
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "SeedDelete", description = "씨앗 삭제 API입니다.")
  public ApiResponse deleteSeed(@PathVariable Long seedId) {
    seedService.deleteSeed(seedId);
    return ApiResponse.success(SuccessStatus.DELETE_SEED.getStatusCode(),SuccessStatus.DELETE_SEED.getMessage());
  }


}
