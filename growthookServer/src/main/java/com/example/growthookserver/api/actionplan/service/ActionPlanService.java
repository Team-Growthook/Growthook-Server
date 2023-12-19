package com.example.growthookserver.api.actionplan.service;

import com.example.growthookserver.api.actionplan.dto.request.ActionPlanCreateRequestDto;
import com.example.growthookserver.api.actionplan.dto.response.ActionPlanCreateResponseDto;

public interface ActionPlanService {
    //* 액션플랜 생성
    ActionPlanCreateResponseDto createActionPlan(Long seedId, ActionPlanCreateRequestDto actionPlanCreateRequestDto);
}
