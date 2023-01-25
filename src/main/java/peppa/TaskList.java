package peppa;

import java.util.ArrayList;

/**
 * Represents a list of tasks and handles all task-related logic.
 */
public class TaskList {
    private ArrayList<Task> taskList;
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Adds task to the end of the current list.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Removes task from the current list.
     *
     * @param task Task to be removed.
     */
    public void deleteTask(Task task) {
        taskList.remove(task);
    }

    /**
     * Returns the number of tasks in the list currently.
     *
     * @return Length of tasklist.
     */
    public int getLength() {
        return taskList.size();
    }

    /**
     * Returns the task at the specified position in the list.
     *
     * @param i Index of the task (zero-based).
     * @return Task at position i.
     */
    public Task retrieveTask(int i)  {
        return taskList.get(i);
    }
}
