package com.starcircle.test.testInterfaces;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

public interface TestInterfaceDynamicTestsDemo {

    @TestFactory
    default Stream<DynamicTest> dynamicTestsForPalindromes() {
        return Stream.of("racecar", "radar", "mom", "dad")
                       .map(text -> dynamicTest(text, () -> assertTrue(isPalindrome(text))));
    }

    default boolean isPalindrome(String text){
        return StringUtils.reverse(text).equals(text);
    }
}
