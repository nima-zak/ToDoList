package com.zak.tododb.controller;

import com.zak.tododb.exceptionHandling.TaskServiceException;
import com.zak.tododb.model.TaskDTO;
import com.zak.tododb.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.zak.tododb.entity.Task;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping("/")
    public String home() {
        return "My To Do List";
    }

    @GetMapping("/tasks")
    public List<Task> getTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/tasks/{owner}")
    public List<Task> getTasksByOwner(@PathVariable String owner) {
        return taskService.getTasksByOwner(owner);
    }

    @PostMapping("/tasks")
    public Task createTask(@RequestBody TaskDTO task) {
        return taskService.saveTask(task);
    }

    @DeleteMapping("/tasks/{id}")
    public void deleteTaskById(@PathVariable Long id) {
        taskService.deleteTaskById(id);
    }
    @PutMapping("/tasks/{id}")
    public TaskDTO updateTaskById(@PathVariable Long id, @RequestBody TaskDTO updatedTask) throws TaskServiceException {
        return taskService.updateTaskById(id, updatedTask);
    }


}
