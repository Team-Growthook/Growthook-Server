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
    GET_CAVE_DETAIL(HttpStatus.OK,"동굴 상세 정보 조회 성공"),
    DELETE_CAVE(HttpStatus.OK,"동글 삭제 완료"),

    /**
     * seed
     */
    POST_SEED_SUCCESS(HttpStatus.CREATED, "씨앗 생성 성공"),
    DELETE_SEED(HttpStatus.OK, "씨앗 삭제 성공"),
    PATCH_SEED_SUCCESS(HttpStatus.OK, "씨앗 수정 성공"),
    GET_SEED_DETAIL(HttpStatus.OK, "씨앗 상세 정보 조회 성공"),
    MOVE_SEED_SUCCESS(HttpStatus.OK, "씨앗 이동 성공"),
    GET_SEED_LIST_BY_CAVE(HttpStatus.OK, "보관함별로 씨앗 리스트 조회 성공"),
    GET_SEED_LIST(HttpStatus.OK, "전체 씨앗 리스트 조회 성공" ),
    TOGGLE_SEED_SCRAP_STATUS(HttpStatus.OK, "씨앗 스크랩 여부 토글 전환 성공"),
    GET_SEED_ALARM(HttpStatus.OK,"씨앗 알람 조회 성공"),
    UNLOCK_SEED(HttpStatus.OK, "씨앗 잠금 해제 성공"),

    /**
     * actionplan
     */
    POST_ACTIONPLAN_SUCCESS(HttpStatus.CREATED, "액션 플랜 생성 성공"),
    GET_SEED_ACTIONPLAN_SUCCESS(HttpStatus.OK, "씨앗 별 액션 플랜 조회 성공"),
    PATCH_ACTIONPLAN_SUCCESS(HttpStatus.OK, "액션 플랜 수정 성공"),
    DELETE_ACTIONPLAN_SUCCESS(HttpStatus.OK,"액션 플랜 삭제 성공"),
    COMPLETE_ACTIONPLAN_SUCCESS(HttpStatus.OK, "액션 플랜 완료하기 성공"),
    GET_FINISHED_ACTIONPLAN_PERCENT(HttpStatus.OK, "완료한 액션 플랜 퍼센트 조회 성공"),
    GET_DOING_ACTIONPLAN_SUCCESS(HttpStatus.OK, "진행 중인 액션 플랜 리스트 조회 성공"),
    GET_FINISHED_ACTIONPLAN_SUCCESS(HttpStatus.OK,"완료한 액션 플랜 리스트 조회 성공"),

    /**
     * review
     */
    POST_REVIEW_SUCCESS(HttpStatus.CREATED, "리뷰 생성 성공"),
    GET_REVIEW_DETAIL(HttpStatus.OK, "리뷰 내용 상세 조회 성공"),

    /**
     * member
     */
    GET_MEMBER_PROFILE(HttpStatus.OK, "멤버 프로필 정보 조회 성공"),
    ;

    private final HttpStatus httpStatus;
    private final String message;

    public int getStatusCode() {
        return this.httpStatus.value();
    }
}
