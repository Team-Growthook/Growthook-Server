package com.example.growthookserver.api.cave.domain;

import com.example.growthookserver.api.member.domain.Member;
import com.example.growthookserver.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cave extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String introduction;

    @Column(name = "is_shared")
    private Boolean isShared;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Cave(String name, String introduction, Boolean isShared, Member member) {
        this.name = name;
        this.introduction = introduction;
        this.isShared = isShared;
        this.member = member;
    }

    public void updateCave(String newName, String newIntroduction, Boolean newIsShared) {
        this.name = newName;
        this.introduction = newIntroduction;
        this.isShared = newIsShared;
    }
}
