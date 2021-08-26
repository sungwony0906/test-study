package com.starcircle.test.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.util.ObjectUtils;

@TestMethodOrder(OrderAnnotation.class)
public class OrderedTests {

    @Test
    void noOrderTest1(){
        System.out.println("no order test - 1");
    }

    @Test
    @Order(3)
    void validValues(){
        String result = regist("something");
        assertEquals("regist success", result);
    }

    @Test
    @Order(2)
    void emptyValues(){
        assertThrows(IllegalArgumentException.class, () -> regist(""));
    }

    @Test
    void noOrderTest2(){
        System.out.println("no order test - 2");
    }

    @Test
    @Order(1)
    void nullValues(){
        assertThrows(IllegalArgumentException.class, () -> regist(null));
    }

    @Test
    void noOrderTest3(){
        System.out.println("no order test - 3");
    }

    private String regist(String value){
        if(ObjectUtils.isEmpty(value)){
            throw new IllegalArgumentException();
        }
        return "regist success";
    }
}
