package duke;

import java.util.ArrayList;

import duke.tasks.Task;

/**
 * Represents the list of tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;
    private int taskCounter;

    /**
     * Constructs an instance and sets initial task count as 0
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
        this.taskCounter = 0;
    }

    /**
     * Inserts the specified element
     * @param task task
     */
    public void add(Task task) {
        this.tasks.add(task);
        this.taskCounter++;
    }

    /**
     * Removes the element in this list.
     * @param index index number of the task
     */
    public void remove(int index) {
        assert index >= 0 : "Invalid index";
        this.tasks.remove(index);
        this.taskCounter--;
    }

    /**
     * Returns the element at the specified position in this list.
     * @param index index number of the task
     * @return the element at the specified position in this list
     */
    public Task get(int index) {
        assert index >= 0 : "Invalid index";
        return this.tasks.get(index);
    }

    /**
     * Returns the number of elements in this list.
     * @return the number of elements in this list
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Gets the number of task
     * @return taskCounter
     */
    public int getTaskCounter() {
        return this.taskCounter;
    }
}
