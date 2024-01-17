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

    @Column
    private String profileImage;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "social_platform")
    private SocialPlatform socialPlatform;

    @Column(name = "used_ssuk")
    private Integer usedSsuk;

    @Column(name = "gathered_ssuk")
    private Integer gatheredSsuk;

    @Column(nullable = false, name = "social_id")
    private String socialId;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Builder
    public Member(String nickname, String email, SocialPlatform socialPlatform, String socialId, String profileImage) {
        this.nickname = nickname;
        this.email = email;
        this.socialPlatform = socialPlatform;
        this.socialId = socialId;
        this.profileImage = profileImage;
        this.usedSsuk = 0;
        this.gatheredSsuk = 0;
    }

    public void incrementGatheredSsuk() {
        this.gatheredSsuk = (this.gatheredSsuk == null ? 0 : this.gatheredSsuk) + 1;
    }

    public void useSsuck() {
        this.gatheredSsuk--;
        this.usedSsuk++;
    }

    public void updateRefreshToken (String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
