package com.test.slot4j.controller;

import com.test.slot4j.controller.dto.GameSessionRequestDto;
import com.test.slot4j.controller.dto.GameSessionResponseDto;
import com.test.slot4j.service.GameSessionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/sessions")
public class SessionController {
    private final GameSessionService gameSessionService;

    public SessionController(GameSessionService gameSessionService) {
        this.gameSessionService = gameSessionService;
    }

    @PostMapping
    public ResponseEntity<GameSessionResponseDto> getOrCreateSession(@Valid @RequestBody GameSessionRequestDto gameSessionRequestDto) {
        GameSessionResponseDto session = gameSessionService.getOrCreateSession(gameSessionRequestDto);
        return new ResponseEntity<>(session, HttpStatus.CREATED);
    }
}
