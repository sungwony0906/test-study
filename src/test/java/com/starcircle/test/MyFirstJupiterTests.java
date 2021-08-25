package com.starcircle.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MyFirstJupiterTests {

    private final Calculator calculator = new Calculator();

    @Test
    void addition(){
        assertEquals(2, calculator.add(1,1));
    }
}
