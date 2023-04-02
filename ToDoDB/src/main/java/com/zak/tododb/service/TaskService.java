package com.zak.tododb.service;

import com.zak.tododb.entity.Task;
import com.zak.tododb.exceptionHandling.TaskServiceException;
import com.zak.tododb.model.TaskDTO;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    List<Task> getAllTasks();
    List<Task> getTasksByOwner(String owner);
    Optional<Task>getTaskById(Long taskId);
    Task saveTask(TaskDTO task);
    void deleteTaskById(Long taskId);

    TaskDTO updateTaskById(long id, TaskDTO taskDTO) throws TaskServiceException;

}
