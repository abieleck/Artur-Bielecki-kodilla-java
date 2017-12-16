package com.kodilla.spring.portfolio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class Board {

    private TaskList toDoList;
    private TaskList inProgressList;
    private TaskList doneList;

    public Board(TaskList toDoList, TaskList inProgressList, TaskList doneList) {
        this.toDoList = toDoList;
        this.inProgressList = inProgressList;
        this.doneList = doneList;
    }

    public String getLastToDoTask() {
        return toDoList.getLastTask();
    }

    public String getLastInProgressTask() {
        return inProgressList.getLastTask();
    }

    public String getLastDoneTask() {
        return doneList.getLastTask();
    }
}
