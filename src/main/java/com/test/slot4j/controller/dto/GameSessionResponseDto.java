package com.test.slot4j.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class GameSessionResponseDto {
    private UUID id;
}
