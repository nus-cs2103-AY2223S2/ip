package smartduke;

import smartduke.task.Task;

import java.util.ArrayList;

/**
 * TaskList contains the task list which stores tasks and has operations to add/delete tasks in the list.
 */
public class TaskList {
    /**
     * The list of tasks added by the user and currently recorded by SmartDuke.
     */
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) throws DukeException  {
        this.tasks = tasks;
    }

    public int getNoOfTasks() {
        return this.tasks.size();
    }

    /**
     * Add the given task to list.
     * @param task the given task
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Delete the task with the given task number from the list.
     * @param taskNo the given task number
     * @throws DukeException If there is no such task in the list with the given task number.
     * @return The deleted task.
     */
    public Task delete(int taskNo) throws DukeException {
        try {
            return this.tasks.remove(taskNo - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Sorry you have provided an invalid task number...");
        }
    }

    /**
     * Get the list of tasks currently recorded.
     * @return The list of tasks.
     */
    public ArrayList<Task> list() {
        return this.tasks;
    }

    /**
     * Marks the task with the given task number as done.
     * @param taskNo the given task number
     * @throws DukeException If there is no such task in the list with the given task number.
     * @return The marked task.
     */
    public Task mark(int taskNo) throws DukeException {
        try {
            Task task = this.tasks.get(taskNo - 1);
            task.markDone();
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Sorry you have provided an invalid task number...");
        }
    }

    /**
     * Marks the task with the given task number as not done.
     * @param taskNo the given task number
     * @throws DukeException If there is no such task in the list with the given task number.
     * @return The unmarked task.
     */
    public Task unmark(int taskNo) throws DukeException {
        try {
            Task task = this.tasks.get(taskNo - 1);
            task.markNotDone();
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Sorry you have provided an invalid task number...");
        }
    }
}
