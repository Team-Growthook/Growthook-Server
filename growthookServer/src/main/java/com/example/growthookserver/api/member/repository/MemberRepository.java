package com.example.growthookserver.api.member.repository;

import com.example.growthookserver.api.member.domain.Member;
import com.example.growthookserver.common.exception.BadRequestException;
import com.example.growthookserver.common.exception.NotFoundException;
import com.example.growthookserver.common.exception.UnAuthorizedException;
import com.example.growthookserver.common.response.ErrorStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findMemberById(Long id);

    boolean existsBySocialId(String socialId);
    Optional<Member> findByRefreshToken(String refreshToken);

    Optional<Member> findBySocialId(String socialId);

    default Member findMemberByIdOrThrow(Long memberId){
        return findMemberById(memberId)
            .orElseThrow(() -> new NotFoundException(ErrorStatus.NOT_FOUND_MEMBER.getMessage()));
    }

    default Member findMemberBySocialIdOrThrow(String socialId) {
        return findBySocialId(socialId)
            .orElseThrow(() -> new BadRequestException(ErrorStatus.INVALID_MEMBER.getMessage()));
    }

    default Member findByRefreshTokenOrThrow(String refreshToken) {
        return findByRefreshToken(refreshToken)
            .orElseThrow(() -> new UnAuthorizedException(ErrorStatus.INVALID_MEMBER.getMessage()));
    }
}
