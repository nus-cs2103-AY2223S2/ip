package hachi.main;

import hachi.tasks.Task;

import java.util.ArrayList;

/**
 * Represents a list ot saved tasks.
 */
public class TaskList {

    private ArrayList<Task> toDoList;

    /**
     * TaskList constructor.
     */
    public TaskList() {
        this.toDoList = new ArrayList<Task>();
    }

    /**
     * Returns a Task of the specified index.
     *
     * @param i Index of the Task.
     * @return Task of specified index.
     */
    public Task get(int i) {
        return this.toDoList.get(i);
    }

    /**
     * Adds a Task into the TaskList.
     *
     * @param task New Task object to be added to the TaskList.
     */
    public void add(Task task) {
        assert task != null;
        this.toDoList.add(task);
    }

    /**
     * Removes a Task from the TaskList.
     *
     * @param i Index of the Task to be removed.
     */
    public void remove(int i) {
        this.toDoList.remove(i);
    }

    /**
     * Returns the size of the TaskList.
     *
     * @return Size of the TaskList.
     */
    public int size() {
        return this.toDoList.size();
    }


    /**
     * Return the string representation of the TaskList.
     *
     * @return string representation of the TaskList.
     */
    public String toString() {
        return "" + this.toDoList;
    }
}
