package com.example.growthookserver.api.actionplan.domain;

import com.example.growthookserver.api.review.domain.Review;
import com.example.growthookserver.api.seed.domain.Seed;
import com.example.growthookserver.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;

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

    @OneToMany(mappedBy = "actionPlan",cascade = ALL)
    private List<Review> reviews = new ArrayList<>();

    @Builder
    public ActionPlan(String content, Seed seed) {
        this.content = content;
        this.isScraped = false;
        this.isFinished = false;
        this.seed = seed;
    }

    public void updateActionPlan(String newContent) {
        this.content = newContent;
    }

    public void completeActionPlan(Boolean newIsFinished) {
        this.isFinished = newIsFinished;
    }
}
