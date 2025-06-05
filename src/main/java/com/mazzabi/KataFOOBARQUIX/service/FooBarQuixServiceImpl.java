package com.mazzabi.KataFOOBARQUIX.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FooBarQuixServiceImpl implements IFooBarQuixService {

    public String transform(int number) {
        String numberStr = String.valueOf(number);
        String joinResult = getStringByDivisibleRule(number) + getStringByContainDigitRule(numberStr);
        return joinResult.isEmpty() ? numberStr : joinResult;
    }

    private static String getStringByContainDigitRule(String numberStr) {
        return numberStr.chars()
                .mapToObj(c -> (char) c)
                .flatMap(character ->
                        Arrays.stream(Rule.values())
                                .map(rule -> rule.applyDigit(character))
                                .flatMap(Optional::stream)
                )
                .collect(Collectors.joining());
    }

    private static String getStringByDivisibleRule(int number) {
        return Arrays.stream(Rule.values())
                .map(rule -> rule.applyDiv(number))
                .flatMap(Optional::stream)
                .collect(Collectors.joining());
    }
}
