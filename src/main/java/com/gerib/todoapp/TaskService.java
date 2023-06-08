package com.gerib.todoapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task create(Task task) {
        Task createdTask = this.taskRepository.save(task);
        return createdTask;
    }

    public Task markAsDone(String taskId) {
        //Hapi 1 marrim Taskun nga database duke perdorur taskId
        Task taskFromDb = this.taskRepository.findById(taskId).get();
        //Hapi 2 taskun qe morem e markojm as DONE
        taskFromDb.setCompleted(true);
        //Hapi 3 taskun completed e ruajm ne DB
        return this.taskRepository.save(taskFromDb);
    }

    public int deleteTask(String taskId) {
        int removed = this.taskRepository.removeTaskById(taskId);
        return removed;

    }

    public List<Task> getAllTasks(boolean completed) {
        List<Task> tasks = new ArrayList<>();
        if (completed) {
            tasks = this.taskRepository.findAllByCompletedTrue();
        } else {
            tasks = this.taskRepository.findAllByCompletedFalse();
        }
        return tasks;
    }

}
