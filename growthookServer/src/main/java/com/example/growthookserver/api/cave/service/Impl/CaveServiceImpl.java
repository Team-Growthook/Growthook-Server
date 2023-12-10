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
@Transactional(readOnly = true)
public class CaveServiceImpl implements CaveService {
    private final MemberRepository memberRepository;
    private final CaveRepository caveRepository;

    @Override
    @Transactional
    public CaveCreateResponseDto createCave(Long memberId, CaveCreateRequestDto caveCreateRequestDto){
        Member member = memberRepository.findMemberByIdOrThrow(memberId);
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
    public List<CaveAllResponseDto> getCaveAll(Long memberId){
        List<Cave> caves = caveRepository.findAllByMemberId(memberId);

        return caves.stream()
                .map(cave -> CaveAllResponseDto.of(cave.getId(), cave.getName()))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void updateCave(Long caveId, CaveUpdateRequestDto caveUpdateRequestDto){
        Cave existingCave = caveRepository.findCaveByIdOrThrow(caveId);
        existingCave.updateCave(caveUpdateRequestDto.getName(), caveUpdateRequestDto.getIntroduction(), caveUpdateRequestDto.getIsShared());
    }

    @Override
    public CaveDetailGetResponseDto getCaveDetail(Long memberId, Long caveId){
        Member member = memberRepository.findMemberByIdOrThrow(memberId);
        Cave cave = caveRepository.findCaveByIdOrThrow(caveId);

        return CaveDetailGetResponseDto.of(cave.getName(), cave.getIntroduction(), member.getNickname(), cave.getIsShared());
    }

    @Override
    @Transactional
    public void deleteCave(Long caveId) {
        Cave cave = caveRepository.findCaveByIdOrThrow(caveId);
        caveRepository.delete(cave);
    }

}
