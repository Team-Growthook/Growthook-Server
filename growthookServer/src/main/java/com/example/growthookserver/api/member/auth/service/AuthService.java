package com.example.growthookserver.api.member.auth.service;

import com.example.growthookserver.api.member.auth.dto.Request.AuthRequestDto;
import com.example.growthookserver.api.member.auth.dto.Response.AuthResponseDto;
import com.example.growthookserver.api.member.auth.dto.Response.AuthTokenResponseDto;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public interface AuthService {
    AuthResponseDto socialLogin(AuthRequestDto authRequestDto) throws NoSuchAlgorithmException, InvalidKeySpecException;

    AuthTokenResponseDto getNewToken(String accessToken, String refreshToken);
}
