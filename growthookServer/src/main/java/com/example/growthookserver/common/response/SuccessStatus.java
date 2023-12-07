package com.example.growthookserver.common.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public enum SuccessStatus {

    /**
     * auth
     */
    SIGNUP_SUCCESS(HttpStatus.CREATED, "회원가입 성공"),
    SIGNIN_SUCCESS(HttpStatus.OK, "로그인 성공"),
    GET_NEW_TOKEN_SUCCESS(HttpStatus.OK,"토큰 재발급 성공"),

    /**
     * cave
     */
    POST_CAVE_SUCCESS(HttpStatus.CREATED,"동굴 생성 성공"),
    GET_CAVE_ALL(HttpStatus.OK,"동굴 목록 조회 성공"),
    PATCH_CAVE_SUCCESS(HttpStatus.OK,"동굴 수정 성공"),
    GET_CAVE_DETAIL(HttpStatus.OK,"동굴 상세 정보 조회 성공")
    ;

    private final HttpStatus httpStatus;
    private final String message;

    public int getStatusCode() {
        return this.httpStatus.value();
    }
}
