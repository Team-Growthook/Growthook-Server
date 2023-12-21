package com.example.growthookserver.api.seed.controller;

import com.example.growthookserver.api.seed.dto.request.SeedCreateRequestDto;
import com.example.growthookserver.api.seed.dto.request.SeedUpdateRequestDto;
import com.example.growthookserver.api.seed.dto.response.SeedCreateResponseDto;
import com.example.growthookserver.api.seed.service.SeedService;
import com.example.growthookserver.common.response.ApiResponse;
import com.example.growthookserver.common.response.SuccessStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
@Tag(name = "Seed - 인사이트 관련 API", description = "Seed API Document")
public class SeedController {

  private final SeedService seedService;

  @PostMapping("/cave/{caveId}/seed")
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

  @PatchMapping("/seed/{seedId}")
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "SeedPatch", description = "씨앗 내용을 수정하는 API입니다.")
  public ApiResponse updateSeed(@PathVariable Long seedId, @Valid @RequestBody SeedUpdateRequestDto seedUpdateRequestDto) {
    seedService.updateSeed(seedId, seedUpdateRequestDto);
    return ApiResponse.success(SuccessStatus.PATCH_SEED_SUCCESS.getStatusCode(), SuccessStatus.PATCH_SEED_SUCCESS.getMessage());
  }

}
