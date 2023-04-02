package com.zak.tododb.service;

import com.zak.tododb.dao.TaskRepository;
import com.zak.tododb.exceptionHandling.TaskServiceException;
import com.zak.tododb.model.TaskDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.zak.tododb.entity.Task;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public List<Task> getTasksByOwner(String owner) {
        return taskRepository.findByOwner(owner);
    }

    @Override
    public Task saveTask(TaskDTO taskDTO){
        ModelMapper modelMapper = new ModelMapper();
        Task task = modelMapper.map(taskDTO, Task.class);
        return taskRepository.save(task);
    }
    @Override
    public Optional<Task> getTaskById(Long taskId) {
        return taskRepository.findById(taskId);
    }


    public void deleteTaskById(Long taskId) {
        taskRepository.deleteById(taskId);
    }

    @Override
    public TaskDTO updateTaskById(long id, TaskDTO taskDTO) throws TaskServiceException{
        Optional<Task> existingTask = getTaskById(id);
        if (existingTask.isPresent()) {
            Task task = existingTask.get();
            TaskDTO selectedTask = new TaskDTO();
            selectedTask.setTaskName(StringUtils.hasLength(taskDTO.getTaskName())?taskDTO.getTaskName():task.getTaskName());
            selectedTask.setOwner(StringUtils.hasLength(taskDTO.getOwner())?taskDTO.getOwner():task.getOwner());
            selectedTask.setStartTime(!ObjectUtils.isEmpty(taskDTO.getStartTime())?taskDTO.getStartTime():task.getStartTime());
            deleteTaskById(existingTask.get().getTaskId());
            Task finalResult = saveTask(selectedTask);
            ModelMapper modelMapper= new ModelMapper();
            return modelMapper.map(finalResult, TaskDTO.class);
//            task.setTaskName(taskDTO.getTaskName());
//            task.setOwner(taskDTO.getOwner());
//            task.setStartTime(taskDTO.getStartTime());
//            return saveTask(task);
        } else {
            throw new TaskServiceException("Task not found with id: " + id);
        }
    }
}
