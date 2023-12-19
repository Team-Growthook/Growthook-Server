package com.example.growthookserver.api.seed.repository;

import com.example.growthookserver.api.cave.domain.Cave;
import com.example.growthookserver.api.seed.domain.Seed;
import com.example.growthookserver.common.exception.NotFoundException;
import com.example.growthookserver.common.response.ErrorStatus;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeedRepository extends JpaRepository<Seed, Long> {
  Optional<Seed> findSeedById(Long seedId);

  default Seed findSeedByIdOrThrow(Long seedId) {
    return findSeedById(seedId)
        .orElseThrow(()-> new NotFoundException(ErrorStatus.NOT_FOUND_SEED.getMessage()));
  }
}
