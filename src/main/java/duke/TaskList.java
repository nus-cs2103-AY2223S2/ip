package duke;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> TASKS;

    public TaskList() {
        TASKS = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> tasks) {
        TASKS = tasks;
    }

    public ArrayList<Task> getTasks() {
        return TASKS;
    }

    public void addTask(Task task) {
        TASKS.add(task);
    }

    public int size() {
        return TASKS.size();
    }
}
