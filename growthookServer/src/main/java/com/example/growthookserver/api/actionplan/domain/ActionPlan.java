package com.example.growthookserver.api.actionplan.domain;

import com.example.growthookserver.api.seed.domain.Seed;
import com.example.growthookserver.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ActionPlan extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @Column(name = "is_scraped")
    private Boolean isScraped;

    @Column(name = "is_finished")
    private Boolean isFinished;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seed_id")
    private Seed seed;

    @Builder
    public ActionPlan(String content, Boolean isScraped, Boolean isFinished, Seed seed) {
        this.content = content;
        this.isScraped = isScraped;
        this.isFinished = isFinished;
        this.seed = seed;
    }
}
