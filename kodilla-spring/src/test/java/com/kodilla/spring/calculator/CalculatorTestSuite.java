package com.kodilla.spring.calculator;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CalculatorTestSuite {

    @Test
    public void testCalculations() {
        //Given
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext("com.kodilla.spring");
        Calculator calculator = (Calculator)applicationContext.getBean("calculator");
        double a = 28.0;
        double b = 8.0;
        //When
        double addResult = calculator.add(a, b);
        double subResult = calculator.sub(a, b);
        double mulResult = calculator.mul(a, b);
        double divResult = calculator.div(a, b);
        //Then
        Assert.assertEquals(a + b, addResult, 0);
        Assert.assertEquals(a - b, subResult, 0);
        Assert.assertEquals(a * b, mulResult, 0);
        Assert.assertEquals(a / b, divResult, 0);

    }
}
