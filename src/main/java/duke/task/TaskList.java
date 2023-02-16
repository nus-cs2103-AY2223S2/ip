package duke.task;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class that contains the list of task objects.
 */
public class TaskList implements Serializable {
    private ArrayList<Task> tasks;

    /**
     * Constructor for TaskList object.
     *
     * @param tasks An ArrayList of task object.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructor for TaskList object.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Takes in an integer and outputs the task object at that index.
     *
     * @param index An ArrayList of task object.
     * @return The Task at the index of the list of tasks.
     */
    public Task get(int index) {
        assert index >= 0;
        assert index <= this.getSize();
        return this.tasks.get(index);
    }

    /**
     * Takes in a task object and append it to the list of tasks.
     *
     * @param task The task object to be put into the tasks list.
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Takes in an integer and deletes the task object at that index.
     *
     * @param index The index of the task in the list of tasks to be deleted.
     */
    public void delete(int index) {
        assert index >= 0;
        assert index <= this.getSize();
        this.tasks.remove(index);
    }

    /**
     * Returns the size of the tasks list.
     *
     * @return The size of the tasks list.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Returns string representation of the list of tasks.
     *
     * @return String representation of the list of tasks.
     */
    @Override
    public String toString() {
        return this.tasks.toString();
    }
}
