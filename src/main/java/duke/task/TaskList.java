package duke.task;

import java.io.IOException;
import java.util.ArrayList;

import duke.storage.Storage;
import duke.UI.UI;

/**
 * The list of task of the user.
 */
public class TaskList {
    private static ArrayList<Task> tasks;

    /**
     * The constructor of TaskList.
     * @param tasks Tasks to be tracked.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Retrieves the task from the 0th index of the list.
     * @param taskNumber The index of the task.
     * @return The task at the corresponding number.
     */
    public Task getTask(int taskNumber) {
        return tasks.get(taskNumber);
    }

    /**
     * Adds a new task to the list.
     * @param task Task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Counts the number of tasks in the list.
     * @return The current number of tasks in the list.
     */
    public int numberOfTasks() {
        return tasks.size();
    }

    /**
     * Removes the task from the list.
     * @param taskNumber The index of the task to be removed.
     */
    public void removeTask(int taskNumber) {
        tasks.remove(taskNumber);
    }

    /**
     * Marks a task as done.
     * @param taskNumber The index of the task to be marked as done.
     * @param storage Storage in the disk
     */
    public void mark(int taskNumber, Storage storage) {
        Task nameOfTask = tasks.get(taskNumber - 1);
        nameOfTask.markAsDone();
        System.out.println("Nice work! This task has been marked as done: \n" + nameOfTask);

        try {
            storage.save(this);
        } catch (IOException error) {
            System.err.println(error.getMessage());
        }
    }

    /**
     * Marks a previously marked as done task as not done.
     * @param taskNumber The index of the previously marked done task to be marked as undone.
     * @param storage Storage in the disk.
     */
    public void unmark(int taskNumber, Storage storage) {
        Task nameOfTask = tasks.get(taskNumber - 1);
        nameOfTask.unmarkAsDone();
        System.out.println("Noted. This task has been marked as not done yet: \n"  + nameOfTask);

        try {
            storage.save(this);
        } catch (IOException error) {
            System.err.println(error.getMessage());
        }
    }

    /**
     * Adds a new task to the list.
     * @param taskToAdd New task to be added.
     * @param storage Storage in the disk.
     */
    public void addTask(Task taskToAdd, Storage storage) {
        tasks.add(taskToAdd);
    }

    /**
     * Deletes an existing task.
     * @param taskNumber The index of the task to be deleted.
     * @param storage Storage in the disk.
     * @return The task to be deleted.
     */
    public Task deleteTask(int taskNumber, Storage storage) {
        assert taskNumber >= 0 : "taskNumber is non-negative";
        Task taskToBeDeleted = tasks.remove(taskNumber - 1);
        return taskToBeDeleted;
    }
}
