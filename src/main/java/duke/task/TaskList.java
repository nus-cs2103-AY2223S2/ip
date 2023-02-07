package duke.task;

import java.util.ArrayList;

import duke.exception.DukeException;

/**
 * Contains the task list and the operations that can be performed on the tasks.
 */
public class TaskList {
    private final ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
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
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Unable to get task.");
        }
    }

    /**
     * Adds given task into TaskList.
     *
     * @param task Task added by user.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Deletes task from TaskList given index.
     *
     * @param taskIndex Index of task to be deleted.
     * @return String representation of the deleted task.
     * @throws DukeException Throws exception if the given task index is invalid.
     */
    public String deleteTask(int taskIndex) throws DukeException {
        try {
            return taskList.remove(taskIndex).toString();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task index given is invalid :( Unable to delete.");
        }
    }

    /**
     * Marks task as done given its index.
     *
     * @param taskIndex Index of task that is to be marked as done.
     * @return String representation of the task that is marked done.
     * @throws DukeException Throws exception if the given task index is invalid.
     */
    public String markTask(int taskIndex) throws DukeException {
        Task task = getTask(taskIndex);
        task.mark();
        return task.toString();
    }

    /**
     * Unmarks task as undone given its index.
     *
     * @param taskIndex Index of task that is to be marked as undone.
     * @return String representation of the task that is marked undone.
     * @throws DukeException Throws exception if the given task index is invalid.
     */
    public String unmarkTask(int taskIndex) throws DukeException {
        Task task = getTask(taskIndex);
        task.unmark();
        return task.toString();
    }

    /**
     * Returns a list of tasks that has the given keyword in their descriptions.
     *
     * @param keyword Keyword to filter the list of tasks.
     * @return A list of tasks that have the keyword in their descriptions.
     */
    public ArrayList<Task> findTask(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task: taskList) {
            if (task.getDescription().contains(keyword)) {
                foundTasks.add(task);
            }
        }
        return foundTasks;
    }
}
