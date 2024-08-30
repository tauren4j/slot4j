package com.test.slot4j.repository;

import com.test.slot4j.dto.GameStateDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GameStateRepository extends JpaRepository<GameStateDto, UUID> {
}
