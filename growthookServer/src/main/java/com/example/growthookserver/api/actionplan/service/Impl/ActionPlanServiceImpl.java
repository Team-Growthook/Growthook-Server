package com.example.growthookserver.api.actionplan.service.Impl;

import com.example.growthookserver.api.actionplan.domain.ActionPlan;
import com.example.growthookserver.api.actionplan.dto.request.ActionPlanCreateRequestDto;
import com.example.growthookserver.api.actionplan.dto.request.ActionPlanUpdateRequestDto;
import com.example.growthookserver.api.actionplan.dto.response.ActionPlanGetResponseDto;
import com.example.growthookserver.api.actionplan.dto.response.DoingActionPlanGetResponseDto;
import com.example.growthookserver.api.actionplan.dto.response.FinishedActionPlanGetResponseDto;
import com.example.growthookserver.api.actionplan.repository.ActionPlanRepository;
import com.example.growthookserver.api.actionplan.service.ActionPlanService;
import com.example.growthookserver.api.member.domain.Member;
import com.example.growthookserver.api.review.repository.ReviewRepository;
import com.example.growthookserver.api.seed.domain.Seed;
import com.example.growthookserver.api.seed.repository.SeedRepository;
import com.example.growthookserver.common.exception.BadRequestException;
import com.example.growthookserver.common.response.ErrorStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ActionPlanServiceImpl implements ActionPlanService {

    private final ActionPlanRepository actionPlanRepository;
    private final SeedRepository seedRepository;
    private final ReviewRepository reviewRepository;

    @Override
    @Transactional
    public void createActionPlan(Long seedId, ActionPlanCreateRequestDto actionPlanCreateRequestDto){
        Seed seed = seedRepository.findSeedByIdOrThrow(seedId);

        List<String> contents = actionPlanCreateRequestDto.getContents();

        contents.stream()
                .map(content -> ActionPlan.builder().content(content).seed(seed).build())
                .forEach(actionPlanRepository::save);
    }

    @Override
    public List<ActionPlanGetResponseDto> getActionPlan(Long seedId) {
        List<ActionPlan> actionPlans = actionPlanRepository.findAllBySeedIdOrderByCreatedAtDesc(seedId);

        return actionPlans.stream()
                .map(actionPlan -> ActionPlanGetResponseDto.of(actionPlan.getId(), actionPlan.getContent(), actionPlan.getIsScraped(), actionPlan.getIsFinished(),reviewRepository.existsByActionPlan(actionPlan)))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void updateActionPlan(Long actionPlanId, ActionPlanUpdateRequestDto actionPlanUpdateRequestDto) {
        ActionPlan existingActionPlan = actionPlanRepository.findActionPlanByIdOrThrow(actionPlanId);
        existingActionPlan.updateActionPlan(actionPlanUpdateRequestDto.getContent());
    }

    @Override
    @Transactional
    public void deleteActionPlan(Long actionPlanId) {
        ActionPlan exisitngActionPlan = actionPlanRepository.findActionPlanByIdOrThrow(actionPlanId);
        actionPlanRepository.delete(exisitngActionPlan);
    }

    @Override
    @Transactional
    public void completeActionPlan(Long actionPlanId) {
        ActionPlan existinActionPlan = actionPlanRepository.findActionPlanByIdOrThrow(actionPlanId);
        if(existinActionPlan.getIsFinished()) {
            throw new BadRequestException(ErrorStatus.ALREADY_COMPLETE_ACTIONPLAN.getMessage());
        }
        existinActionPlan.completeActionPlan(true);

        Member member = existinActionPlan.getSeed().getCave().getMember();
        member.incrementGatheredSsuk();
    }

    @Override
    public int getActionPlanPercent(Long memberId) {
        List<ActionPlan> allActionPlans = actionPlanRepository.findAllByMemberId(memberId);
        long totalActionPlans = allActionPlans.size();

        if(totalActionPlans == 0) {
            return 0;
        }

        long finishedActionPlan = allActionPlans.stream().filter(ActionPlan::getIsFinished).count();
        double ratio = (double) finishedActionPlan / totalActionPlans;
        BigDecimal roundedRatio = BigDecimal.valueOf(ratio * 100.0).setScale(1, RoundingMode.HALF_UP);
        return roundedRatio.intValue();
    }

    @Override
    public List<DoingActionPlanGetResponseDto> getDoingActionPlan(Long memberId) {
        List<ActionPlan> doingActionPlans = actionPlanRepository.findAllBySeedCaveMemberIdAndIsFinishedOrderByCreatedAtDesc(memberId,false);

        return doingActionPlans.stream()
                .map(actionPlan -> DoingActionPlanGetResponseDto.of(actionPlan.getId(), actionPlan.getContent(), actionPlan.getIsScraped(),actionPlan.getSeed().getId(),reviewRepository.existsByActionPlan(actionPlan)))
                .collect(Collectors.toList());
    }

    @Override
    public List<FinishedActionPlanGetResponseDto> getFinishedActionPlan(Long memberId) {
        List<ActionPlan> finishedActionPlans = actionPlanRepository.findAllBySeedCaveMemberIdAndIsFinishedOrderByCreatedAtDesc(memberId,true);

        return finishedActionPlans.stream()
                .map(actionPlan -> FinishedActionPlanGetResponseDto.of(actionPlan.getId(), actionPlan.getContent(), actionPlan.getIsScraped(),actionPlan.getSeed().getId(),reviewRepository.existsByActionPlan(actionPlan)))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void toggleActionPlanScrapStatus(Long actionpalnId) {
        ActionPlan actionPlan = actionPlanRepository.findActionPlanByIdOrThrow(actionpalnId);
        actionPlan.toggleScrapStatus();
    }
}
