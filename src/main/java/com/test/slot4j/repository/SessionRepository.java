package com.test.slot4j.repository;

import com.test.slot4j.dto.SessionDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SessionRepository extends JpaRepository<SessionDto, UUID> {
}
