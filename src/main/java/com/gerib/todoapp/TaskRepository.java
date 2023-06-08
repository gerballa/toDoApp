package com.gerib.todoapp;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends MongoRepository<Task, String> {

    int removeTaskById(String id);

    List<Task> findAllByCompletedFalse();
    List<Task> findAllByCompletedTrue();

}
