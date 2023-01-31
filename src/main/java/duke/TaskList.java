package duke;

import java.io.Serializable;
import java.util.ArrayList;

import duke.tasks.Task;


/**
 * The TaskList that stores Task objects.
 */
public class TaskList implements Serializable {
    private ArrayList<Task> tasks;

    /**
     * TaskList constructor.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Returns the Task object at the specified index of the ArrayList.
     *
     * @param i An integer representing the index
     * @return Task
     */
    public Task get(int i) {
        return this.tasks.get(i);
    }

    /**
     * Adds a Task object into the ArrayList.
     *
     * @param t The Task object to be added into the ArrayList.
     */
    public void add(Task t) {
        this.tasks.add(t);
    }

    /**
     * Returns the size of the ArrayList.
     *
     * @return int representing the size.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Removes the Task object at the specified index of the ArrayList.
     *
     * @param i An integer representing the index
     */
    public void remove(int i) {
        this.tasks.remove(i);
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        return "" + this.tasks;
    }

}
