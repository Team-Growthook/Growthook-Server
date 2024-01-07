package com.example.growthookserver.api.member.service;

import com.example.growthookserver.api.member.dto.response.MemberDetailGetResponseDto;

public interface MemberService {
  //* 멤버 프로필 정보 조회
  MemberDetailGetResponseDto getMemberProfile(Long memberId);

}
