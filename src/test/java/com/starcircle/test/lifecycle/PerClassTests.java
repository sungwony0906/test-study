package com.starcircle.test.lifecycle;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
public class PerClassTests {

    String test = "test1";

    @BeforeAll
    static void initAll(){
        System.out.println("before All");
    }

    @BeforeEach
    void init(){
        System.out.println("==============================");
        if ("test1".equals(test)) {
            System.out.println("test is test1");
            test = "test2";
        }
    }

    @Test
    @Order(1)
    void firstTest(){
        System.out.println("first test : "+test);
    }

    @Test
    @Order(2)
    void secondTest(){
        System.out.println("second test : "+test);
        test = "test100";
    }

    @Test
    @Order(3)
    void thirdTest(){
        System.out.println("third test : "+test);
    }

    @AfterEach
    void tearDown(){
        System.out.println("==============================");
    }

    @AfterAll
    static void tearDownAll(){
        System.out.println("after All");
    }
}
