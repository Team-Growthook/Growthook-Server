package com.example.growthookserver.api.seed.domain;

import com.example.growthookserver.api.actionplan.domain.ActionPlan;
import com.example.growthookserver.api.cave.domain.Cave;
import com.example.growthookserver.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Seed extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String insight;

    private String memo;

    private String source;

    private String url;

    @Column(name = "lock_date")
    private LocalDate lockDate;

    @Column(name = "is_scraped")
    private Boolean isScraped;

    @Column(name = "is_locked")
    private Boolean isLocked;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cave_id")
    private Cave cave;

    @OneToMany(mappedBy = "seed",cascade = ALL)
    private List<ActionPlan> actionPlans = new ArrayList<>();

    @Builder
    public Seed(String insight, String memo, String source, String url, Integer goalMonth, Cave cave) {
        this.insight = insight;
        this.memo = memo;
        this.source = source;
        this.url = url;
        this.lockDate = LocalDate.now().plusDays(goalMonth * 30);
        this.isScraped = false;
        this.isLocked = false;
        this.cave = cave;
    }

    public void updateSeed(String newInsight, String newMemo, String newSource, String newUrl) {
        this.insight = newInsight;
        this.memo = newMemo;
        this.source = newSource;
        this.url = newUrl;
    }

    public void changeCave(Cave newCave) {
        this.cave = newCave;
    }

    public void toggleScrapStatus() { this.isScraped = !this.isScraped; }
}
