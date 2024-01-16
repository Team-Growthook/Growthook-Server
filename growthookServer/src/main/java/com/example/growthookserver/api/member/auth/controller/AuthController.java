package com.example.growthookserver.api.member.auth.controller;

import com.example.growthookserver.api.member.auth.dto.Request.AuthRequestDto;
import com.example.growthookserver.api.member.auth.dto.Response.AuthResponseDto;
import com.example.growthookserver.api.member.auth.dto.Response.AuthTokenResponseDto;
import com.example.growthookserver.api.member.auth.service.AuthService;
import com.example.growthookserver.common.config.jwt.JwtTokenProvider;
import com.example.growthookserver.common.response.ApiResponse;
import com.example.growthookserver.common.response.SuccessStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Auth - 인증/인가 관련 API", description = "Auth API Document")
public class AuthController {
    private final AuthService authService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "SocialLogin", description = "소셜 로그인 API입니다.")
    public ApiResponse<AuthResponseDto> socialLogin(@RequestBody AuthRequestDto authRequestDto) throws NoSuchAlgorithmException, InvalidKeySpecException {

        AuthResponseDto responseDto = authService.socialLogin(authRequestDto);
        return ApiResponse.success(SuccessStatus.SIGNIN_SUCCESS, responseDto);

    }

    @GetMapping("/token")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "TokenRefresh", description = "토큰 재발급 API입니다.")
    public ApiResponse<AuthTokenResponseDto> getNewToken(HttpServletRequest request) {
        String accessToken = (String) request.getAttribute("newAccessToken");
        String refreshToken = jwtTokenProvider.resolveRefreshToken(request);

        return ApiResponse.success(SuccessStatus.GET_NEW_TOKEN_SUCCESS, authService.getNewToken(accessToken, refreshToken));
    }
}