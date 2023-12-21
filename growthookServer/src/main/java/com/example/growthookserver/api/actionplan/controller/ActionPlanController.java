package com.example.growthookserver.api.actionplan.controller;

import com.example.growthookserver.api.actionplan.dto.request.ActionPlanCreateRequestDto;
import com.example.growthookserver.api.actionplan.dto.response.ActionPlanCreateResponseDto;
import com.example.growthookserver.api.actionplan.service.ActionPlanService;
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
@Tag(name = "AciontPlan - 액션플랜 관련 API",description = "AcitonPlan APi Documnet")
public class ActionPlanController {

    private final ActionPlanService actionPlanService;

    @PostMapping("seed/{seedId}/actionPlan")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "ActionPlanPost",description = "액션 플랜 생성 API입니다.")
    public ApiResponse<ActionPlanCreateResponseDto> createActionPlan(@PathVariable("seedId")Long seedId, @Valid @RequestBody ActionPlanCreateRequestDto actionPlanCreateRequestDto) {
        return ApiResponse.success(SuccessStatus.POST_ACTIONPLAN_SUCCESS, actionPlanService.createActionPlan(seedId, actionPlanCreateRequestDto));
    }
}
