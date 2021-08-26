package com.starcircle.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class MyFirstJupiterTests {

    private final Calculator calculator = new Calculator();

    @Test
    void addition(){
        assertEquals(2, calculator.add(1,1));
    }

    // junit-jupiter-params에 존재
    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, -3, 15})
    void parameterizedTest(int number) {
        System.out.println("parameter : "+number);
        assertTrue(Math.abs(number) % 2 == 1);
    }

    @RepeatedTest(3)
    void repeatedTest(RepetitionInfo repetitionInfo){
        System.out.println("Repetition #" + repetitionInfo.getCurrentRepetition());
        assertEquals(3, repetitionInfo.getTotalRepetitions());
    }

    //DynamicTest
    // Test generated during runtime
    @TestFactory
    Collection<DynamicTest> dynamicTestCollection() {
        return Arrays.asList(
                DynamicTest.dynamicTest("Add test", () -> assertEquals(2, Math.addExact(1, 1))),
                DynamicTest.dynamicTest("Multiply Test", () -> assertEquals(4, Math.multiplyExact(2, 2)))
        );
    }

    // parameterized test - 동일 메소드를 다른 파라미터로 실행
    // TestTemplate - 동일 메소드를 다른 실행 환경으로 실행
    // 즉, 다른 설정의 결합으로 동일 메소드를 여러번 실행
    // - 파라미터 변경
    // - dependency injection 변경
    // - 환경이 "QA"인 경우 일부 호출 사용/사용 안 함과 같은 다른 조건에서 테스트 실행

    @TestFactory
    Stream<DynamicTest> dynamicTestsExample() {
        List<Integer> input1List = Arrays.asList(1,2,3);
        List<Integer> input2List = Arrays.asList(10,20,30);

        List<DynamicTest> dynamicTests = new ArrayList<>();
        Calculator calculator = new Calculator();

        for(int i=0; i < input1List.size(); i++) {
            int x = input1List.get(i);
            int y = input2List.get(i);
            DynamicTest dynamicTest = DynamicTest.dynamicTest("Dynamic Test for MyUtils.add("+x+","+y+")",
                    () ->{
                assertEquals(x+y, calculator.add(x,y));
            });
            dynamicTests.add(dynamicTest);
        }

        return dynamicTests.stream();
    }
}
