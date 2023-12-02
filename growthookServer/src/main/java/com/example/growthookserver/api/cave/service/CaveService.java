package com.example.growthookserver.api.cave.service;

import com.example.growthookserver.api.cave.dto.request.CaveCreateRequestDto;
import com.example.growthookserver.api.cave.dto.response.CaveCreateResponseDto;

public interface CaveService {
    //* 동굴 생성
    CaveCreateResponseDto createCave(Long memberId, CaveCreateRequestDto caveCreateRequestDto);
}
