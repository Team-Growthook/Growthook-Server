package com.example.growthookserver.api.actionplan.service;

import com.example.growthookserver.api.actionplan.dto.request.ActionPlanCreateRequestDto;
import com.example.growthookserver.api.actionplan.dto.response.ActionPlanCreateResponseDto;
import com.example.growthookserver.api.actionplan.dto.response.ActionPlanGetResponseDto;

import java.util.List;

public interface ActionPlanService {
    //* 액션플랜 생성
    ActionPlanCreateResponseDto createActionPlan(Long seedId, ActionPlanCreateRequestDto actionPlanCreateRequestDto);

    //* 씨앗 별 액션 플랜 조회
    List<ActionPlanGetResponseDto> getActionPlan(Long seedId);
}
