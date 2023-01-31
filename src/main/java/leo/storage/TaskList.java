package leo.storage;

import java.util.List;

import leo.ui.Ui;

/**
 * Represents a list of Tasks.
 */
public class TaskList {

    private final List<Task> taskList;

    /**
     * Constructor for creating a TaskList object containing Tasks.
     *
     * @param tasks The list of tasks to be in the taskList.
     */
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

    /**
     * Constructor for displaying the list of Tasks.
     */
    public void display() {
        for (int i = 0; i < size(); i++) {
            Ui.displayMessage((i + 1) + ". " + getTask(i).toString());
        }
    }

}
