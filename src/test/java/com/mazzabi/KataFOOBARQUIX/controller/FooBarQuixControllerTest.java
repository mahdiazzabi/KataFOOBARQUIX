package com.mazzabi.KataFOOBARQUIX.controller;

import com.mazzabi.KataFOOBARQUIX.service.IFooBarQuixService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FooBarQuixControllerTest {

    @Mock
    IFooBarQuixService transformerService;

    @InjectMocks
    FooBarQuixController fooBarQuixController;

    @Test
    void test_transform_200() {
        when(transformerService.transform(15)).thenReturn("FOOBAR");

        ResponseEntity<String> result = fooBarQuixController.transform(15);

        assertEquals("FOOBAR", result.getBody());
        verify(transformerService).transform(15);
    }

    @Test
    void test_transform_BAD_REQUEST() {
        ResponseEntity<String> result = fooBarQuixController.transform(101);

        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

}