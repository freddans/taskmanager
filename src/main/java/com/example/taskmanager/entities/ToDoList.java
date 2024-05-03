package com.example.taskmanager.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "todolists")
public class ToDoList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String title;

    @ManyToMany
    @JoinTable(name = "todolist_tasks",
            joinColumns = @JoinColumn(name = "todolist_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id")
    )
    private List<Task> taskList;

    private int numberOfTasks;

    private boolean isCompleted;

    public ToDoList() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTask(Task task) {
        this.taskList.add(task);
        numberOfTasks++;
    }

    public int getNumberOfTasks() {
        return numberOfTasks;
    }

    public void setNumberOfTasks(int numberOfTasks) {
        this.numberOfTasks = numberOfTasks;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public void removeTask(Task task) {
        taskList.remove(task);
        numberOfTasks--;
    }
}
