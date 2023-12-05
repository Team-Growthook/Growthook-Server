package com.example.growthookserver.api.cave.service.Impl;

import com.example.growthookserver.api.cave.domain.Cave;
import com.example.growthookserver.api.cave.dto.request.CaveCreateRequestDto;
import com.example.growthookserver.api.cave.dto.request.CaveUpdateRequestDto;
import com.example.growthookserver.api.cave.dto.response.CaveAllResponseDto;
import com.example.growthookserver.api.cave.dto.response.CaveCreateResponseDto;
import com.example.growthookserver.api.cave.dto.response.CaveDetailGetResponseDto;
import com.example.growthookserver.api.cave.repository.CaveRepository;
import com.example.growthookserver.api.cave.service.CaveService;
import com.example.growthookserver.api.member.domain.Member;
import com.example.growthookserver.api.member.repository.MemberRepository;
import com.example.growthookserver.common.exception.NotFoundException;
import com.example.growthookserver.common.response.ErrorStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    @Transactional
    public List<CaveAllResponseDto> getCaveAll(Long memberId){
        List<Cave> caves = caveRepository.findAllByMemberId(memberId);

        if(caves.isEmpty()) {
            throw new NotFoundException(ErrorStatus.NOT_FOUND_MEMBER_CAVE.getMessage());
        }

        return caves.stream()
                .map(cave -> CaveAllResponseDto.of(cave.getId(), cave.getName()))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void updateCave(Long caveId, CaveUpdateRequestDto caveUpdateRequestDto){
        Cave existingCave = caveRepository.findCaveById(caveId);
        existingCave.updateCave(caveUpdateRequestDto.getName(), caveUpdateRequestDto.getIntroduction(), caveUpdateRequestDto.getIsShared());
    }

    @Override
    @Transactional
    public CaveDetailGetResponseDto getCaveDetail(Long memberId, Long caveId){
        Member member = findMemberById(memberId);
        Cave cave = caveRepository.findCaveById(caveId);

        return CaveDetailGetResponseDto.of(cave.getName(), cave.getIntroduction(), member.getNickname(), cave.getIsShared());
    }

    private Member findMemberById(Long memberId){
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException(ErrorStatus.NOT_FOUND_MEMBER.getMessage()));
    }
}
