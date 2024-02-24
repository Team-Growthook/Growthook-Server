package com.example.growthookserver.api.member.auth.service.Impl;

import com.example.growthookserver.api.member.auth.SocialPlatform;
import com.example.growthookserver.api.member.auth.dto.Request.AuthRequestDto;
import com.example.growthookserver.api.member.auth.dto.Response.AuthResponseDto;
import com.example.growthookserver.api.member.auth.dto.Response.AuthTokenResponseDto;

import com.example.growthookserver.api.member.auth.dto.SocialInfoDto;
import com.example.growthookserver.api.member.auth.service.AppleAuthService;
import com.example.growthookserver.api.member.auth.service.AuthService;
import com.example.growthookserver.api.member.auth.service.KakaoAuthService;

import com.example.growthookserver.api.member.domain.Member;
import com.example.growthookserver.api.member.repository.MemberRepository;
import com.example.growthookserver.common.config.jwt.JwtTokenProvider;
import com.example.growthookserver.common.config.jwt.UserAuthentication;
import com.example.growthookserver.common.exception.BadRequestException;
import com.example.growthookserver.common.response.ErrorStatus;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import com.example.growthookserver.external.slack.service.SlackService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final JwtTokenProvider jwtTokenProvider;
    private final KakaoAuthService kakaoAuthService;
    private final AppleAuthService appleAuthService;
    private final MemberRepository memberRepository;
    private final SlackService slackService;

    @Override
    @Transactional
    public AuthResponseDto socialLogin(AuthRequestDto authRequestDto) throws NoSuchAlgorithmException, InvalidKeySpecException {

        if (authRequestDto.getSocialPlatform() == null || authRequestDto.getSocialToken() == null) {
            throw new BadRequestException(ErrorStatus.VALIDATION_REQUEST_MISSING_EXCEPTION.getMessage());
        }

        try {
            SocialPlatform socialPlatform = SocialPlatform.valueOf(authRequestDto.getSocialPlatform());

            SocialInfoDto socialData = getSocialData(socialPlatform, authRequestDto.getSocialToken(),
                authRequestDto.getUserName());

            String refreshToken = jwtTokenProvider.generateRefreshToken();

            Boolean isExistUser = memberRepository.existsBySocialId(socialData.getId());

            // 신규 유저 저장
            if (!isExistUser.booleanValue()) {
                Member member = Member.builder()
                    .nickname(socialData.getNickname())
                    .email(socialData.getEmail())
                    .socialPlatform(socialPlatform)
                    .socialId(socialData.getId())
                    .profileImage(socialData.getProfileImage())
                    .build();

                memberRepository.save(member);

                Long memberCount = memberRepository.count();
                slackService.sendSlackMessage(socialData.getNickname(), memberCount, "#gth_signup");

                member.updateRefreshToken(refreshToken);
            }
            else memberRepository.findMemberBySocialIdOrThrow(socialData.getId()).updateRefreshToken(refreshToken);

            // socialId를 통해서 등록된 유저 찾기
            Member signedMember = memberRepository.findMemberBySocialIdOrThrow(socialData.getId());

            String accessToken = jwtTokenProvider.generateAccessToken(signedMember.getId());

            return AuthResponseDto.of(signedMember.getNickname(), signedMember.getId(), accessToken, signedMember.getRefreshToken());

        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException(ErrorStatus.ANOTHER_ACCESS_TOKEN.getMessage());
        }
    }

    @Override
    @Transactional
    public AuthTokenResponseDto getNewToken(String accessToken, String refreshToken) {
        return AuthTokenResponseDto.of(accessToken,refreshToken);
    }

    private SocialInfoDto getSocialData(SocialPlatform socialPlatform, String socialAccessToken, String userName) {

        switch (socialPlatform) {
            case KAKAO:
                return kakaoAuthService.login(socialAccessToken);
            case APPLE:
                return appleAuthService.login(socialAccessToken, userName);
            default:
                throw new IllegalArgumentException(ErrorStatus.ANOTHER_ACCESS_TOKEN.getMessage());
        }
    }
}