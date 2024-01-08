package com.example.growthookserver.api.seed.controller;

import com.example.growthookserver.api.seed.dto.request.SeedMoveRequestDto;
import com.example.growthookserver.api.seed.dto.request.SeedCreateRequestDto;
import com.example.growthookserver.api.seed.dto.request.SeedUpdateRequestDto;
import com.example.growthookserver.api.seed.dto.response.SeedAlarmGetResponseDto;
import com.example.growthookserver.api.seed.dto.response.SeedCreateResponseDto;
import com.example.growthookserver.api.seed.dto.response.SeedDetailGetResponseDto;
import com.example.growthookserver.api.seed.dto.response.SeedListGetResponseDto;
import com.example.growthookserver.api.seed.dto.response.SeedMoveResponseDto;
import com.example.growthookserver.api.seed.service.SeedService;
import com.example.growthookserver.common.response.ApiResponse;
import com.example.growthookserver.common.response.SuccessStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
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

  @GetMapping("seed/{seedId}/detail")
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "SeedDetailGet", description = "씨앗의 상세 내용을 조회하는 API입니다.")
  public ApiResponse<SeedDetailGetResponseDto> getSeedDetail(@PathVariable Long seedId) {
    return ApiResponse.success(SuccessStatus.GET_SEED_DETAIL, seedService.getSeedDetail(seedId));
  }

  @PatchMapping("/seed/{seedId}")
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "SeedPatch", description = "씨앗 내용을 수정하는 API입니다.")
  public ApiResponse updateSeed(@PathVariable Long seedId, @Valid @RequestBody SeedUpdateRequestDto seedUpdateRequestDto) {
    seedService.updateSeed(seedId, seedUpdateRequestDto);
    return ApiResponse.success(SuccessStatus.PATCH_SEED_SUCCESS.getStatusCode(), SuccessStatus.PATCH_SEED_SUCCESS.getMessage());
  }

  @PostMapping("seed/{seedId}/move")
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "SeedMove", description = "씨앗을 다른 동굴로 이동하는 API입니다.")
  public ApiResponse<SeedMoveResponseDto> moveSeed(@PathVariable Long seedId, @Valid @RequestBody SeedMoveRequestDto seedMoveRequestDto) {
    return ApiResponse.success(SuccessStatus.MOVE_SEED_SUCCESS, seedService.moveSeed(seedId, seedMoveRequestDto));
  }

  @GetMapping("/cave/{caveId}/seed/list")
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "SeedListByCaveGet", description = "보관함별 씨앗 리스트를 조회하는 API입니다.")
  public ApiResponse<List<SeedListGetResponseDto>> getSeedListByCave(@PathVariable Long caveId) {
    return ApiResponse.success(SuccessStatus.GET_SEED_LIST_BY_CAVE, seedService.getSeedListByCave(caveId));
  }

  @GetMapping("member/{memberId}/seed/list")
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "SeedListGet", description = "전체 씨앗 리스트를 조회하는 API입니다.")
  public ApiResponse<List<SeedListGetResponseDto>> getSeedList(@PathVariable Long memberId) {
    return ApiResponse.success(SuccessStatus.GET_SEED_LIST, seedService.getSeedList(memberId));
  }

  @PatchMapping("seed/{seedId}/scrap/status")
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "ToggleSeedScrapStatus", description = "씨앗 스크랩 여부를 전환하는 토글 API입니다.")
  public ApiResponse toggleSeedScrapStatus(@PathVariable Long seedId) {
    seedService.toggleSeedScrapStatus(seedId);
    return ApiResponse.success(SuccessStatus.TOGGLE_SEED_SCRAP_STATUS.getStatusCode(), SuccessStatus.TOGGLE_SEED_SCRAP_STATUS.getMessage());
  }

  @GetMapping("member/{memberId}/alarm")
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "SeedAlarm", description = "기한이 3일 이내로 남은 씨앗에 대한 알림을 조회하는 API입니다.")
  public ApiResponse<SeedAlarmGetResponseDto> getSeedAlarm(@PathVariable Long memberId) {
    return ApiResponse.success(SuccessStatus.GET_SEED_ALARM, seedService.getSeedAlarm(memberId));
  }

  @PatchMapping("seed/{seedId}/lock/status")
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "unlockSeed", description = "인사이트 잠금을 해제하는 API입니다.")
  public ApiResponse unlockSeed(@PathVariable Long seedId) {
    seedService.unlockSeed(seedId);
    return ApiResponse.success(SuccessStatus.UNLOCK_SEED.getStatusCode(), SuccessStatus.UNLOCK_SEED.getMessage());
  }

}
