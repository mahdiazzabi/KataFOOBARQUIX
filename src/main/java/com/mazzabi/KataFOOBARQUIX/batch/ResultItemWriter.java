package com.mazzabi.KataFOOBARQUIX.batch;

import org.springframework.batch.item.*;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class ResultItemWriter implements ItemWriter<String>, ItemStream {

    private final Path outputPath;
    private BufferedWriter writer;

    public ResultItemWriter(Path outputPath) {
        this.outputPath = outputPath;
    }

    @Override
    public void open(ExecutionContext executionContext) throws ItemStreamException {
        try {
            Files.createDirectories(outputPath.getParent());
            this.writer = Files.newBufferedWriter(outputPath, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new ItemStreamException("Failed to open output file: " + outputPath, e);
        }
    }

    @Override
    public void write(Chunk<? extends String> items) throws Exception {
        for (String item : items) {
            writer.write(item);
            writer.newLine();
        }
        writer.flush();
    }

    @Override
    public void update(ExecutionContext executionContext) throws ItemStreamException {
        // Pas nécessaire ici, mais doit être implémenté
    }

    @Override
    public void close() throws ItemStreamException {
        if (writer != null) {
            try {
                writer.close();
            } catch (IOException e) {
                throw new ItemStreamException("Failed to close output file: " + outputPath, e);
            }
        }
    }
}
