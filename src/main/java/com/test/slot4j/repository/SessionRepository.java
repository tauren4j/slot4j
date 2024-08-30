package com.test.slot4j.repository;

import com.test.slot4j.dto.SessionDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SessionRepository extends JpaRepository<SessionDto, UUID> {

    Optional<SessionDto> findByUserIdAndGameName(Long userId, String gameId);
}
