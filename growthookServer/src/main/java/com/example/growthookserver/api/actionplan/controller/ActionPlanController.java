package com.example.growthookserver.api.actionplan.controller;

import com.example.growthookserver.api.actionplan.dto.request.ActionPlanCreateRequestDto;
import com.example.growthookserver.api.actionplan.dto.request.ActionPlanUpdateRequestDto;
import com.example.growthookserver.api.actionplan.dto.response.ActionPlanCreateResponseDto;
import com.example.growthookserver.api.actionplan.dto.response.ActionPlanGetResponseDto;
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

    @GetMapping("seed/{seedId}/actionPlan")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "ActionPlanGet", description = "씨앗 별 액션 플랜 조회 API입니다.")
    public ApiResponse<ActionPlanGetResponseDto> getActionPlan(@PathVariable Long seedId) {
        return ApiResponse.success(SuccessStatus.GET_SEED_ACTIONPLAN_SUCCESS, actionPlanService.getActionPlan(seedId));
    }

    @PatchMapping("actionPlan/{actionPlanId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "ActionPlanPatch", description = "액션 플랜 내용을 수정하는 API입니다.")
    public ApiResponse updateActionPlan(@PathVariable Long actionPlanId, @Valid @RequestBody ActionPlanUpdateRequestDto actionPlanUpdateRequestDto) {
        actionPlanService.updateActionPlan(actionPlanId, actionPlanUpdateRequestDto);
        return ApiResponse.success(SuccessStatus.PATCH_ACTIONPLAN_SUCCESS.getStatusCode(), SuccessStatus.PATCH_ACTIONPLAN_SUCCESS.getMessage());
    }

    @DeleteMapping("actionPlan/{actionPlanId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "ActionPlanDelete", description = "액션 플랜을 삭제하는 API입니다.")
    public ApiResponse deleteActionPlan(@PathVariable Long actionPlanId) {
        actionPlanService.deleteActionPlan(actionPlanId);
        return ApiResponse.success(SuccessStatus.DELETE_ACTIONPLAN_SUCCESS.getStatusCode(), SuccessStatus.DELETE_ACTIONPLAN_SUCCESS.getMessage());
    }

    @PatchMapping("actionPlan/{actionPlanId}/completion")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "ActionPlanComplete", description = "액션 플랜을 완료하는 API입니다.")
    public ApiResponse completeActionPlan(@PathVariable Long actionPlanId) {
        actionPlanService.completeActionPlan(actionPlanId);
        return ApiResponse.success(SuccessStatus.COMPLETE_ACTIONPLAN_SUCCESS.getStatusCode(),SuccessStatus.COMPLETE_ACTIONPLAN_SUCCESS.getMessage());
    }

    @GetMapping("member/{memberId}/actionPlan/percent")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "ActionPlanPercent", description = "완료한 액션 플랜 퍼센트를 구하는 API입니다.")
    public ApiResponse<Integer> getActionPlanPercent(@PathVariable Long memberId) {
        return ApiResponse.success(SuccessStatus.GET_FINISHED_ACTIONPLAN_PERCENT, actionPlanService.getActionPlanPercent(memberId));
    }
}
