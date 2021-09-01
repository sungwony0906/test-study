package com.starcircle.test.assertions;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.starcircle.test.Calculator;
import com.starcircle.test.Person;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

public class HamcrestTest {

    private final Calculator calculator = new Calculator();

    private final Person person = new Person("Jane", "Doe");

    @Test
    void standardAssertions() {
        assertThat(2, equalTo(calculator.add(1, 1)));
        assertThat("The optional failure message is now the first parameter",4, equalTo(calculator.multiply(2, 2)));
        assertThat("Assertion messages can be lazily evaluated -- "
                           + "to avoid constructing complex messages unnecessarily.",
                'a', is(lessThan('b')));
    }

    @Test
    void groupedAssertions() {
        // assertion을 그룹짓는 매커니즘은 없는 것 같다
        // 객체 그래프 탐색이 용이한 것 같고 assert grouping 등은 JUnit을 활용해 조합하는 것이 바람직 해 보인다
        assertThat("person", person,
                allOf(hasProperty("firstName", equalTo("Jane")),
                        hasProperty("lastName", equalTo("Doe"))));
    }

    @Test
    void dependentAssertions(){
        assertAll("properties",
                () -> {
                    assertThat(person.getFirstName(),
                            Matchers.allOf(
                                    is(notNullValue()),
                                    startsWith("J"),
                                    endsWith("e")
                                )
                            );
                },
                () -> {
                    assertThat(person.getLastName(),
                            Matchers.allOf(
                                    is(notNullValue()),
                                    startsWith("D"),
                                    endsWith("e")
                            )
                    );
                }
        );
    }
}
