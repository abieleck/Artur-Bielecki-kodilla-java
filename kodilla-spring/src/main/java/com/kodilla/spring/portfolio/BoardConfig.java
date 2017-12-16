package com.kodilla.spring.portfolio;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class BoardConfig {

    private TaskList getInitializedList(String taskText) {
        TaskList taskList = new TaskList();
        taskList.addTask(taskText);
        return taskList;
    }

    @Bean
    public TaskList toDoList() {
        return getInitializedList("To-do list");
    }

    @Bean
    public TaskList inProgressList() {
        return getInitializedList("In-progress list");
    }

    @Bean
    public TaskList doneList() {
        return getInitializedList("Done list");
    }
}
