package chattime;

import java.util.ArrayList;

import chattime.task.Task;

/**
 * Represents a list object to store user's tasks.
 *
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs a taskList object with ArrayList as data structure.
     *
     * @param initialTasks Stored data loaded from storage.
     */
    public TaskList(ArrayList<Task> initialTasks) {
        tasks = initialTasks;
    }

    /**
     * Adds a new task to current task list.
     * @param task New task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes index-th task object from current task list.
     *
     * @param index Input index from user, index-th task in task list to be removed.
     */
    public void removeTask(int index) {
        tasks.remove(index - 1);
    }

    /**
     * Gets the index-th task from the task list.
     *
     * @param index Input index from user.
     * @return The index-th task in task list.
     */
    public Task getTask(int index) {
        return tasks.get(index - 1);
    }

    /**
     * Gets the current entire task list.
     *
     * @return Current task list.
     */
    public ArrayList<Task> getList() {
        return tasks;
    }
}
