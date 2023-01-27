package leo.storage;

import java.util.List;

/**
 * Represents a list of Tasks.
 */
public class TaskList {

    private final List<Task> taskList;

    public TaskList(List<Task> tasks) {
        this.taskList = tasks;
    }

    /**
     * Returns the number of Tasks in the list.
     *
     * @return Number of Tasks.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Adds Task into the list of Tasks.
     *
     * @param t Task to be added.
     */
    public void addTask(Task t) {
        taskList.add(t);
    }

    /**
     * Removes Task from the list of Tasks.
     *
     * @param num Index of Task to be removed.
     */
    public void removeTask(int num) {
        taskList.remove(num);
    }

    /**
     * Returns Task from the list of Tasks.
     *
     * @param num Index of Task to be retrieved.
     * @return Task at index.
     */
    public Task getTask(int num) {
        return taskList.get(num);
    }

}
