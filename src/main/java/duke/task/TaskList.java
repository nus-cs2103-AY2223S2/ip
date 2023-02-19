package duke.task;

import java.util.ArrayList;

/**
 * List to index and contain Tasks.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Constructor for the TaskList class.
     * @param taskList ArrayList containing Tasks.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Another constructor for the TaskList class, if there is no existing Task.
     */
    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    /**
     * Adds a Task to the TaskList.
     * @param t Task to be added.
     */
    public void add(Task t) {
        this.taskList.add(t);
    }

    /**
     * Gets Task from the TaskList.
     * @param i index of the Task to get.
     * @return The Task to get.
     */
    public Task get(int i) {
        return this.taskList.get(i);
    }

    /**
     * Removes Task from the TaskList.
     * @param i index of the Task to remove.
     * @return The Task that got removed.
     */
    public Task remove(int i) {
        return this.taskList.remove(i);
    }

    /**
     * Gets number of Tasks in the TaskList.
     * @return The number of Tasks in the TaskList.
     */
    public int size() {
        return this.taskList.size();
    }
}
