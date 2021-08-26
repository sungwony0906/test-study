package com.starcircle.test.conditionalTest;

import static org.junit.jupiter.api.condition.JRE.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledForJreRange;
import org.junit.jupiter.api.condition.DisabledOnJre;
import org.junit.jupiter.api.condition.EnabledForJreRange;
import org.junit.jupiter.api.condition.EnabledOnJre;

public class JreConditionTEsts {
    @Test
    @EnabledOnJre(JAVA_8)
    void onlyOnJava8() {
        System.out.println("test only on java 8");
    }

    @Test
    @EnabledOnJre({ JAVA_9, JAVA_10 })
    void onJava9Or10() {
        System.out.println("test on java 9 or java 10");
    }

    @Test
    @EnabledForJreRange(min = JAVA_9, max = JAVA_11)
    void fromJava9to11() {
        System.out.println("test enable from java 9 to java 11");
    }

    @Test
    @EnabledForJreRange(min = JAVA_9)
    void fromJava9toCurrentJavaFeatureNumber() {
        System.out.println("test enable from java 9");
    }

    @Test
    @EnabledForJreRange(max = JAVA_11)
    void fromJava8To11() {
        System.out.println("test enable to java 11");
    }

    @Test
    @DisabledOnJre(JAVA_9)
    void notOnJava9() {
        System.out.println("test disable on java 9");
    }

    @Test
    @DisabledForJreRange(min = JAVA_9, max = JAVA_11)
    void notFromJava9to11() {
        System.out.println("test disable from java 9 to java 11");
    }

    @Test
    @DisabledForJreRange(min = JAVA_9)
    void notFromJava9toCurrentJavaFeatureNumber() {
        System.out.println("test disable from java 9");
    }

    @Test
    @DisabledForJreRange(max = JAVA_11)
    void notFromJava8to11() {
        System.out.println("test disable to java 11");
    }
}
