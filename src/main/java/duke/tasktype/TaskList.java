package duke.tasktype;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class used to store multiple tasks.
 */
public class TaskList implements Serializable {
    private ArrayList<Task> lst;

    /**
     * The constructor of TaskList.
     */
    public TaskList() {
        this.lst = new ArrayList<>();
    }

    /**
     * Get the nth task in the list.
     *
     * @param n the index of the task
     * @return the nth task in the list
     */
    public Task get(int n) {
        return this.lst.get(n);
    }

    /**
     * Add a Task object in the list.
     *
     * @param task the Task object to be added
     */
    public void add(Task task) {
        this.lst.add(task);
    }

    /**
     * Return how many tasks in the list.
     *
     * @return the number of tasks in the list
     */
    public int size() {
        return this.lst.size();
    }

    /**
     * Remove the task from the list.
     *
     * @param n the index of the task to be removed
     */
    public void remove(int n) {
        this.lst.remove(n);
    }

    /**
     * Override the toString() method to show the task list.
     *
     * @return a String showing the whole task list
     */
    @Override
    public String toString() {
        return this.lst.toString();
    }
}
