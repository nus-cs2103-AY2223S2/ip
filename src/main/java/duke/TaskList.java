package duke;

import java.util.ArrayList;

import duke.tasks.Task;

/**
 * Represents the task list
 */
public class TaskList {
    private static ArrayList<Task> task;
    private int taskCounter;

    /**
     * TaskList constructor
     */
    public TaskList() {
        task = new ArrayList<>();
        this.taskCounter = 0;
    }

    /**
     * Inserts the specified element
     * @param t
     */
    public void add(Task t) {
        assert t == null : "Invalid task";
        task.add(t);
        taskCounter++;
    }

    /**
     * Removes the element in this list.
     * @param index
     */
    public void remove(int index) {
        assert index >= 0 : "Invalid index";
        task.remove(index);
        taskCounter--;
    }

    /**
     * Returns the element at the specified position in this list.
     * @param index
     * @return the element at the specified position in this list
     */
    public Task get(int index) {
        assert index >= 0 : "Invalid index";
        return task.get(index);
    }

    /**
     * Returns the number of elements in this list.
     * @return the number of elements in this list
     */
    public int size() {
        return task.size();
    }

    /**
     * Returns the number of taskCounter
     * @return the number of taskCounter
     */
    public int getTaskCounter() {
        return this.taskCounter;
    }
}
