package com.example.growthookserver.api.member.auth.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class AuthTokenResponseDto {
    private String accessToken;

    private String refreshToken;
}
