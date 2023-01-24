package model;

import interfaces.Model;

import java.util.ArrayList;
import java.util.List;

public class TaskModel implements Model {
    private final ArrayList<Task> tasks;
    public TaskModel() {
        this.tasks = new ArrayList<>();
    }
    @Override
    public void addTask(Task task) {
        tasks.add(task);
    }

    @Override
    public List<Task> getTasks() {
        return this.tasks;
    }

    public Task getTask(int index) {
        return this.tasks.get(index); // out of bounds exception
    }

    public void markTaskDone(int taskIndex) {
        tasks.get(taskIndex).markTaskDone(); // handle out of bounds exception
    }

    public void markTaskUndone(int taskIndex) {
        tasks.get(taskIndex).markTaskUndone(); // handle out of bounds exception
    }
}
