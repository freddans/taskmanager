package com.example.taskmanager.services;

import com.example.taskmanager.entities.Task;
import com.example.taskmanager.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // Get all Tasks
    public List allTasks() {
        return taskRepository.findAll();
    }

    // Create task
    public String createTask(Task newTask) {

        // Check if task already exist
        Task existingTask = taskRepository.findByTitle(newTask.getTitle());
        if (existingTask != null && newTask.getTitle().toLowerCase().contains(existingTask.getTitle().toLowerCase())) {
            return "Task already exists with title: " + existingTask.getTitle() + " on ID: " + existingTask.getId();
        } else {

            taskRepository.save(new Task(newTask.getTitle()));
            return "Added new Task with title: " + newTask.getTitle();
        }
    }

    public String getTaskById(long id) {
        Optional<Task> optionalTask = taskRepository.findById(id);

        if (!optionalTask.isPresent()) {

            return "ERROR: Task does not exist with the provided ID: " + id;
        } else {

            Task existingTask = optionalTask.get();
            return "ID: " + existingTask.getId() + "\nTitle: " + existingTask.getTitle() + "\nCompleted: " + existingTask.isCompleted();
        }
    }

    public String updateTask(long id, Task updatedTask) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        Task existingTitle = taskRepository.findByTitle(updatedTask.getTitle());

        if (!optionalTask.isPresent()) {
            return "ERROR: No Task exists with the provided ID: " + id;
        } else {

            if (existingTitle.getTitle().toLowerCase().contains(updatedTask.getTitle().toLowerCase())) {

                return "ERROR: Task with this name already exists";
            } else {

                Task existingTask = optionalTask.get();

                existingTask.setTitle(updatedTask.getTitle());
                taskRepository.save(existingTask);

                return "Updated Task";
            }

        }
    }

    public String deleteTaskById(long id) {
        Optional<Task> optionalTask = taskRepository.findById(id);

        if (!optionalTask.isPresent()) {
            return "ERROR: Task with provided ID: " + id + " does not exist";
        } else {

            Task taskToBeDeleted = optionalTask.get();
            taskRepository.delete(taskToBeDeleted);

            return "Task has been deleted";
        }
    }

}
