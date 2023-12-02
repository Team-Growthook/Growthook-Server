package com.example.growthookserver.api.cave.repository;

import com.example.growthookserver.api.cave.domain.Cave;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CaveRepository extends JpaRepository<Cave, Long> {
}
