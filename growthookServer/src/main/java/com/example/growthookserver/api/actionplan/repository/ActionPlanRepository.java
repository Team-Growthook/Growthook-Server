package com.example.growthookserver.api.actionplan.repository;

import com.example.growthookserver.api.actionplan.domain.ActionPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActionPlanRepository extends JpaRepository<ActionPlan,Long> {
    List<ActionPlan> findAllBySeedId(Long seedId);
}
