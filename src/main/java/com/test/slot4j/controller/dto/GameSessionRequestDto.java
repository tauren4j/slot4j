package com.test.slot4j.controller.dto;

import lombok.Data;

@Data
public class GameSessionRequestDto {
    private long userId;
    private String gameId;
}
