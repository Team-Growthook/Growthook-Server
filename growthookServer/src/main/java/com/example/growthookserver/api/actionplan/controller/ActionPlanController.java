package com.example.growthookserver.api.actionplan.controller;

import com.example.growthookserver.api.actionplan.dto.request.ActionPlanCreateRequestDto;
import com.example.growthookserver.api.actionplan.dto.request.ActionPlanUpdateRequestDto;
import com.example.growthookserver.api.actionplan.dto.response.ActionPlanGetResponseDto;
import com.example.growthookserver.api.actionplan.dto.response.DoingActionPlanGetResponseDto;
import com.example.growthookserver.api.actionplan.dto.response.FinishedActionPlanGetResponseDto;
import com.example.growthookserver.api.actionplan.service.ActionPlanService;
import com.example.growthookserver.common.response.ApiResponse;
import com.example.growthookserver.common.response.SuccessStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
@SecurityRequirement(name = "JWT Authentication")
@Tag(name = "AciontPlan - 액션플랜 관련 API",description = "AcitonPlan APi Documnet")
public class ActionPlanController {

    private final ActionPlanService actionPlanService;

    @PostMapping("seed/{seedId}/actionplan")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "ActionPlanPost",description = "액션 플랜 생성 API입니다.")
    public ApiResponse createActionPlan(@PathVariable("seedId")Long seedId, @Valid @RequestBody ActionPlanCreateRequestDto actionPlanCreateRequestDto) {
        actionPlanService.createActionPlan(seedId, actionPlanCreateRequestDto);
        return ApiResponse.success(SuccessStatus.POST_ACTIONPLAN_SUCCESS.getStatusCode(), SuccessStatus.POST_ACTIONPLAN_SUCCESS.getMessage());
    }

    @GetMapping("seed/{seedId}/actionplan")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "ActionPlanGet", description = "씨앗 별 액션 플랜 조회 API입니다.")
    public ApiResponse<ActionPlanGetResponseDto> getActionPlan(@PathVariable Long seedId) {
        return ApiResponse.success(SuccessStatus.GET_SEED_ACTIONPLAN_SUCCESS, actionPlanService.getActionPlan(seedId));
    }

    @PatchMapping("actionplan/{actionPlanId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "ActionPlanPatch", description = "액션 플랜 내용을 수정하는 API입니다.")
    public ApiResponse updateActionPlan(@PathVariable Long actionPlanId, @Valid @RequestBody ActionPlanUpdateRequestDto actionPlanUpdateRequestDto) {
        actionPlanService.updateActionPlan(actionPlanId, actionPlanUpdateRequestDto);
        return ApiResponse.success(SuccessStatus.PATCH_ACTIONPLAN_SUCCESS.getStatusCode(), SuccessStatus.PATCH_ACTIONPLAN_SUCCESS.getMessage());
    }

    @DeleteMapping("actionplan/{actionPlanId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "ActionPlanDelete", description = "액션 플랜을 삭제하는 API입니다.")
    public ApiResponse deleteActionPlan(@PathVariable Long actionPlanId) {
        actionPlanService.deleteActionPlan(actionPlanId);
        return ApiResponse.success(SuccessStatus.DELETE_ACTIONPLAN_SUCCESS.getStatusCode(), SuccessStatus.DELETE_ACTIONPLAN_SUCCESS.getMessage());
    }

    @PatchMapping("actionplan/{actionPlanId}/completion")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "ActionPlanComplete", description = "액션 플랜을 완료하는 API입니다.")
    public ApiResponse completeActionPlan(@PathVariable Long actionPlanId) {
        actionPlanService.completeActionPlan(actionPlanId);
        return ApiResponse.success(SuccessStatus.COMPLETE_ACTIONPLAN_SUCCESS.getStatusCode(),SuccessStatus.COMPLETE_ACTIONPLAN_SUCCESS.getMessage());
    }

    @GetMapping("member/{memberId}/actionplan/percent")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "ActionPlanPercent", description = "완료한 액션 플랜 퍼센트를 구하는 API입니다.")
    public ApiResponse<Integer> getActionPlanPercent(@PathVariable Long memberId) {
        return ApiResponse.success(SuccessStatus.GET_FINISHED_ACTIONPLAN_PERCENT, actionPlanService.getActionPlanPercent(memberId));
    }

    @GetMapping("member/{memberId}/doing")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "DoingActionPlan", description = "진행 중인 액션 플랜 목록을 조회하는 API입니다.")
    public ApiResponse<DoingActionPlanGetResponseDto> getDoingActionPlan(@PathVariable Long memberId) {
        return ApiResponse.success(SuccessStatus.GET_DOING_ACTIONPLAN_SUCCESS,actionPlanService.getDoingActionPlan(memberId));
    }

    @GetMapping("member/{memberId}/finished")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "FinishedActionPlan",description = "완료한 액션 플랜 목록을 조회하는 API입니다.")
    public ApiResponse<FinishedActionPlanGetResponseDto> getFinishedActionPlan(@PathVariable Long memberId) {
        return ApiResponse.success(SuccessStatus.GET_FINISHED_ACTIONPLAN_SUCCESS, actionPlanService.getFinishedActionPlan(memberId));
    }

    @PatchMapping("actionplan/{actionplanId}/scrap")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "ScrapedActionPlan", description = "액션 플랜을 스크랩 하는 API입니다.")
    public ApiResponse toggleActionPlanScrapStatus(@PathVariable Long actionplanId) {
        actionPlanService.toggleActionPlanScrapStatus(actionplanId);
        return ApiResponse.success(SuccessStatus.TOGGLE_ACTIONPLAN_SCRAP.getStatusCode(), SuccessStatus.TOGGLE_SEED_SCRAP_STATUS.getMessage());
    }
}
