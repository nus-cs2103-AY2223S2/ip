package duke;

import java.util.ArrayList;

import duke.task.Task;

/**
 * A class to encapsulate task list information & behaviour.
 */
public class TaskList {
    // The task list.
    private ArrayList<Task> list;

    /**
     * Constructor for TaskList class to create empty list.
     */
    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    /**
     * Constructor for TaskList class to load existing list.
     * @param list List of tasks.
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof TaskList) {
            TaskList other = (TaskList) obj;
            return this.list.equals(other.list);
        }
        return false;
    }

    /**
     * Adds a task to the list.
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        list.add(task);
    }

    /**
     * Removes the task at a specific index from the list.
     * @param taskIndex Index of task to be removed.
     */
    public Task removeTask(int taskIndex) {
        return list.remove(taskIndex);
    }

    /**
     * Returns a new TaskList of all tasks that contain a given keyword in their description.
     * @param keyword The word to search for.
     * @return The resulting TaskList.
     */
    public TaskList find(String keyword) {
        ArrayList<Task> output = new ArrayList<>();
        for (Task task : list) {
            if (task.searchDescription(keyword)) {
                output.add(task);
            }
        }

        return new TaskList(output);
    }

    /**
     * Gets the task stored in a given index.
     * @param taskIndex Index to get task from.
     * @return Task stored at taskIndex.
     */
    public Task getTask(int taskIndex) {
        return list.get(taskIndex);
    }

    /**
     * Gets the size of the current list.
     * @return Size of list.
     */
    public int getSize() {
        return list.size();
    }

    /**
     * Gets the ArrayList storing the tasks.
     * @return The list.
     */
    public ArrayList<Task> getList() {
        return list;
    }
}
