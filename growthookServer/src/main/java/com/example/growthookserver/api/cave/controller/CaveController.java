package com.example.growthookserver.api.cave.controller;

import com.example.growthookserver.api.cave.dto.request.CaveCreateRequestDto;
import com.example.growthookserver.api.cave.dto.response.CaveCreateResponseDto;
import com.example.growthookserver.api.cave.service.CaveService;
import com.example.growthookserver.common.response.ApiResponse;
import com.example.growthookserver.common.response.SuccessStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
}
