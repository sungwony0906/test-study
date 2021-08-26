package com.starcircle.test.assumptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

import com.starcircle.test.Calculator;
import org.junit.jupiter.api.Test;

public class AssumptionsTests {

    private final Calculator calculator = new Calculator();

    @Test
    void testOnlyOnCiServer(){
        assumeTrue("CI".equals(System.getenv("ENV")));
    }

    @Test
    void testOnlyOnDeveloperWorkstation(){
        assumeTrue("DEV".equals(System.getenv("ENV")),
                () -> "Aborting test: not on develop workstation");
    }

    @Test
    void testInAllEnvironments() {
        assumingThat("CI".equals(System.getenv("ENV")),
                () -> {
                    // perform these assertions only on the CI server
                    assertEquals(2, calculator.divide(4, 2));
                });

        // perform these assertions in all environments
        assertEquals(42, calculator.multiply(6, 7));
    }
}
