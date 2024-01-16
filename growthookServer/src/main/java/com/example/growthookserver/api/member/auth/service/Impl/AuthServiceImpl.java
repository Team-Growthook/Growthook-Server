package com.example.growthookserver.api.member.auth.service.Impl;

import com.example.growthookserver.api.member.auth.SocialPlatform;
import com.example.growthookserver.api.member.auth.dto.Request.AuthRequestDto;
import com.example.growthookserver.api.member.auth.dto.Response.AuthResponseDto;
import com.example.growthookserver.api.member.auth.dto.Response.AuthTokenResponseDto;

import com.example.growthookserver.api.member.auth.dto.SocialInfoDto;
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

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final JwtTokenProvider jwtTokenProvider;
    private final KakaoAuthService kakaoAuthService;
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public AuthResponseDto socialLogin(AuthRequestDto authRequestDto) throws NoSuchAlgorithmException, InvalidKeySpecException {

        if (authRequestDto.getSocialPlatform() == null || authRequestDto.getSocialToken() == null) {
            throw new BadRequestException(ErrorStatus.VALIDATION_REQUEST_MISSING_EXCEPTION.getMessage());
        }

        try {
            SocialPlatform socialPlatform = SocialPlatform.valueOf(authRequestDto.getSocialPlatform());

            SocialInfoDto socialData = getSocialData(socialPlatform, authRequestDto.getSocialToken());

            String refreshToken = jwtTokenProvider.generateRefreshToken();

            Boolean isExistUser = isMemberBySocialId(socialData.getId());

            // 신규 유저 저장
            if (!isExistUser.booleanValue()) {
                Member member = Member.builder()
                    .nickname(socialData.getNickname())
                    .email(socialData.getEmail())
                    .socialPlatform(socialPlatform)
                    .socialId(socialData.getId())
                    .build();

                memberRepository.save(member);

                member.updateRefreshToken(refreshToken);
            }
            else findMemberBySocialId(socialData.getId()).updateRefreshToken(refreshToken);

            // socialId를 통해서 등록된 유저 찾기
            Member signedMember = findMemberBySocialId(socialData.getId());

            Authentication authentication = new UserAuthentication(signedMember.getId(), null, null);

            String accessToken = jwtTokenProvider.generateAccessToken(authentication);

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

    private Member findMemberBySocialId(String socialId) {
        return memberRepository.findBySocialId(socialId)
                .orElseThrow(() -> new BadRequestException(ErrorStatus.INVALID_MEMBER.getMessage()));
    }

    private boolean isMemberBySocialId(String socialId) {
        return memberRepository.existsBySocialId(socialId);
    }

    private SocialInfoDto getSocialData(SocialPlatform socialPlatform, String socialAccessToken) throws NoSuchAlgorithmException, InvalidKeySpecException {

        switch (socialPlatform) {
            case KAKAO:
                return kakaoAuthService.login(socialAccessToken);
            default:
                throw new IllegalArgumentException(ErrorStatus.ANOTHER_ACCESS_TOKEN.getMessage());
        }
    }
}