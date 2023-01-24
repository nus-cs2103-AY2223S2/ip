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
}
