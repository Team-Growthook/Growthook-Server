package com.example.growthookserver.common.util;

import com.example.growthookserver.api.seed.repository.SeedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@EnableScheduling
@RequiredArgsConstructor
public class SeedScheduler {

  private final SeedRepository seedRepository;
  @Scheduled(cron = "0 0 0 * * *", zone = "Asia/Seoul")
  @Transactional
  public void updateLockStatusForExpiredSeeds() {
    int updatedCount = seedRepository.updateLockStatusForExpiredSeeds();
  }
}
