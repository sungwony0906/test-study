package com.starcircle.test.annotations;

import org.junit.jupiter.api.Test;

public class CustomAnnotationTests {

    @Fast
    @Test
    void fast(){
        System.out.println("hihi");
    }

    @FastTest
    void fastTest(){
        System.out.println("hihi22");
    }
}
