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
        final String TO_DO_TASK_TXT = "To-do task";
        final String IN_PROGRESS_TASK_TXT = "In-progress task";
        final String DONE_TASK_TXT = "Done task";
        //Given
        Package packagePortfolio = Package.getPackage("com.kodilla.spring.portfolio");
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(packagePortfolio.getName());
        Board board = applicationContext.getBean(Board.class);
        //When
        board.addToDoTask(TO_DO_TASK_TXT);
        board.addInProgressTask(IN_PROGRESS_TASK_TXT);
        board.addDoneTask(DONE_TASK_TXT);
        //Then
        Assert.assertEquals(TO_DO_TASK_TXT, board.getLastToDoTask());
        Assert.assertEquals(IN_PROGRESS_TASK_TXT, board.getLastInProgressTask());
        Assert.assertEquals(DONE_TASK_TXT, board.getLastDoneTask());
    }
}
