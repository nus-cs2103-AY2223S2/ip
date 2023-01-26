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

    public Task getTask(int taskIndex) throws DukeException {
        try {
            return taskList.get(taskIndex);
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
     * @param taskIndex Index of task to be deleted.
     * @return String representation of the deleted task.
     * @throws DukeException
     */
    public String deleteTask(int taskIndex) throws DukeException {
        try {
            return this.taskList.remove(taskIndex).toString();
        } catch (IndexOutOfBoundsException e){
            throw new DukeException("duke.Task index given is invalid :( Unable to delete.");
        }
    }

    /**
     * Marks task as done given its index.
     *
     * @param taskIndex Index of task that is to be marked as done.
     * @return String representation of the task that is marked done.
     * @throws DukeException
     */
    public String markTask(int taskIndex) throws DukeException {
        Task task = this.getTask(taskIndex);
        task.mark();
        return task.toString();
    }

    /**
     * Unmarks task as undone given its index.
     *
     * @param taskIndex Index of task that is to be marked as undone.
     * @return String representation of the task that is marked undone.
     * @throws DukeException
     */
    public String unmarkTask(int taskIndex) throws DukeException {
        Task task = this.getTask(taskIndex);
        task.unmark();
        return task.toString();
    }

    public ArrayList<Task> findTask(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            if (task.getDescription().indexOf(keyword) != -1) {
                foundTasks.add(task);
            }
        }
        return foundTasks;
    }
}
