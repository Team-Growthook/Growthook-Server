package com.example.growthookserver.api.actionplan.repository;

import com.example.growthookserver.api.actionplan.domain.ActionPlan;
import com.example.growthookserver.common.exception.NotFoundException;
import com.example.growthookserver.common.response.ErrorStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ActionPlanRepository extends JpaRepository<ActionPlan,Long> {
    List<ActionPlan> findAllBySeedId(Long seedId);

    Optional<ActionPlan> findActionPlanById(Long actionPlanId);

    default ActionPlan findActionPlanByIdOrThrow(Long actionPlanId) {
        return findActionPlanById(actionPlanId)
                .orElseThrow(() -> new NotFoundException(ErrorStatus.NOT_FOUND_ACTIONPLAN.getMessage()));
    }
}
