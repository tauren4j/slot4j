package com.test.slot4j.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class SessionDto {
    private UUID sessionId;
    private long userId;
    private GameStateDto gameStateDto;
}
