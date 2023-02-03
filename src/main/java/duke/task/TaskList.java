package duke.task;

import duke.DukeException;

import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the task list.
     * @param index The number of the task to delete.
     * @throws DukeException If the number specified is invalid (> Number of tasks in task list or <= 0).
     */
    public void deleteTask(int index) throws DukeException {
        try {
            tasks.remove(index - 1);
        } catch (IndexOutOfBoundsException err) {
            throw new DukeException("That task does not exist!");
        }
    }

    /**
     * Unmarks a task in the task list.
     * @param index The number of the task to unmark.
     * @throws DukeException If the number specified is invalid (> Number of tasks in task list or <= 0).
     */
    public void unmarkTask(int index) throws DukeException {
        try {
            Task cur = tasks.get(index - 1);
            cur.markUndone();
        } catch (IndexOutOfBoundsException err) {
            throw new DukeException("That task does not exist!");
        }
    }

    /**
     * Marks a task in the task list.
     * @param index The number of the task to mark.
     * @throws DukeException If the number specified is invalid (> Number of tasks in task list or <= 0).
     */
    public void markTask(int index) throws DukeException {
        try {
            Task cur = tasks.get(index - 1);
            cur.markDone();
        } catch (IndexOutOfBoundsException err) {
            throw new DukeException("That task does not exist!");
        }
    }

    public int getNumTasks() {
        return tasks.size();
    }
}