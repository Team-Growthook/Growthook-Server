package com.example.growthookserver.api.member.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SocialInfoDto {
    private String id;
    private String nickname;
    private String email;
    private String profileImage;
}