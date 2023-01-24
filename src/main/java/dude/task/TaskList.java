package dude.task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Task getTask(int taskIndex) {
        return tasks.get(taskIndex - 1);
    }

    public void addTask(Task task) {
        tasks.add(task);
        Task.addTaskCount();
    }

    public void deleteTask(int taskIndex) {
        tasks.remove(taskIndex - 1);
        Task.decreaseTaskCount();
    }

    public String toRaw() {
        StringBuilder input = new StringBuilder();
        for (Task task : tasks) {
            input.append(task.toRaw());
        }
        return input.toString();
    }

    @Override
    public String toString() {
        StringBuilder result;
        if (tasks.size() != 0) {
            result = new StringBuilder("\tHere are the tasks in your list: \n");
            for (int i = 0; i < tasks.size(); i++) {
                result.append("\t").append(i + 1).append(".").append(tasks.get(i).toString()).append("\n");
            }
        } else {
            result = new StringBuilder("\tEh... You currently got no task leh.\n");
        }
        return result.toString();
    }

}