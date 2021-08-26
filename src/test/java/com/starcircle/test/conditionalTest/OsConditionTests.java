package com.starcircle.test.conditionalTest;

import static org.junit.jupiter.api.condition.OS.LINUX;
import static org.junit.jupiter.api.condition.OS.MAC;
import static org.junit.jupiter.api.condition.OS.WINDOWS;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledOnOs;

public class OsConditionTests {

    @Test
    @EnabledOnOs(MAC)
    void onlyOnMacOs(){
        System.out.println("test only on mac os");
    }

    @TestOnMac
    void testOnMac(){
        System.out.println("custom annotation for test on mac");
    }

    @Test
    @EnabledOnOs({LINUX, MAC})
    void onLinuxOrMac(){
        System.out.println("test on linux or mac");
    }

    @Test
    @DisabledOnOs(WINDOWS)
    void notOnWindows(){
        System.out.println("test disabled on window");
    }

    @Test
    @EnabledOnOs(WINDOWS)
    void onWindows(){
        System.out.println("test on window");
    }

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Test
    @EnabledOnOs(MAC)
    @interface TestOnMac {
    }
}
