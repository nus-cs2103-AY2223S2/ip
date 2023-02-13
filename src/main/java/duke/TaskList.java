package duke;

import java.util.ArrayList;

/**
 * List to contain the tasks in Duke
 */
public class TaskList {
    private ArrayList<Task> list;

    /**
     * Constructor of the TaskList
     * @param size Size of the list
     */
    public TaskList(int size) {
        list = new ArrayList<Task>(size);
    }

    /**
     * Adds the task to the end of the list
     * @param task Task to be added
     */
    public void addTask(Task task) {
        list.add(task);
    }

    /**
     * Gets the size of the list
     * @return Size of the list
     */
    public int getSize() {
        return list.size();
    }

    /**
     * Gets the task at the given index
     * @param index Index of the task needed
     * @return Task at the index given
     */
    public Task getTask(int index) {
        return list.get(index);
    }

    /**
     * Removes the task at the given index
     * @param index Index of the task to be removed
     * @return Task that was removed
     */
    public Task removeTask(int index) {
        return list.remove(index);
    }
}
