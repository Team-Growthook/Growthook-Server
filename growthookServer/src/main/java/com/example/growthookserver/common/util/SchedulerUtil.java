package com.example.growthookserver.common.util;

import com.example.growthookserver.api.seed.repository.SeedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class SchedulerUtil {

  private final SeedRepository seedRepository;
  @Scheduled(cron = "0 0 0 * * *", zone = "Asia/Seoul")
  @Transactional
  public void updateLockStatusForExpiredSeeds() {
    int updatedCount = seedRepository.updateLockStatusForExpiredSeeds();
    System.out.println("Updated " + updatedCount + " seeds.");
  }
}
