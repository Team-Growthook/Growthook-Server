package com.example.growthookserver.api.cave.controller;

import com.example.growthookserver.api.cave.dto.request.CaveCreateRequestDto;
import com.example.growthookserver.api.cave.dto.request.CaveUpdateRequestDto;
import com.example.growthookserver.api.cave.dto.response.CaveAllResponseDto;
import com.example.growthookserver.api.cave.dto.response.CaveCreateResponseDto;
import com.example.growthookserver.api.cave.dto.response.CaveUpdateResponseDto;
import com.example.growthookserver.api.cave.service.CaveService;
import com.example.growthookserver.common.response.ApiResponse;
import com.example.growthookserver.common.response.SuccessStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
@Tag(name = "Cave Controller", description = "Cave API Document")
public class CaveController {
    private final CaveService caveService;

    @PostMapping("member/{memberId}/cave")
    @Operation(summary = "CavePost", description = "동굴 생성 API입니다.")
    public ApiResponse<CaveCreateResponseDto> createCave(@PathVariable("memberId") Long memberId, @Valid @RequestBody CaveCreateRequestDto caveCreateRequestDto) {
        return ApiResponse.success(SuccessStatus.POST_CAVE_SUCCESS, caveService.createCave(memberId, caveCreateRequestDto));
    }

    @GetMapping("member/{memberId}/cave/all")
    @Operation(summary =  "CaveAllGet", description = "동굴 리스트를 가져오는 API입니다.")
    public ApiResponse<CaveAllResponseDto> getCaveAll(@PathVariable Long memberId) {
        return ApiResponse.success(SuccessStatus.GET_CAVE_ALL, caveService.getCaveAll(memberId));
    }

    @PatchMapping("cave/{caveId}/patch")
    @Operation(summary = "CavePatch", description = "동굴 내용을 수정하는 API입니다.")
    public ApiResponse updateCave(@PathVariable Long caveId, @Valid @RequestBody CaveUpdateRequestDto caveUpdateRequestDto) {
        caveService.updateCave(caveId, caveUpdateRequestDto);
        return ApiResponse.success(SuccessStatus.PATCH_CAVE_SUCCESS.getStatusCode(), SuccessStatus.PATCH_CAVE_SUCCESS.getMessage());
    }
}

