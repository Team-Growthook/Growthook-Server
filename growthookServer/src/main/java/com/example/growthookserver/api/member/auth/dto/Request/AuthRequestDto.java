package com.example.growthookserver.api.member.auth.dto.Request;

import static lombok.AccessLevel.PROTECTED;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = PROTECTED)
public class AuthRequestDto {
    private String socialPlatform;
    private String socialToken;
}