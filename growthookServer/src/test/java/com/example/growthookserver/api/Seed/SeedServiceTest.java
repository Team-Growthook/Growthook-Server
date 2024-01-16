package com.example.growthookserver.api.Seed;

import com.example.growthookserver.api.seed.repository.SeedRepository;
import com.example.growthookserver.api.seed.service.SeedService;
import java.util.concurrent.CountDownLatch;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class SeedServiceTest implements SchedulingConfigurer {

  @Mock
  private SeedRepository seedRepository;

  @Value("${schedules.cron.reward.publish}")
  private String cronExpression;

  @InjectMocks
  private SeedService seedService;

  private CountDownLatch latch = new CountDownLatch(1);

  @Test
  void testScheduledUpdateLockStatus() throws InterruptedException {
    // 현재 시스템 시간을 사용
    Clock systemClock = Clock.systemDefaultZone();

    // 특정 시간을 기준으로 시계를 고정 (예: 2024-01-17T00:00:00Z)
    Instant fixedInstant = Instant.parse("2024-01-17T00:00:00Z");
    Clock fixedClock = Clock.fixed(fixedInstant, ZoneId.of("UTC"));

    // SeedService 생성 시 가짜 Clock 주입
    seedService = new SeedService(systemClock);

    // configureTasks 메서드 직접 호출
    configureTasks(new ScheduledTaskRegistrar(), seedService);

    // 리포지토리 메소드의 동작을 모킹
    when(seedRepository.updateLockStatusForExpiredSeeds()).thenReturn(5);

    // 매일 정각에 동작하도록 설정된 스케줄링이 정상적으로 동작하는지 확인
    latch.await(); // 테스트가 통과하려면 특정 조건에서 latch.countDown()을 호출해야 함
    verify(seedRepository).updateLockStatusForExpiredSeeds();
  }

  @Override
  public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
    // 크론 표현식을 사용하여 스케줄된 작업을 구성
    taskRegistrar.addCronTask(() -> {
      seedService.updateLockStatusForExpiredSeeds();
      latch.countDown(); // 테스트를 위해 latch를 감소시킴
    }, cronExpression);
  }
}
