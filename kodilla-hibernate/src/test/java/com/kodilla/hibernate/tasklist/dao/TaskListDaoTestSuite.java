package com.kodilla.hibernate.tasklist.dao;

import com.kodilla.hibernate.tasklist.TaskList;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TaskListDaoTestSuite {

    private final static String NAME = "List name";
    private final static String DESCRIPTION = "List desription";

    @Autowired
    private TaskListDao taskListDao;

    @Test
    public void testFindByListName() {
        //Given
        TaskList taskList = new TaskList(NAME, DESCRIPTION);
        taskListDao.save(taskList);
        //When
        List<TaskList> returnedTaskLists = taskListDao.findByListName(NAME);
        //Then
        Assert.assertEquals(1, returnedTaskLists.size());
        TaskList returnedTaskList = returnedTaskLists.get(0);
        Assert.assertEquals(NAME, returnedTaskList.getListName());
        Assert.assertEquals(DESCRIPTION, returnedTaskList.getDescription());
        //Clean up
        int id = returnedTaskList.getId();
        taskListDao.delete(id);
    }

}
