package com.gerib.todoapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("task")
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/create")
    public Task create(@RequestBody Task task) {
        Task createdTask = this.taskService.create(task);
        return createdTask;
    }

    @GetMapping("/{id}/markAsDone")
    public Task markAsDone(@PathVariable String id) {
        Task doneTask = this.taskService.markAsDone(id);
        return doneTask;
    }

    @DeleteMapping("/delete/{id}")
    public int delete(@PathVariable String id) {
        return this.taskService.deleteTask(id);
    }

    @GetMapping("/getAllCompleted")
    public List<Task> getAllCompleted() {
        return this.taskService.getAllTasks(true);
    }

    @GetMapping("/getAllUncompleted")
    public List<Task> getAllUncompleted() {
        return this.taskService.getAllTasks(false);
    }
}

