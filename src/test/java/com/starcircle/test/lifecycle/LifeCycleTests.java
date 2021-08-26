package com.starcircle.test.lifecycle;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class LifeCycleTests {

    @BeforeAll
    static void initAll(){
        System.out.println("before All");
    }

    @BeforeEach
    void init(){
        System.out.println("before Each");
    }

    @Test
    void succeedingTest(){
        System.out.println("succeeding");
    }

    @Test
    void failingTest(){
        System.out.println("failing");
        fail("a failing test");
    }

    @Test
    @Disabled("for demonstration purposes")
    void skippedTest() {
        System.out.println("not executed");
    }

    @Test
    void abortedTest(){
        System.out.println("aborted test");
        assumeTrue("abc".contains("Z"));
        fail("test should have been aborted");
    }

    @AfterEach
    void tearDown(){
        System.out.println("after Each");
    }

    @AfterAll
    static void tearDownAll(){
        System.out.println("after All");
    }
}
