package com.example.taskmanager.controllers;

import com.example.taskmanager.entities.Task;
import com.example.taskmanager.entities.ToDoList;
import com.example.taskmanager.services.ToDoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todolist")
public class ToDoListController {

    private ToDoListService toDoListService;

    @Autowired
    public ToDoListController(ToDoListService toDoListService) {
        this.toDoListService = toDoListService;
    }

    // Get all todolists
    @GetMapping("/all")
    public ResponseEntity<List<ToDoList>> getAllToDoLists() {
        return ResponseEntity.ok(toDoListService.getAllToDoLists());
    }

    // Create new todolist
    @PostMapping("/create")
    public ResponseEntity<String> createToDoList(@RequestBody ToDoList newToDoList) {
        return ResponseEntity.ok(toDoListService.createToDoList(newToDoList));
    }

    // Get task by ID
    @GetMapping("/{id}")
    public ResponseEntity<String> getToDoListById(@PathVariable long id) {
        return ResponseEntity.ok(toDoListService.getToDoListById(id));
    }

    // Update task
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateTaskById(@PathVariable long id, @RequestBody ToDoList toDoListToUpdate) {
        return ResponseEntity.ok(toDoListService.updateToDoList(id, toDoListToUpdate));
    }

    // Delete task
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteToDoListById(@PathVariable long id) {
        return ResponseEntity.ok(toDoListService.deleteToDoListById(id));
    }

    // Add Task To ToDo-List
    @PutMapping("/add/{id}")
    public ResponseEntity<String> addTaskToToDoList(@PathVariable long id, @RequestBody Task taskToBeAdded) {
        return ResponseEntity.ok(toDoListService.addTaskToToDoList(id, taskToBeAdded));
    }

    // Remove Task from ToDo-List
    @PutMapping("/removetaskfromlistid/{id}")
    public ResponseEntity<String> removeTaskFromToDoList(@PathVariable long id, @RequestBody Task taskToBeRemoved) {
        return ResponseEntity.ok(toDoListService.removeTaskFromToDoList(id, taskToBeRemoved));
    }
}
