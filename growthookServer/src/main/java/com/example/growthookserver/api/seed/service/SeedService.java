package com.example.growthookserver.api.seed.service;

import com.example.growthookserver.api.cave.dto.request.CaveCreateRequestDto;
import com.example.growthookserver.api.cave.dto.response.CaveCreateResponseDto;
import com.example.growthookserver.api.seed.dto.request.SeedCreateRequestDto;
import com.example.growthookserver.api.seed.dto.request.SeedMoveRequestDto;
import com.example.growthookserver.api.seed.dto.request.SeedUpdateRequestDto;
import com.example.growthookserver.api.seed.dto.response.SeedAlarmGetResponseDto;
import com.example.growthookserver.api.seed.dto.response.SeedCreateResponseDto;
import com.example.growthookserver.api.seed.dto.response.SeedDetailGetResponseDto;
import com.example.growthookserver.api.seed.dto.response.SeedMoveResponseDto;

import java.time.LocalDate;

public interface SeedService {
  //* 씨앗 생성
  SeedCreateResponseDto createSeed(Long caveId, SeedCreateRequestDto seedCreateRequestDto);

  //* 씨앗 삭제
  void deleteSeed(Long seedId);

  //* 씨앗 수정
  void updateSeed(Long seedId, SeedUpdateRequestDto seedUpdateRequestDto);

  //* 씨앗 상세 정보 조회
  SeedDetailGetResponseDto getSeedDetail(Long seedId);

  //* 씨앗 이동
  SeedMoveResponseDto moveSeed(Long seedId, SeedMoveRequestDto seedMoveRequestDto);

  //* 씨앗 알림 조회
  SeedAlarmGetResponseDto getSeedAlarm(Long memberId);
}
