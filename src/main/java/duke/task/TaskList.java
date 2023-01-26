package duke.task;

import duke.exception.DukeException;

import java.util.ArrayList;

/**
 * Contains the task list and the operations that can be performed on the tasks.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public int getSize() {
        return taskList.size();
    }

    public Task getTask(int idx) throws DukeException {
        try {
            return taskList.get(idx);
        } catch (IndexOutOfBoundsException e){
            throw new DukeException("Unable to get task.");
        }
    }

    /**
     * Adds given task into TaskList.
     *
     * @param task Task added by user.
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Deletes task from TaskList given index.
     *
     * @param idx Index of task to be deleted.
     * @return String representation of the deleted task.
     * @throws DukeException
     */
    public String deleteTask(int idx) throws DukeException {
        try {
            return this.taskList.remove(idx).toString();
        } catch (IndexOutOfBoundsException e){
            throw new DukeException("duke.Task index given is invalid :( Unable to delete.");
        }
    }

    /**
     * Marks task as done given its index.
     *
     * @param idx Index of task that is to be marked as done.
     * @return String representation of the task that is marked done.
     * @throws DukeException
     */
    public String markTask(int idx) throws DukeException {
        Task task = this.getTask(idx);
        task.mark();
        return task.toString();
    }

    /**
     * Unmarks task as undone given its index.
     *
     * @param idx Index of task that is to be marked as undone.
     * @return String representation of the task that is marked undone.
     * @throws DukeException
     */
    public String unmarkTask(int idx) throws DukeException {
        Task task = this.getTask(idx);
        task.unmark();
        return task.toString();
    }
}
