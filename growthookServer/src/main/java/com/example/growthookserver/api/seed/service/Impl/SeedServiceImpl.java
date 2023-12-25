package com.example.growthookserver.api.seed.service.Impl;

import com.example.growthookserver.api.cave.domain.Cave;
import com.example.growthookserver.api.cave.repository.CaveRepository;
import com.example.growthookserver.api.seed.domain.Seed;
import com.example.growthookserver.api.seed.dto.request.SeedCreateRequestDto;
import com.example.growthookserver.api.seed.dto.request.SeedUpdateRequestDto;
import com.example.growthookserver.api.seed.dto.response.SeedCreateResponseDto;
import com.example.growthookserver.api.seed.dto.response.SeedDetailGetResponseDto;
import com.example.growthookserver.api.seed.repository.SeedRepository;
import com.example.growthookserver.api.seed.service.SeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

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

}
