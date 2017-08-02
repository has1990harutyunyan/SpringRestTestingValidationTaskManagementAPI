package com.example.demo.rest;
import com.example.demo.model.Task;
import com.example.demo.service.TaskService;
import com.example.demo.validation.ValidationErrorBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping(value = "/tasks/add", consumes = "application/json")
    public ResponseEntity addTask(@Valid @RequestBody Task task, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(ValidationErrorBuilder.fromBindingErrors(errors));
        }

        taskService.addTask(task);
        return ResponseEntity.ok("ADDED");
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity getTaskById(@PathVariable long id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity deleteTaskById(@PathVariable long id) {
        taskService.deleteTaskById(id);
        return ResponseEntity.ok("DELETED");
    }


    @PutMapping("/tasks/{id}")
    public ResponseEntity updateTask(@PathVariable long id, @RequestBody Task task) {
        Task currentTask = taskService.getTaskById(id);
        currentTask.setTitle(task.getTitle());
        currentTask.setDescription(task.getDescription());
        currentTask.setDuration(task.getDuration());
        currentTask.setUser(task.getUser());
        taskService.updateTask(currentTask);
        return ResponseEntity.ok("UPDATED");
    }

    @GetMapping("/tasks/")
    public ResponseEntity getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @GetMapping("/tasks/userId/{userId}")
    public ResponseEntity getTasksByUserId(@PathVariable long userId) {
        return ResponseEntity.ok(taskService.showAllTasksByUserId(userId));

    }
}
