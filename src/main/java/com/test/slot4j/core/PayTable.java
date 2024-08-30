package com.test.slot4j.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PayTable {
    private static final Map<List<Symbol>, Double> payTableMap = new HashMap<>();

    static {
        payTableMap.put(List.of(Symbol.TEN, Symbol.TEN, Symbol.TEN, Symbol.TEN), 10d);
    }

    public static double getPayment(BetLine betLine) {
        return payTableMap.getOrDefault(betLine.getSymbols(), 0d);
    }
}
