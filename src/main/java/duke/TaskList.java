package duke;

import tasks.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks. It contains methods for adding, retrieving and deleting tasks.
 */
public class TaskList {
    private List<Task> list;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Constructs a TaskList from an initial list of tasks.
     *
     * @param initList The initial list of tasks to use for the TaskList.
     */
    public TaskList(List<Task> initList) {
        this.list = initList;
    }

    /**
     * Retrieves a task from the TaskList at the specified index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return list.get(index);
    }


    /**
     * Adds a task to the TaskList.
     *
     * @param task The task to add.
     */
    public void addTask(Task task) {
        list.add(task);
    }

    /**
     * Deletes a task from the TaskList at the specified index.
     *
     * @param index The index of the task to delete.
     */
    public void deleteTask(int index) {
        list.remove(index);
    }

    /**
     * Gets the number of tasks in the TaskList.
     *
     * @return The number of tasks in the TaskList.
     */
    public int getSize() {
        return list.size();
    }


    public List<Task> getTaskList() {
        return list;
    }
}
