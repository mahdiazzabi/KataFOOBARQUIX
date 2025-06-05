package com.mazzabi.KataFOOBARQUIX.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class FooBarQuixServiceImplTest {

    private FooBarQuixServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new FooBarQuixServiceImpl();
    }

    @Test
    void testTransform1() {
        assertEquals("1", service.transform(1));
    }

    @Test
    void testTransform3() {
        assertEquals("FOOFOO", service.transform(3));
    }

    @Test
    void testTransform5() {
        assertEquals("BARBAR", service.transform(5));
    }

    @Test
    void testTransform7() {
        assertEquals("QUIXQUIX", service.transform(7));
    }

    @Test
    void testTransform9() {
        assertEquals("FOO", service.transform(9));
    }

    @Test
    void testTransform51() {
        assertEquals("FOOBAR", service.transform(51));
    }

    @Test
    void testTransform53() {
        assertEquals("BARFOO", service.transform(53));
    }

    @Test
    void testTransform33() {
        assertEquals("FOOFOOFOO", service.transform(33));
    }

    @Test
    void testTransform15() {
        assertEquals("FOOBARBAR", service.transform(15));
    }
}