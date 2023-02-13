package duke.task;

import java.util.ArrayList;

/**
 * Task list to store the task given by the user.
 */
public class TaskList {
    /**
     * Arraylist to keep track of the task.
     */
    private final ArrayList<Task> taskList;

    /**
     * Initialize the task list.
     *
     * @param taskList list of task to be keep track.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Initialize an empty task list.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Return task from task list given its index.
     *
     * @param index index of the task in task list.
     * @return task at that given index.
     */
    public Task getTaskAt(int index) {
        return taskList.get(index);
    }

    /**
     * Insert task at the end of the task list.
     *
     * @param task task to be inserted.
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Remove task from task list given its index.
     *
     * @param index index of task to be removed.
     */
    public void removeTaskAt(int index) {
        taskList.remove(index);
    }

    /**
     * Return number of task in the task list.
     *
     * @return number of task has been keep tracked.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Check if the tasklist is empty or not.
     *
     * @return true if it is empty list, false otherwise.
     */
    public boolean isEmpty() {
        return taskList.isEmpty();
    }
}
