package com.test.slot4j.service;

import com.test.slot4j.dto.BalanceUpdateRequestDto;
import com.test.slot4j.dto.BalanceUpdateResponseDto;
import com.test.slot4j.dto.UserDto;
import com.test.slot4j.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BalanceService {
    private static final String URL = "balance.update.url";
    private final RestTemplate restTemplate;
    private final UserRepository userRepository;


    public ResponseEntity<BalanceUpdateResponseDto> updateBalance(long userId, double bet, double winAmount) {
        Optional<UserDto> userDtoOptional = userRepository.findById(userId);

        if (userDtoOptional.isPresent()) {
            UserDto userDto = userDtoOptional.get();
            double balanceUpdateAmount = winAmount - bet;
            String secretKey = userDto.getSecretKey();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + secretKey);


            BalanceUpdateRequestDto requestDto = new BalanceUpdateRequestDto(balanceUpdateAmount);

            HttpEntity<BalanceUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto, headers);

            return restTemplate.exchange(
                    URL,
                    HttpMethod.POST,
                    requestEntity,
                    BalanceUpdateResponseDto.class
            );
        }
        //todo implement retry/fallback/logging logic
        else {
            throw new RuntimeException();
        }
    }
}
