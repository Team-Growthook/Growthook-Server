package com.example.growthookserver.api.cave.controller;

import com.example.growthookserver.api.cave.dto.request.CaveCreateRequestDto;
import com.example.growthookserver.api.cave.dto.request.CaveUpdateRequestDto;
import com.example.growthookserver.api.cave.dto.response.CaveAllResponseDto;
import com.example.growthookserver.api.cave.dto.response.CaveCreateResponseDto;
import com.example.growthookserver.api.cave.dto.response.CaveDetailGetResponseDto;
import com.example.growthookserver.api.cave.service.CaveService;
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
@Tag(name = "Cave - 동굴 관련 API", description = "Cave API Document")
public class CaveController {
    private final CaveService caveService;

    @PostMapping("member/{memberId}/cave")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "CavePost", description = "동굴 생성 API입니다.")
    public ApiResponse<CaveCreateResponseDto> createCave(@PathVariable("memberId") Long memberId, @Valid @RequestBody CaveCreateRequestDto caveCreateRequestDto) {
        return ApiResponse.success(SuccessStatus.POST_CAVE_SUCCESS, caveService.createCave(memberId, caveCreateRequestDto));
    }

    @GetMapping("member/{memberId}/cave/all")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary =  "CaveAllGet", description = "동굴 리스트를 가져오는 API입니다.")
    public ApiResponse<CaveAllResponseDto> getCaveAll(@PathVariable Long memberId) {
        return ApiResponse.success(SuccessStatus.GET_CAVE_ALL, caveService.getCaveAll(memberId));
    }

    @PatchMapping("cave/{caveId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "CavePatch", description = "동굴 내용을 수정하는 API입니다.")
    public ApiResponse updateCave(@PathVariable Long caveId, @Valid @RequestBody CaveUpdateRequestDto caveUpdateRequestDto) {
        caveService.updateCave(caveId, caveUpdateRequestDto);
        return ApiResponse.success(SuccessStatus.PATCH_CAVE_SUCCESS.getStatusCode(), SuccessStatus.PATCH_CAVE_SUCCESS.getMessage());
    }

    @GetMapping("member/{memberId}/cave/{caveId}/detail")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "CaveDetailGet", description = "동굴의 상세 내용을 조회하는 API입니다.")
    public ApiResponse<CaveDetailGetResponseDto> getCaveDetail(@PathVariable Long memberId, @PathVariable Long caveId) {
        return ApiResponse.success(SuccessStatus.GET_CAVE_DETAIL, caveService.getCaveDetail(memberId, caveId));
    }

    @DeleteMapping("cave/{caveId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "CaveDelete", description = "동굴을 삭제하는 API입니다.")
    public ApiResponse deleteCave(@PathVariable Long caveId) {
        caveService.deleteCave(caveId);
        return ApiResponse.success(SuccessStatus.DELETE_CAVE.getStatusCode(),SuccessStatus.DELETE_CAVE.getMessage());
    }
}

