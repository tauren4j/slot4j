package com.test.slot4j.dto;

import com.test.slot4j.core.BetLine;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
public class SlotRequestDto {
    private UUID sessionId;
    private long userId;
    private long bet;
    private Set<BetLine> betLines;
}
