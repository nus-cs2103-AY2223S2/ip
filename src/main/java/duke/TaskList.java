package duke;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> TASKS;

    public TaskList() {
        this.TASKS = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.TASKS = tasks;
    }

    public ArrayList<Task> getTasks() {
        return TASKS;
    }

    public void addTask(Task task) {
        this.TASKS.add(task);
    }

    public int size() {
        return this.TASKS.size();
    }
}
