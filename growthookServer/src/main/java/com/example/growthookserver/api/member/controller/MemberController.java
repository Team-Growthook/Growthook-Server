package com.example.growthookserver.api.member.controller;

import com.example.growthookserver.api.member.dto.response.MemberDetailGetResponseDto;
import com.example.growthookserver.api.member.service.MemberService;
import com.example.growthookserver.common.response.ApiResponse;
import com.example.growthookserver.common.response.SuccessStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
@Tag(name = "Member - 유저 관련 API", description = "Member API Document")
public class MemberController {

  private final MemberService memberService;

  @GetMapping("/member/{memberId}/profile")
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "MemberProfileGet", description = "멤버 프로필을 조회하는 API입니다.")
  public ApiResponse<MemberDetailGetResponseDto> getMemberProfile(@PathVariable("memberId") Long memberId) {
    return ApiResponse.success(SuccessStatus.GET_MEMBER_PROFILE, memberService.getMemberProfile(memberId));
  }
}
