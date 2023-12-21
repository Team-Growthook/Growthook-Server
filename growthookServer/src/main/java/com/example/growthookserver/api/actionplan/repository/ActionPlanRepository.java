package com.example.growthookserver.api.actionplan.repository;

import com.example.growthookserver.api.actionplan.domain.ActionPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActionPlanRepository extends JpaRepository<ActionPlan,Long> {
}
