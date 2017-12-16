package com.kodilla.spring.portfolio;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BoardTestSuite {

    @Test
    public void testTaskAdd() {
        //Given
        Package packagePortfolio = Package.getPackage("com.kodilla.spring.portfolio");
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(packagePortfolio.getName());
        //When
        Board board = applicationContext.getBean(Board.class);
        //Then
        Assert.assertEquals("To-do list", board.getLastToDoTask());
        Assert.assertEquals("In-progress list", board.getLastInProgressTask());
        Assert.assertEquals("Done list", board.getLastDoneTask());
    }
}
