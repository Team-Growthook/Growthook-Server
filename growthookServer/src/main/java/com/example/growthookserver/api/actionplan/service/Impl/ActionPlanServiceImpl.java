package com.example.growthookserver.api.actionplan.service.Impl;

import com.example.growthookserver.api.actionplan.domain.ActionPlan;
import com.example.growthookserver.api.actionplan.dto.request.ActionPlanCreateRequestDto;
import com.example.growthookserver.api.actionplan.dto.request.ActionPlanUpdateRequestDto;
import com.example.growthookserver.api.actionplan.dto.response.ActionPlanCreateResponseDto;
import com.example.growthookserver.api.actionplan.dto.response.ActionPlanGetResponseDto;
import com.example.growthookserver.api.actionplan.repository.ActionPlanRepository;
import com.example.growthookserver.api.actionplan.service.ActionPlanService;
import com.example.growthookserver.api.seed.domain.Seed;
import com.example.growthookserver.api.seed.repository.SeedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<ActionPlanGetResponseDto> getActionPlan(Long seedId) {
        List<ActionPlan> actionPlans = actionPlanRepository.findAllBySeedId(seedId);

        return actionPlans.stream()
                .map(actionPlan -> ActionPlanGetResponseDto.of(actionPlan.getId(), actionPlan.getContent(), actionPlan.getIsScraped(), actionPlan.getIsFinished()))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void updateActionPlan(Long actionPlanId, ActionPlanUpdateRequestDto actionPlanUpdateRequestDto) {
        ActionPlan existingActionPlan = actionPlanRepository.findActionPlanByIdOrThrow(actionPlanId);
        existingActionPlan.updateActionPlan(actionPlanUpdateRequestDto.getContent());
    }
}
