package com.example.taskmanager.controllers;

import com.example.taskmanager.entities.Task;
import com.example.taskmanager.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    private TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // Get all tasks
    @GetMapping("/all")
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(taskService.allTasks());
    }

    // Create
    @PostMapping("/create")
    public ResponseEntity<String> createTask(@RequestBody Task newTask) {
        return ResponseEntity.ok(taskService.createTask(newTask));
    }

    // Get task by ID
    @GetMapping("/{id}")
    public ResponseEntity<String> getTaskById(@PathVariable long id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    // Update task
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateTaskById(@PathVariable long id, @RequestBody Task taskToUpdate) {
        return ResponseEntity.ok(taskService.updateTask(id, taskToUpdate));
    }

    // Delete task
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTaskById(@PathVariable long id) {
        return ResponseEntity.ok(taskService.deleteTaskById(id));
    }

}
