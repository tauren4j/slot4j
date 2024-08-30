package com.test.slot4j.service;

import com.test.slot4j.controller.dto.GameSessionRequestDto;
import com.test.slot4j.controller.dto.GameSessionResponseDto;
import com.test.slot4j.dto.SessionDto;
import com.test.slot4j.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GameSessionService {
    private final SessionRepository sessionRepository;
    public GameSessionResponseDto getOrCreateSession(GameSessionRequestDto gameSessionRequestDto) {
        SessionDto.SessionDtoBuilder session = SessionDto.builder()
                .userId(gameSessionRequestDto.getUserId())
                .gameId(gameSessionRequestDto.getGameId());
        Optional<SessionDto> existingSession = sessionRepository.findByUserIdAndGameName(gameSessionRequestDto.getUserId(), gameSessionRequestDto.getGameId());
        if (existingSession.isEmpty()) {
           session.sessionId(UUID.randomUUID());

        }
        SessionDto sessionDto = session.build();
        var savedSession = sessionRepository.save(sessionDto);
        return new GameSessionResponseDto(savedSession.getSessionId());
    }
}
