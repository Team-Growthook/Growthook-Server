package com.example.growthookserver.api.actionplan.service;

import com.example.growthookserver.api.actionplan.dto.request.ActionPlanCreateRequestDto;
import com.example.growthookserver.api.actionplan.dto.request.ActionPlanUpdateRequestDto;
import com.example.growthookserver.api.actionplan.dto.response.ActionPlanGetResponseDto;
import com.example.growthookserver.api.actionplan.dto.response.DoingActionPlanGetResponseDto;
import com.example.growthookserver.api.actionplan.dto.response.FinishedActionPlanGetResponseDto;

import java.util.List;

public interface ActionPlanService {
    //* 액션플랜 생성
    void createActionPlan(Long seedId, ActionPlanCreateRequestDto actionPlanCreateRequestDto);

    //* 씨앗 별 액션 플랜 조회
    List<ActionPlanGetResponseDto> getActionPlan(Long seedId);

    //* 액션 플랜 수정
    void updateActionPlan(Long actionPlanId, ActionPlanUpdateRequestDto actionPlanUpdateRequestDto);

    //* 액션 플랜 삭제
    void deleteActionPlan(Long actionPlanId);

    //* 액션 플랜 완료 토글
    void completeActionPlan(Long actionPlanId);

    //* 액션 플랜 달성 퍼센트 조회
    int getActionPlanPercent(Long memberId);

    //* 진행 중인 액션 플랜 목록 조회
    List<DoingActionPlanGetResponseDto> getDoingActionPlan(Long memberId);

    //* 완료한 액션 플랜 목록 조회
    List<FinishedActionPlanGetResponseDto> getFinishedActionPlan(Long memberId);
}
