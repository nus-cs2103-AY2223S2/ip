package duke.task;

import java.util.ArrayList;

/**
 * Represents a collection of Tasks.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Constructs a TaskList instance.
     */
    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    /**
     * Adds a Task to this TaskList.
     *
     * @param task Task to be added.
     * @return true (as specified by ArrayList.add).
     */
    public boolean add(Task task) {
        return tasks.add(task);
    }

    /**
     * Removes the Task at specified index.
     *
     * @param idx Index of the Task to be removed.
     * @return The Task that was removed from TaskList.
     */
    public Task remove(int idx) {
        if (idx < 0 || idx >= tasks.size()) {
            // out of bounds
            return null;
        }

        return tasks.remove(idx);
    }

    /**
     * Removes all Task from the TaskList.
     */
    public void removeAllTask() {
        tasks.clear();
    }

    /**
     * Consolidates all the String representation of every Task in TaskList.
     * Each representation is separated by a newline.
     * Representation is specified by <code>Storable</code>.
     *
     * @return String representation of all the Task in this TaskList.
     */
    public String prepareFileSave() {
        StringBuilder sb = new StringBuilder();
        for (Task t : tasks) {
            sb.append(t.toCsv()).append("\n");
        }
        return sb.toString();
    }

    public boolean isValidIndex(int idx) {
        return idx >= 0 && idx < tasks.size();
    }

    /**
     * Returns the Task from this TaskList at the specified index.
     *
     * @param idx Index of the Task.
     * @return Task at the specified position.
     */
    public Task get(int idx) {
        if (idx < 0 || idx >= tasks.size()) {
            // out of bounds
            return null;
        }

        return tasks.get(idx);
    }

    /**
     * Return the number of Task in this TaskList.
     *
     * @return The number of Task in this TaskList.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns true if this TaskList contains no Task.
     *
     * @return true if this TaskList contains no Task.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

}
