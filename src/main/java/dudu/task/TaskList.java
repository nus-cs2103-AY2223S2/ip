package dudu.task;

import java.util.ArrayList;

import dudu.exception.DuduException;
import dudu.exception.TaskNumRangeException;

/**
 * Task list class for storing tasks
 */
public class TaskList {
    private final ArrayList<Task> list;

    /**
     * Constructor for an empty task list.
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Constructor for a used task list.
     *
     * @param list List of task.
     * @throws DuduException If the list is invalid.
     */
    public TaskList(ArrayList<Task> list) throws DuduException {
        this.list = list;
    }

    /**
     * Adds a task to the list.
     *
     * @param task Task to be added.
     * @return List of tasks.
     */
    public ArrayList<Task> addTask(Task task) {
        list.add(task);
        return list;
    }

    /**
     * Get total number of tasks in the list.
     *
     * @return Number of tasks.
     */
    public String getTotalTask() {
        String secondHalf;
        if (list.size() <= 1) {
            secondHalf = " task in the list.";
        } else {
            secondHalf = " tasks in the list.";
        }
        return "Now you have " + list.size() + secondHalf;
    }

    /**
     * Get a specific task in the list.
     *
     * @param index Index of the task to be retrieved.
     * @return The selected task.
     * @throws TaskNumRangeException If the index is out of range.
     */
    public Task getTask(int index) throws TaskNumRangeException {
        if (index >= list.size() || index < 0) {
            throw new TaskNumRangeException(String.valueOf(index));
        }
        return list.get(index);
    }

    /**
     * Gets the list of tasks.
     *
     * @return Return the list of tasks.
     */
    public ArrayList<Task> getList() {
        return list;
    }

    /**
     * Deletes a specific task in the list.
     *
     * @param index Index of the task to be deleted.
     * @return Returns the updated list.
     */
    public ArrayList<Task> delete(int index) {
        list.remove(index);
        return list;
    }

    /**
     * Prints all the tasks in the list using a format.
     */
    public String printList() {
        StringBuilder sb = new StringBuilder();
        if (list.size() == 0) {
            sb.append("There is no task in your list\n");
        } else {
            sb.append("Here are the tasks in your list:\n");
            for (int i = 0; i < list.size(); i++) {
                Task currTask = list.get(i);
                sb.append(i + 1 + "." + currTask + "\n");
            }
        }
        return sb.toString();
    }
}
