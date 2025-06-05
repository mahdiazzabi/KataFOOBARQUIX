package com.mazzabi.KataFOOBARQUIX.batch;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NumberItemReaderTest {
    @Test
    void testReadAllLines() throws Exception {
        var resource = new ClassPathResource("input-test.txt");
        var reader = new NumberItemReader(resource);

        List<String> lines = new ArrayList<>();
        String line;
        while ((line = reader.read()) != null) {
            lines.add(line);
        }

        assertEquals(List.of("1", "3", "5", "7"), lines);
    }
}