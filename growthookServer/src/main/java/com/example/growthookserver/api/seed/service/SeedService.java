package com.example.growthookserver.api.seed.service;

import com.example.growthookserver.api.seed.dto.request.SeedCreateRequestDto;
import com.example.growthookserver.api.seed.dto.request.SeedMoveRequestDto;
import com.example.growthookserver.api.seed.dto.request.SeedUpdateRequestDto;
import com.example.growthookserver.api.seed.dto.response.SeedAlarmGetResponseDto;
import com.example.growthookserver.api.seed.dto.response.SeedCreateResponseDto;
import com.example.growthookserver.api.seed.dto.response.SeedDetailGetResponseDto;
import com.example.growthookserver.api.seed.dto.response.SeedListGetResponseDto;
import com.example.growthookserver.api.seed.dto.response.SeedMoveResponseDto;
import java.util.List;

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

  //* 보관함별 씨앗 리스트 조회
  List<SeedListGetResponseDto> getSeedListByCave(Long caveId);

  //* 씨앗 전체 리스트 조회
  List<SeedListGetResponseDto> getSeedList(Long memberId);

  //* 씨앗 스크랩 상태 변경
  void toggleSeedScrapStatus(Long seedId);

  //* 씨앗 알림 조회
  SeedAlarmGetResponseDto getSeedAlarm(Long memberId);

  //* 씨앗 잠금 해제
  void unlockSeed(Long seedId);
}
