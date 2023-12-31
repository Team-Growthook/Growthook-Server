package com.example.growthookserver.api.cave.service;

import com.example.growthookserver.api.cave.dto.request.CaveCreateRequestDto;
import com.example.growthookserver.api.cave.dto.request.CaveUpdateRequestDto;
import com.example.growthookserver.api.cave.dto.response.CaveAllResponseDto;
import com.example.growthookserver.api.cave.dto.response.CaveCreateResponseDto;
import com.example.growthookserver.api.cave.dto.response.CaveDetailGetResponseDto;

import java.util.List;

public interface CaveService {
    //* 동굴 생성
    CaveCreateResponseDto createCave(Long memberId, CaveCreateRequestDto caveCreateRequestDto);

    //* 동굴 리스트 조회
    List<CaveAllResponseDto> getCaveAll(Long memberId);

    //* 동굴 업데이트
    void updateCave(Long caveId, CaveUpdateRequestDto caveUpdateRequestDto);

    //* 동굴 정보 조회
    CaveDetailGetResponseDto getCaveDetail(Long memberId, Long caveId);

    //* 동굴 삭제
    void deleteCave(Long caveId);
}