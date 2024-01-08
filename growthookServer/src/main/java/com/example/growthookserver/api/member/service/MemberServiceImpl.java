package com.example.growthookserver.api.member.service;

import com.example.growthookserver.api.member.domain.Member;
import com.example.growthookserver.api.member.dto.response.MemberDetailGetResponseDto;
import com.example.growthookserver.api.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService{

  private final MemberRepository memberRepository;

  @Override
  public MemberDetailGetResponseDto getMemberProfile(Long memberId) {
    Member member = memberRepository.findMemberByIdOrThrow(memberId);
    return MemberDetailGetResponseDto.of(member.getNickname(), member.getEmail());
  }

  @Override
  @Transactional
  public void deleteMember(Long memberId) {
    Member member = memberRepository.findMemberByIdOrThrow(memberId);
    memberRepository.delete(member);
  }
}
