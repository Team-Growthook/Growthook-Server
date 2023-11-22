package com.example.growthookserver.api.review.domain;

import com.example.growthookserver.api.actionplan.domain.ActionPlan;
import com.example.growthookserver.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "actionplan_id")
    private ActionPlan actionPlan;

    @Builder
    public Review(String content, ActionPlan actionPlan) {
        this.content = content;
        this.actionPlan = actionPlan;
    }
}
