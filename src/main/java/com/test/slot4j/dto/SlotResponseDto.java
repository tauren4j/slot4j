package com.test.slot4j.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SlotResponseDto {
    private String[][] reels;
    private double winAmount;
}
