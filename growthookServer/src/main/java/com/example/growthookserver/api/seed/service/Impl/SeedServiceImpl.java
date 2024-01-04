package com.example.growthookserver.api.seed.service.Impl;

import com.example.growthookserver.api.cave.domain.Cave;
import com.example.growthookserver.api.cave.repository.CaveRepository;
import com.example.growthookserver.api.seed.domain.Seed;
import com.example.growthookserver.api.seed.dto.request.SeedCreateRequestDto;
import com.example.growthookserver.api.seed.dto.request.SeedMoveRequestDto;
import com.example.growthookserver.api.seed.dto.request.SeedUpdateRequestDto;
import com.example.growthookserver.api.seed.dto.response.SeedAlarmGetResponseDto;
import com.example.growthookserver.api.seed.dto.response.SeedCreateResponseDto;
import com.example.growthookserver.api.seed.dto.response.SeedDetailGetResponseDto;
import com.example.growthookserver.api.seed.dto.response.SeedMoveResponseDto;
import com.example.growthookserver.api.seed.repository.SeedRepository;
import com.example.growthookserver.api.seed.service.SeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SeedServiceImpl implements SeedService {

  private final CaveRepository caveRepository;
  private final SeedRepository seedRepository;

  @Override
  @Transactional
  public SeedCreateResponseDto createSeed(Long caveId, SeedCreateRequestDto seedCreateRequestDto) {
    Cave cave = caveRepository.findCaveByIdOrThrow(caveId);
    Seed seed = Seed.builder()
        .cave(cave)
        .memo(seedCreateRequestDto.getMemo())
        .url(seedCreateRequestDto.getUrl())
        .insight(seedCreateRequestDto.getInsight())
        .source(seedCreateRequestDto.getSource())
        .goalMonth(seedCreateRequestDto.getGoalMonth())
        .build();
    Seed savedSeed = seedRepository.save(seed);
    return SeedCreateResponseDto.of(savedSeed.getId());
  }

  @Override
  @Transactional
  public void deleteSeed(Long seedId) {
    Seed seed = seedRepository.findSeedByIdOrThrow(seedId);
    seedRepository.delete(seed);
  }

  @Override
  @Transactional
  public void updateSeed(Long seedId, SeedUpdateRequestDto seedUpdateRequestDto) {
    Seed existingSeed = seedRepository.findSeedByIdOrThrow(seedId);
    existingSeed.updateSeed(seedUpdateRequestDto.getInsight(), seedUpdateRequestDto.getMemo(),
            seedUpdateRequestDto.getSource(), seedUpdateRequestDto.getUrl());
  }

  @Override
  public SeedDetailGetResponseDto getSeedDetail(Long seedId) {
    Seed seed = seedRepository.findSeedByIdOrThrow(seedId);
    LocalDate lockDate = seed.getLockDate();
    LocalDate currentDate = LocalDate.now();
    long remainingDays = currentDate.until(lockDate, ChronoUnit.DAYS);
    return SeedDetailGetResponseDto.of(seed.getCave().getName(), seed.getInsight(), seed.getMemo(), seed.getSource(),
            seed.getUrl(), seed.getIsScraped(), lockDate.toString(), remainingDays);
  }

  @Override
  @Transactional
  public SeedMoveResponseDto moveSeed(Long seedId, SeedMoveRequestDto seedMoveRequestDto) {
    Cave targetCave = caveRepository.findCaveByIdOrThrow(seedMoveRequestDto.getCaveId());
    Seed seed = seedRepository.findSeedByIdOrThrow(seedId);
    seed.changeCave(targetCave);
    return SeedMoveResponseDto.of(seed.getCave().getId(), seed.getCave().getName());
  }

  @Override
  public SeedAlarmGetResponseDto getSeedAlarm(Long memberId) {
    LocalDate now = LocalDate.now();
    LocalDate threeDaysLater = now.plusDays(3);

    List<Seed> seeds = seedRepository.findByCave_MemberIdAndLockDateBetween(memberId, now, threeDaysLater);

    if(seeds.isEmpty()) {
      return SeedAlarmGetResponseDto.of(0);
    }

    int seedCount = seeds.size();

    return SeedAlarmGetResponseDto.of(seedCount);
  }
}
