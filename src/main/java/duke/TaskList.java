package duke;

import duke.task.Task;

import java.util.ArrayList;

/**
 * Represents a tasklist.
 */
public class TaskList {
    private ArrayList<Task> array;

    /**
     * Returns an empty task list.
     */
    public TaskList() {
        array = new ArrayList<>();
    }

    /**
     * Adds a task to the end of the list.
     * @param task task to be added.
     */
    public void addTask(Task task) {
        array.add(task);
    }

    /**
     * Deletes a task from an index.
     * @param index index of task to delete.
     */
    public void deleteTask(int index) {
        array.remove(index);
    }

    /**
     * Marks a task at an index.
     * @param index index of task to mark.
     * @param mark final status of task.
     */
    public void markTask(int index, boolean mark) {
        assert index < array.size() : "index cannot be out of bounds";
        this.getTask(index).setStatus(mark);
    }

    /**
     * Returns task at index.
     * @param index index of task to return.
     * @return task at index.
     */
    public Task getTask(int index) {
        if (index < array.size()) {
            return array.get(index);
        } else {
            return null;
        }
    }

    /**
     * Returns length of task list.
     * @return length of task list.
     */
    public int getLength() {
        return array.size();
    }
}
