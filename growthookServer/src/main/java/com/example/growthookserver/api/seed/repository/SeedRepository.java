package com.example.growthookserver.api.seed.repository;

import com.example.growthookserver.api.seed.domain.Seed;
import com.example.growthookserver.common.exception.NotFoundException;
import com.example.growthookserver.common.response.ErrorStatus;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SeedRepository extends JpaRepository<Seed, Long> {
  Optional<Seed> findSeedById(Long seedId);
  List<Seed> findByCaveIdOrderByIdDesc(Long caveId);

  List<Seed> findByMemberIdOrderByIdDesc(Long memberId);

  default Seed findSeedByIdOrThrow(Long seedId) {
    return findSeedById(seedId)
        .orElseThrow(()-> new NotFoundException(ErrorStatus.NOT_FOUND_SEED.getMessage()));
  }
  List<Seed> findByCave_MemberIdAndLockDateBetween(Long memberId, LocalDate now, LocalDate threeDaysLater);

  @Modifying(clearAutomatically=true)
  @Query("UPDATE Seed s SET s.isLocked = true WHERE s.lockDate < CURRENT_DATE AND s.isLocked = false")
  int updateLockStatusForExpiredSeeds();
}
