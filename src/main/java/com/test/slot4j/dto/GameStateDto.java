package com.test.slot4j.dto;

import com.test.slot4j.core.BetLine;
import lombok.Builder;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
@Builder
public class GameStateDto {
    private UUID id;
    private UUID sessionId;
    private Set<BetLine> betLines;
    private String[][] reels;
    private double bet;
    private double winAmount;
}
