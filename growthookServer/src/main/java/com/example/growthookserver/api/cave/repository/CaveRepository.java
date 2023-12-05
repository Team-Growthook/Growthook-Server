package com.example.growthookserver.api.cave.repository;

import com.example.growthookserver.api.cave.domain.Cave;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CaveRepository extends JpaRepository<Cave, Long> {
    List<Cave> findAllByMemberId(Long memberId);

    Optional<Cave> findCaveById(Long caveId);
}
