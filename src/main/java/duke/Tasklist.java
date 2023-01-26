package duke;

import duke.task.Task;

import java.util.ArrayList;

/**
 * Tasklist class that stores tasks and handles the addition and deletion of tasks
 * @author oliverloo
 * @version 1.0
 *
 */
public class Tasklist {
    ArrayList<Task> list;

    /**
     * Constructor method that Initialises an empty list of tasks
     */
    public Tasklist() {
        this.list = list = new ArrayList<Task>(100);
    }

    /**
     * Adds a Task into tasklist object
     * @param task
     */
    public void add(Task task) {
        list.add(task);
    }

    /**
     * Deletes a Task from Tasklist at index i
     * @param i
     */
    public void delete(int i) {
        list.remove(i);
    }

    /**
     * Returns a Task from Tasklist at index i
     * @param i
     * @return Task at index i
     */
    public Task get(int i) {
        return list.get(i);
    }

    /**
     * Returns the number of tasks in Tasklist
     * @return number of tasks in Tasklist
     */
    public int size() {
        return list.size();
    }

    /**
     * Clears all tasks in Tasklist
     */
    public void clear() {
        list.clear();
    }
}
