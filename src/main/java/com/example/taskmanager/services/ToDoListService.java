package com.example.taskmanager.services;

import com.example.taskmanager.entities.Task;
import com.example.taskmanager.entities.ToDoList;
import com.example.taskmanager.repositories.TaskRepository;
import com.example.taskmanager.repositories.ToDoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ToDoListService {

    private ToDoListRepository toDoListRepository;
    private TaskRepository taskRepository;

    @Autowired
    public ToDoListService(ToDoListRepository toDoListRepository, TaskRepository taskRepository) {
        this.toDoListRepository = toDoListRepository;
        this.taskRepository = taskRepository;
    }

    // Get all todolists
    public List getAllToDoLists() {
        return toDoListRepository.findAll();
    }

    // Create
    public String createToDoList(ToDoList newToDoList) {
        toDoListRepository.save(newToDoList);
        return "Added new ToDo-List with title: " + newToDoList.getTitle();
    }

    public String getToDoListById(long id) {
        Optional<ToDoList> optionalToDoList = toDoListRepository.findById(id);

        if (!optionalToDoList.isPresent()) {

            return "ERROR: ToDo-List does not exist with the provided ID: " + id;
        } else {

            ToDoList existingToDoList = optionalToDoList.get();
            return "ID: " + existingToDoList.getId() + "\nTitle: " + existingToDoList.getTitle() + "\nNumber of tasks: " + existingToDoList.getNumberOfTasks() + "\nCompleted: " + existingToDoList.isCompleted();
        }
    }

    public String updateToDoList(long id, ToDoList updatedToDoList) {
        Optional<ToDoList> optionalToDoList = toDoListRepository.findById(id);


        if (!optionalToDoList.isPresent()) {
            return "ERROR: No ToDo-List exists with the provided ID: " + id;
        } else {

            // Check if title already exist
            ToDoList existingTitle = toDoListRepository.findByTitle(updatedToDoList.getTitle());


            // if there are no existing title with that name:
            if (existingTitle != null && existingTitle.getTitle().toLowerCase().contains(updatedToDoList.getTitle().toLowerCase())) {

                return "ERROR: ToDo-List with this name already exists";
            } else {

                ToDoList existingToDoList = optionalToDoList.get();

                existingToDoList.setTitle(updatedToDoList.getTitle());
                toDoListRepository.save(existingToDoList);

                return "Updated ToDo-List";
            }
        }
    }

    public String deleteToDoListById(long id) {
        Optional<ToDoList> optionalToDoList = toDoListRepository.findById(id);

        if (!optionalToDoList.isPresent()) {
            return "ERROR: ToDo-List with the provided ID: " + id + " does not exist";
        } else {

            ToDoList toDoListToBeDeleted = optionalToDoList.get();
            toDoListRepository.delete(toDoListToBeDeleted);

            return "ToDo-List has been deleted";
        }
    }

    public String addTaskToToDoList(long id, Task taskToBeAdded) {
        Optional<ToDoList> optionalToDoList = toDoListRepository.findById(id);
        Optional<Task> optionalTask = taskRepository.findById(taskToBeAdded.getId());

        if (!optionalToDoList.isPresent()) {

            return "ERROR: ToDo-List with the provided ID: " + id + " does not exist";
        } else {

            if (!optionalTask.isPresent()) {

                return "ERROR: Task with the provided ID: " + taskToBeAdded.getId() + " does not exist";
            } else {

                ToDoList existingToDoList = optionalToDoList.get();
                Task existingTask = optionalTask.get();

                existingToDoList.setTask(existingTask);

                toDoListRepository.save(existingToDoList);

                return "Added Task to ToDo-List";
            }
        }
    }

    public String removeTaskFromToDoList(long id, Task taskToBeRemoved) {
        Optional<ToDoList> optionalToDoList = toDoListRepository.findById(id);
        Optional<Task> optionalTask = taskRepository.findById(taskToBeRemoved.getId());

        if (!optionalToDoList.isPresent()) {

            return "ERROR: ToDo-List with the provided ID: " + id + " does not exist";
        } else {

            if (!optionalTask.isPresent()) {

                return "ERROR: Task with the provided ID: " + taskToBeRemoved.getId() + " does not exist";
            } else {

                ToDoList existingToDoList = optionalToDoList.get();
                Task existingTask = optionalTask.get();

                if (existingToDoList.getTaskList().isEmpty()) {

                    return "ToDo-List does not have any current Tasks";
                } else {

                    existingToDoList.removeTask(existingTask);

                    toDoListRepository.save(existingToDoList);

                    return "Removed Task from ToDo-List";
                }
            }
        }
    }

}
