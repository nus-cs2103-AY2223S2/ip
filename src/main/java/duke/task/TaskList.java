package duke.task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represent a task list.
 */
public class TaskList {
    private final List<Task> tasks;

    /**
     * Creates a TaskList object.
     */
    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < tasks.size(); ++i) {
            stringBuilder.append(String.format("%d.%s\n", i + 1, tasks.get(i).toString()));
        }

        return stringBuilder.toString().trim();
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index The index of the task to be returned.
     * @return The task at the specified index.
     * @throws IndexOutOfBoundsException Indicates that the index is out of range.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Add the specified task to the end of the list.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Remove the task at the specified index.
     *
     * @param index The index of the task to be removed.
     * @return The removed task.
     * @throws IndexOutOfBoundsException Indicates that the index is out of range.
     */
    public Task removeAt(int index) {
        return tasks.remove(index);
    }
}
