package com.test.slot4j.core;

public enum Symbol {
    A(0.1),
    K(0.15),
    Q(0.2),
    J(0.25),
    TEN(0.25),
    WILD(0.05);


    private final double probability;

    Symbol(double probability) {
        this.probability = probability;
    }

    public double getProbability() {
        return probability;
    }
}
