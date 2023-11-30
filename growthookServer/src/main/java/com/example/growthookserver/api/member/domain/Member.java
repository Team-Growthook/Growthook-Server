package com.example.growthookserver.api.member.domain;

import com.example.growthookserver.api.member.auth.SocialPlatform;
import com.example.growthookserver.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nickname;

    @Column
    private String email;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "social_platform")
    private SocialPlatform socialPlatform;

    @Column(name = "is_new_member")
    private Boolean isNewMember;

    @Column(name = "used_ssuk")
    private Integer usedSsuk;

    @Column(name = "gathered_ssuk")
    private Integer gatheredSsuk;

    @Column(nullable = false, name = "social_id")
    private String socialId;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Builder
    public Member(String nickname, String email, SocialPlatform socialPlatform, Boolean isNewMember, Integer usedSsuk, Integer gatheredSsuk) {
        this.nickname = nickname;
        this.email = email;
        this.socialPlatform = socialPlatform;
        this.isNewMember = isNewMember;
        this.usedSsuk = usedSsuk;
        this.gatheredSsuk = gatheredSsuk;
    }
}
