package model;

import interfaces.Model;

import java.util.ArrayList;
import java.util.List;

public class TaskModel implements Model {
    private final ArrayList<Task> tasks;
    public TaskModel() {
        this.tasks = new ArrayList<>();
    }

    public Task createTask(String description) {
        Task newTask = new ToDo(description);
        this.tasks.add(newTask);
        return newTask;
    }

    public Task createTask(String description, String deadline) {
        Task newTask = new Deadline(description, deadline);
        this.tasks.add(newTask);
        return newTask;
    }

    public Task createTask(String description, String startTime, String endTime) {
        Task newTask = new Event(description, startTime, endTime);
        this.tasks.add(newTask);
        return newTask;
    }

    @Override
    public List<Task> getTasks() {
        return this.tasks;
    }

    public Task getTask(int index) {
        return this.tasks.get(index); // out of bounds exception
    }

    public int getNumberOfTasks() {
        return this.tasks.size();
    }

    public void markTaskDone(int taskIndex) {
        tasks.get(taskIndex).markTaskDone(); // handle out of bounds exception
    }

    public void markTaskUndone(int taskIndex) {
        tasks.get(taskIndex).markTaskUndone(); // handle out of bounds exception
    }
}
