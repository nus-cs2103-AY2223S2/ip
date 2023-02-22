package task;

import java.util.ArrayList;

import exception.TaskListIndexException;

/**
 * A wrapper for <code>ArrayList&lt;Task&gt;</code> with custom
 * <code>IndexOutOfBoundsException</code> handling.
 */
public class TaskList {
    private final ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Creates an empty tasklist.
     */
    public TaskList() {

    }

    /**
     * Returns the <code>Task</code> at the specified position of the wrapped <code>ArrayList</code>.
     *
     * @param i index of the <code>Task</code> to return.
     * @return the <code>Task</code> at the specified position.
     * @throws TaskListIndexException if no <code>Task</code> exists at the specified position.
     */
    public Task get(int i) throws TaskListIndexException {
        try {
            return tasks.get(i);
        } catch (IndexOutOfBoundsException ex) {
            String message = "There's no task #" + i + "! ";
            if (tasks.size() == 1) {
                message += "There is currently 1 task...";
            } else {
                message += "There are currently " + tasks.size() + " tasks...";
            }
            throw new TaskListIndexException(message);
        }
    }

    /**
     * Appends the specified <code>Task</code> to the end of the wrapped <code>ArrayList</code>.
     *
     * @param t <code>Task</code> to be appended.
     */
    public void add(Task t) {
        tasks.add(t);
    }

    /**
     * Deletes the <code>Task</code> at the specified position of the wrapped <code>ArrayList</code>.
     *
     * @param i index of the <code>Task</code> to return.
     * @return the <code>Task</code> deleted from the specified position.
     * @throws TaskListIndexException if no <code>Task</code> exists at the specified position.
     */
    public Task delete(int i) throws TaskListIndexException {
        try {
            return tasks.remove(i);
        } catch (IndexOutOfBoundsException ex) {
            String message = "There's no task #" + i + "! ";
            if (tasks.size() == 1) {
                message += "There is currently 1 task...";
            } else {
                message += "There are currently " + tasks.size() + " tasks...";
            }
            throw new TaskListIndexException(message);
        }
    }

    /**
     * Clears the wrapped <code>ArrayList</code>.
     */
    public void clear() {
        tasks.clear();
    }

    /**
     * Returns the size of the wrapped <code>ArrayList</code>.
     *
     * @return the size of the wrapped <code>ArrayList</code>.
     */
    public int size() {
        return tasks.size();
    }
}
