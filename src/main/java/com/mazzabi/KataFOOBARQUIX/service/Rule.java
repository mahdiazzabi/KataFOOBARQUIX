package com.mazzabi.KataFOOBARQUIX.service;

import java.util.Optional;

public enum Rule {
    FOO_DIV(3, null, "FOO"),
    BAR_DIV(5, null, "BAR"),
    QUIX_DIV(7, null, "QUIX"),
    FOO_DIGIT(null, '3', "FOO"),
    BAR_DIGIT(null, '5', "BAR"),
    QUIX_DIGIT(null, '7', "QUIX");

    private final Integer divisibleBy;
    private final Character digit;
    private final String replacement;

    Rule(Integer divisibleBy, Character digit, String replacement) {
        this.divisibleBy = divisibleBy;
        this.digit = digit;
        this.replacement = replacement;
    }

    public Optional<String> applyDiv(int number) {
        return divisibleBy != null && number % divisibleBy == 0 ? Optional.of(replacement) : Optional.empty();
    }

    public Optional<String> applyDigit(char c) {
        return digit != null && digit == c ? Optional.of(replacement) : Optional.empty();
    }
}
