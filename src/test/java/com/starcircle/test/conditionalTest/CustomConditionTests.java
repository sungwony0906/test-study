package com.starcircle.test.conditionalTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIf;
import org.junit.jupiter.api.condition.EnabledIf;

public class CustomConditionTests {
    @Test
    @EnabledIf("customCondition")
    void enabled() {
        System.out.println("enabled");
    }

    @Test
    @DisabledIf("customCondition")
    void disabled() {
        System.out.println("disabled");
    }

    boolean customCondition() {
        return true;
    }
}
