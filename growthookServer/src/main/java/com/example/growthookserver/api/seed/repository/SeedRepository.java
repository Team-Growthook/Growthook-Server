package com.example.growthookserver.api.seed.repository;

import com.example.growthookserver.api.cave.domain.Cave;
import com.example.growthookserver.api.seed.domain.Seed;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeedRepository extends JpaRepository<Seed, Long> {
}
