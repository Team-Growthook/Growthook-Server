package com.example.growthookserver.api.member.auth.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class AuthResponseDto {

    private String nickname;

    private Long memberId;

    private String accessToken;

    private String refreshToken;

}
