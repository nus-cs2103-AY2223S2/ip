package collections;

import java.util.ArrayList;

import exceptions.SundayException;
import task.Task;
import utilities.Storage;


/**
 * TaskList class represents a collection of tasks with methods to add, mark, unmark, delete, save, and load tasks.
 *
 * @author Tan Yan-Hao Joshua
 */
public class TaskList {

    /**
     * The list of Tasks stored.
     */
    private ArrayList<Task> list;

    /**
     * Constructs a new TaskList with an empty list of tasks.
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Adds a task to the list of tasks.
     *
     * @param task The task to add.
     */
    public void add(Task task) {
        this.list.add(task);
    }

    /**
     * Marks a task as completed at the specified index in the list of tasks.
     *
     * @param index The index of the task to mark.
     * @return The marked task.
     */
    public Task mark(int index) {
        this.list.get(index).mark();
        return this.list.get(index);
    }

    /**
     * Unmarks a task as completed at the specified index in the list of tasks.
     *
     * @param index The index of the task to unmark.
     * @return The unmarked task.
     */
    public Task unmark(int index) {
        this.list.get(index).unmark();
        return this.list.get(index);
    }

    /**
     * Deletes a task at the specified index in the list of tasks.
     *
     * @param index The index of the task to delete.
     * @return The deleted task.
     */
    public Task delete(int index) {
        return this.list.remove(index);
    }

    /**
     * Gets a task at the specified index in the list of tasks.
     *
     * @param index The index of the task to get.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return this.list.get(index);
    }

    /**
     * Gets the number of uncompleted tasks in the list of tasks.
     *
     * @return The number of uncompleted tasks.
     */
    public int getUncompletedSize() {
        int count = 0;
        for (Task task : this.list) {
            if (!task.isComplete()) {
                count++;
            }
        }
        return count;
    }

    /**
     * Saves the list of tasks to the data file.
     *
     * @return True if the save operation is successful, otherwise false.
     * @throws SundayException If the data file could not be written to.
     */
    public boolean save() throws SundayException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (i > 0) {
                sb.append("\n");
            }
            sb.append(this.list.get(i).save());
        }
        return Storage.writeToDataFile(sb.toString());
    }

    /**
     * Loads the list of tasks from the data file.
     *
     * @return True if the save operation is successful, otherwise false.
     * @throws SundayException If the data file could not be read from.
     */
    public boolean load() throws SundayException {
        if (Storage.createDataFile()) {
            return true;
        }
        Storage.readFromDataFile();
        return false;
    }

    /**
     * Method to check if the task list is empty.
     *
     * @return True if the list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    /**
     * Finds all tasks that match a given keyword in the list of tasks.
     *
     * @param keyword The keyword to search for in the tasks.
     * @return A TaskList containing all tasks that match the keyword.
     */
    public TaskList find(String keyword) {
        TaskList found = new TaskList();
        for (Task task : this.list) {
            if (task.match(keyword)) {
                found.add(task);
            }
        }
        return found;
    }

    /**
     * Overrides the toString() method to display the TaskList object in a formatted manner.
     *
     * @return A formatted string representation of the TaskList object.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (i > 0) {
                sb.append("\n");
            }
            sb.append(this.getTask(i));
        }
        return sb.toString();
    }
}
