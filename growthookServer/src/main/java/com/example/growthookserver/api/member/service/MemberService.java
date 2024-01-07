package com.example.growthookserver.api.member.service;

import com.example.growthookserver.api.member.dto.response.MemberDetailGetResponseDto;
import com.example.growthookserver.api.member.dto.response.MemberUsedSsukGetResponseDto;

public interface MemberService {
  //* 멤버 프로필 정보 조회
  MemberDetailGetResponseDto getMemberProfile(Long memberId);

  //* 회원 탈퇴
  void deleteMember(Long memberId);

  //* 사용한 쑥의 개수 조회
  MemberUsedSsukGetResponseDto getUsedSsuk(Long memberId);
}
