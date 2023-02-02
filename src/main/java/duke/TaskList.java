package duke;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final ArrayList<Task> taskList;

    /**
     * Constructor for a TaskList object with an existing ArrayList.
     *
     * @param existing Existing list of Tasks.
     */
    public TaskList(ArrayList<Task> existing) {
        this.taskList = existing;
    }

    /**
     * Constructor for a new TaskList object.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Gets the i-th Task in the TaskList.
     *
     * @param idx Index of the Task to retrieve
     * @return Requested task
     * @throws IndexOutOfBoundsException If supplied index is invalid
     */
    public Task get(int idx) throws IndexOutOfBoundsException {
        return taskList.get(idx);
    }

    /**
     * Adds a Task to the TaskList.
     *
     * @param task Task to add
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Deletes a Task from the TaskList.
     *
     * @param idx Index of the task to delete
     * @return The deleted Task object.
     */
    public Task deleteTask(int idx) throws IndexOutOfBoundsException {
        if (idx < 0 || idx >= taskList.size()) {
            throw new IndexOutOfBoundsException();
        }

        return taskList.remove(idx);
        // printInBanner("Don't need this trash anymore yo~\n" + task + getTasklistSize());
    }

    /**
     * Gets the size of the TaskList.
     *
     * @return An int representing the size of the TaskList.
     */
    public int getSize() {
        return this.taskList.size();
        // return "\nNow you have " + taskList.size() + " items on your list.";
    }

    /**
     * Public getter for the ArrayList.
     *
     * @return The ArrayList of Tasks.
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }
}
