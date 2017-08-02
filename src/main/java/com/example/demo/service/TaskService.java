package com.example.demo.service;

import com.example.demo.model.Task;
import com.example.demo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;


    public void addTask(Task task) {
        taskRepository.save(task);
    }

    public Task getTaskById(long id) {
        return taskRepository.findOne(id);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public void deleteTaskById(long id) {
        taskRepository.delete(id);
    }

    public void updateTask(Task task) {
        taskRepository.save(task);
    }

    public List<Task> showAllTasksByUserId(long id) {
       return taskRepository.findAllByUserId(id);
    }


}
