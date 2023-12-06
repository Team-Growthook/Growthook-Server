package com.example.growthookserver.api.cave.service;

import com.example.growthookserver.api.cave.dto.request.CaveCreateRequestDto;
import com.example.growthookserver.api.cave.dto.response.CaveAllResponseDto;
import com.example.growthookserver.api.cave.dto.response.CaveCreateResponseDto;

import java.util.List;

public interface CaveService {
    //* 동굴 생성
    CaveCreateResponseDto createCave(Long memberId, CaveCreateRequestDto caveCreateRequestDto);

    //* 동굴 리스트 조회
    List<CaveAllResponseDto> getCaveAll(Long memberId);
}