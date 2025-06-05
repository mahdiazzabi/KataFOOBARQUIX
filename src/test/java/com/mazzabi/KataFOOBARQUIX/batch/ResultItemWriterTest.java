package com.mazzabi.KataFOOBARQUIX.batch;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.batch.item.Chunk;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ResultItemWriterTest {
    private static Path tempFile;

    @BeforeEach
    void setUp() throws IOException {
        tempFile = Files.createTempFile("foobarquix-test-output", ".txt");
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(tempFile);
    }

    @Test
    void testWriteLines() throws Exception {
        var writer = new ResultItemWriter(tempFile);
        writer.write(Chunk.of("FOO", "BAR", "QUIX"));
        writer.close();

        List<String> output = Files.readAllLines(tempFile);
        assertEquals(List.of("FOO", "BAR", "QUIX"), output);
    }
}