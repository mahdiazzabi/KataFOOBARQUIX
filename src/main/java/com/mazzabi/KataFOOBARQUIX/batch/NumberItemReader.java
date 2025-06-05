package com.mazzabi.KataFOOBARQUIX.batch;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStream;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class NumberItemReader implements ItemReader<String>, ItemStream {

    private final Resource resource;
    private BufferedReader reader;

    public NumberItemReader(Resource resource) {
        this.resource = resource;
    }

    @Override
    public String read() throws Exception {
        if (reader == null) {
            throw new IllegalStateException("Reader not open. Call open() before reading.");
        }
        String line = reader.readLine();
        return (line != null && !line.isBlank()) ? line.trim() : null;
    }

    @Override
    public void open(ExecutionContext executionContext) throws ItemStreamException {
        try {
            this.reader = new BufferedReader(new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new ItemStreamException("Failed to open input resource: " + resource.getDescription(), e);
        }
    }

    @Override
    public void update(ExecutionContext executionContext) throws ItemStreamException {
        // Pas besoin d’implémenter pour ce cas simple
    }

    @Override
    public void close() throws ItemStreamException {
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException e) {
                throw new ItemStreamException("Failed to close input resource: " + resource.getDescription(), e);
            }
        }
    }
}
