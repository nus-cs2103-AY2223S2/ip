package duke;

import java.util.ArrayList;

/**
 * Encapsulates a TaskList that stores the tasks in an ArrayList.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Creates a TaskList object with an ArrayList of tasks.
     * @param tasks The ArrayList of Task objects.
     */
    TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Creates a TaskList object with an empty ArrayList
     */
    TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Gets the list of tasks.
     * @return The ArrayList of Task objects.
     */
    ArrayList<Task> getList() {
        return this.tasks;
    }

    /**
     * Gets a specific task from the list.
     * @param taskNumber The number of the task in the list.
     * @return The specified Task object from the list.
     */
    Task getTask(int taskNumber) {
        assert taskNumber > 1 && taskNumber < numberOfTasks(): "Invalid task number";
        return tasks.get(taskNumber - 1);
    }

    /**
     * Returns an integer representing the number of tasks the list.
     * @return The size of the list.
     */
    int numberOfTasks() {
        return tasks.size();
    }

    /**
     * Modifies a Task object in a list to show that it is done.
     * @param taskNumber The number of the task in the list.
     * @throws DukeException Throws a DukeException if the task number is invalid.
     */
    void markTaskAsDone(int taskNumber) throws DukeException {
        if (taskNumber < 1 || taskNumber > numberOfTasks()) {
            throw new DukeException("OOPS!!! Invalid task number. Please try again.");
        }
        tasks.get(taskNumber - 1).markAsDone();
    }

    /**
     * Modifies a Task object in the list to show that it is incomplete.
     * @param taskNumber The number of the task in the list.
     * @throws DukeException Throws a DukeException if the task number is invalid.
     */
    void markTaskAsIncomplete(int taskNumber) throws DukeException {
        if (taskNumber < 1 || taskNumber > numberOfTasks()) {
            throw new DukeException("OOPS!!! Invalid task number. Please try again.");
        }
        tasks.get(taskNumber - 1).markAsNotDone();
    }

    /**
     * Adds a task to the list.
     * @param t The task to be added to the list.
     */
    void add(Task t) {
        tasks.add(t);
    }

    /**
     * Deletes a task from the list.
     * @param taskNumber The number of the task to be deleted from the list.
     * @throws DukeException Throws a DukeException if the task number is invalid.
     */
    public Task deleteTaskFromList(int taskNumber) throws DukeException {
        if (taskNumber < 1 || taskNumber > numberOfTasks()) {
            throw new DukeException("OOPS!!! Invalid task number. Please try again.");
        }
        return tasks.remove(taskNumber - 1);
    }
}
