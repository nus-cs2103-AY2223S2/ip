package duke;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    protected List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the list of tasks.
     *
     * @return List of tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Adds a task into the list of tasks.
     *
     * @param task Task object to be added into task list.
     */
    public void add(Task task) {
        tasks.add(task);
    }
}
