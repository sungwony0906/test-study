package com.starcircle.test.assertions;

import static java.time.Duration.ofMillis;
import static java.time.Duration.ofMinutes;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import com.starcircle.test.Calculator;
import com.starcircle.test.Person;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

// assertJ
// 강점 : 메소드 체이닝을 지원하여 연쇄 호출하며 테스트 코드를 작성할 수 있다
// 단점 :
public class AssertJTest {

    private final Calculator calculator = new Calculator();

    private final Person person = new Person("Jane", "Doe");

    @Test
    void standardAssertions() {
        assertThat(2).isEqualTo(calculator.add(1, 1));
        assertThat(4)
                .as("The optional failure message on chaining method")
                .isEqualTo(calculator.multiply(2, 2));
        assertThat('a' < 'b')
                .as("Assertion messages can be lazily evaluated -- "
                                               + "to avoid constructing complex messages unnecessarily.")
                .isFalse();
    }

    @Test
    void groupedAssertions(){
        assertSoftly( softAssertions -> {
            softAssertions.assertThat("Jane").isEqualTo(person.getFirstName());
            softAssertions.assertThat("Doe").isEqualTo(person.getLastName());
        });
    }

    @Test
    void dependentAssertions() {
        assertSoftly( softAssertions -> {
            softAssertions.assertThat(person.getFirstName())
                    .isNotNull()
                    .satisfies(s -> {
                        assertThat(s).startsWith("J");
                        assertThat(s).endsWith("e");
                    });
            softAssertions.assertThat(person.getLastName())
                    .isNotNull()
                    .satisfies(s -> {
                        assertThat(s).startsWith("D");
                        assertThat(s).endsWith("e");
                    });
        });
    }

    @Test
    void exceptionTesting(){
        assertThatThrownBy(() -> calculator.divide(1, 0))
                .isInstanceOf(ArithmeticException.class)
                .hasMessage("/ by zero");

        Throwable thrown = catchThrowable(() -> calculator.divide(1, 0));
        assertThat(thrown)
                .isInstanceOf(ArithmeticException.class)
                .hasMessage("/ by zero");
    }

    @Test
    void timeoutNotExceeded() {
        Future future = Executors.newSingleThreadExecutor()
                .submit(() -> {});
        assertThat(future)
                .succeedsWithin(ofMinutes(2));
    }

    @Test
    void timeoutNotExceededWithResult() {
        Future<String> future = Executors.newSingleThreadExecutor()
                .submit(() -> "a result");
        assertThat(future)
                .succeedsWithin(ofMinutes(2))
                .isEqualTo("a result");
    }

    @Test
    void timeoutNotExceededWithMethod() {
        Future<String> future = Executors.newSingleThreadExecutor()
                .submit(AssertJTest::greeting);
        assertThat(future)
                .succeedsWithin(ofMinutes(2))
                .isEqualTo("Hello, World!");
    }

    @Test
    void timeoutExceeded() {
        Future future = Executors.newSingleThreadExecutor()
                                .submit(() -> {
                                    try {
                                        Thread.sleep(100);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                });
        assertThat(future)
                .succeedsWithin(ofMillis(10));
    }

    @Test
    void timeoutExceededWithPreemptiveTermination() {
        Future future = Executors.newSingleThreadExecutor()
                .submit(() -> {
                    try {
                        new CountDownLatch(1).await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });

        assertThat(future)
                .succeedsWithin(ofMillis(10));
    }

    private static String greeting() {
        return "Hello, World!";
    }


    // asssertJ의 유용함
    @Test
    void filterTest() throws Exception{
        //given
        List<Person> persons = getPersons();

        //when + then
        assertThat(persons)
                .filteredOn("firstName", "sungwon")
                .size()
                .isEqualTo(3);

        assertThat(persons)
                .filteredOn("firstName", "sungwon")
                .filteredOn("lastName", "yun")
                .size()
                .isEqualTo(1);
    }

    private List<Person> getPersons(){
        return List.of(new Person("sungwon", "yun")
                , new Person("sungwon", "kim")
                , new Person("sungwon", "park")
                , new Person("sungho", "yun"));
    }
}