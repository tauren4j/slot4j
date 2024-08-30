package com.test.slot4j.service;

import com.test.slot4j.core.BetLine;
import com.test.slot4j.core.ReelsGenerator;
import com.test.slot4j.dto.BalanceUpdateResponseDto;
import com.test.slot4j.dto.GameStateDto;
import com.test.slot4j.dto.SlotRequestDto;
import com.test.slot4j.dto.SlotResponseDto;
import com.test.slot4j.repository.GameStateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SlotService {
    private final ReelsGenerator reelsGenerator;
    private final BalanceService balanceService;
    private final GameStateRepository gameStateRepository;


    @Transactional
    public SlotResponseDto spin(SlotRequestDto slotRequestDto) {
        UUID sessionId = slotRequestDto.getSessionId();
        long userId = slotRequestDto.getUserId();
        long bet = slotRequestDto.getBet();
        Set<BetLine> betLines = slotRequestDto.getBetLines();
        String[][] reels = reelsGenerator.generateReels();

        double winAmount = calculateWin(reels, betLines, bet);
        BalanceUpdateResponseDto balanceUpdateResponseDto = balanceService.updateBalance(userId, bet, winAmount).getBody();
        if (balanceUpdateResponseDto != null &&  balanceUpdateResponseDto.isSuccess()) {
            GameStateDto gameStateDto = GameStateDto.builder()
                    .sessionId(sessionId)
                    .bet(bet)
                    .betLines(betLines)
                    .reels(reels)
                    .winAmount(winAmount)
                    .build();
            gameStateRepository.save(gameStateDto);
        }
        return SlotResponseDto.builder()
                .reels(reels)
                .winAmount(winAmount)
                .build();
    }

    //todo implement calculation logic
    private double calculateWin(String[][] reels, Set<BetLine> betLines, double bet) {
        return 0.0;
    }
}
