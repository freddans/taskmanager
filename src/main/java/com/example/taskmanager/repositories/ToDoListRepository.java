package com.example.taskmanager.repositories;

import com.example.taskmanager.entities.ToDoList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoListRepository extends JpaRepository<ToDoList, Long> {
    ToDoList findByTitle(String title);
}
