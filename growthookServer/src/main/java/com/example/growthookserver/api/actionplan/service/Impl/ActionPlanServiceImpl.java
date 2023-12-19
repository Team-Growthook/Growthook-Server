package com.example.growthookserver.api.actionplan.service.Impl;

import com.example.growthookserver.api.actionplan.domain.ActionPlan;
import com.example.growthookserver.api.actionplan.dto.request.ActionPlanCreateRequestDto;
import com.example.growthookserver.api.actionplan.dto.response.ActionPlanCreateResponseDto;
import com.example.growthookserver.api.actionplan.repository.ActionPlanRepository;
import com.example.growthookserver.api.actionplan.service.ActionPlanService;
import com.example.growthookserver.api.seed.domain.Seed;
import com.example.growthookserver.api.seed.repository.SeedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ActionPlanServiceImpl implements ActionPlanService {

    private final ActionPlanRepository actionPlanRepository;
    private final SeedRepository seedRepository;

    @Override
    @Transactional
    public ActionPlanCreateResponseDto createActionPlan(Long seedId, ActionPlanCreateRequestDto actionPlanCreateRequestDto){
        Seed seed = seedRepository.findSeedByIdOrThrow(seedId);
        ActionPlan actionPlan = ActionPlan.builder()
                .content(actionPlanCreateRequestDto.getContent())
                .seed(seed)
                .build();
        ActionPlan savedActionPlan = actionPlanRepository.save(actionPlan);
        return ActionPlanCreateResponseDto.of(savedActionPlan.getId());
    }
}