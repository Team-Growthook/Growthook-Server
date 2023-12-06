package com.example.growthookserver.api.cave.service.Impl;

import com.example.growthookserver.api.cave.domain.Cave;
import com.example.growthookserver.api.cave.dto.request.CaveCreateRequestDto;
import com.example.growthookserver.api.cave.dto.response.CaveCreateResponseDto;
import com.example.growthookserver.api.cave.repository.CaveRepository;
import com.example.growthookserver.api.cave.service.CaveService;
import com.example.growthookserver.api.member.domain.Member;
import com.example.growthookserver.api.member.repository.MemberRepository;
import com.example.growthookserver.common.exception.NotFoundException;
import com.example.growthookserver.common.response.ErrorStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CaveServiceImpl implements CaveService {
    private final MemberRepository memberRepository;
    private final CaveRepository caveRepository;

    @Override
    @Transactional
    public CaveCreateResponseDto createCave(Long memberId, CaveCreateRequestDto caveCreateRequestDto){
        Member member = findMemberById(memberId);
        Cave cave = Cave.builder()
                .name(caveCreateRequestDto.getName())
                .introduction(caveCreateRequestDto.getIntroduction())
                .isShared(caveCreateRequestDto.getIsShared())
                .member(member)
                .build();
        Cave savedCave = caveRepository.save(cave);
        return CaveCreateResponseDto.of(savedCave.getId());
    }

    private Member findMemberById(Long memberId){
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException(ErrorStatus.NOT_FOUND_MEMBER.getMessage()));
    }
}
