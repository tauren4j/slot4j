package com.test.slot4j.core;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.stream.IntStream;

@Service
public class ReelsGenerator {

    private static final int ROWS = 3;
    private static final int COLUMNS = 3;
    private static final SecureRandom random = new SecureRandom();

    public String[][] generateReels() {
        return IntStream.range(0, ROWS)
                .mapToObj(row -> IntStream.range(0, COLUMNS)
                        .mapToObj(col -> getRandomSymbolBasedOnProbability().name())
                        .toArray(String[]::new))
                .toArray(String[][]::new);
    }

    private Symbol getRandomSymbolBasedOnProbability() {
        double randomValue = random.nextDouble();
        double cumulativeProbability = 0.0;

        for (Symbol symbol : Symbol.values()) {
            cumulativeProbability += symbol.getProbability();
            if (randomValue <= cumulativeProbability) {
                return symbol;
            }
        }
        throw new IllegalStateException("Incorrect probabilities");
    }
}
