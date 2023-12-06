package com.example.growthookserver.api.cave.repository;

import com.example.growthookserver.api.cave.domain.Cave;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CaveRepository extends JpaRepository<Cave, Long> {
    List<Cave> findAllByMemberId(Long memberId);
}
