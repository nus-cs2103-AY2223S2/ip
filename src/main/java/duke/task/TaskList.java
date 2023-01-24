package duke.task;

import duke.DukeException;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 *
 * @author wz2k
 */
public class TaskList {
    /** Task list */
    private ArrayList<Task> taskList;

    /**
     * Creates a list of tasks.
     *
     * @param taskList List of tasks.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return Total task count.
     */
    public int getSize() {
        return taskList.size();
    }

    /**
     * Adds a task to the list.
     *
     * @param task Task to add.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Returns the specified task.
     *
     * @param taskNumber Task order in the list.
     * @return Task of the corresponding task number.
     */
    public Task getTask(int taskNumber) {
        return taskList.get(taskNumber - 1);
    }

    /**
     * Marks the task at the specified task number as done.
     *
     * @param taskNumber Task order in the list.
     * @return Task of the corresponding task number.
     */
    public Task markTask(int taskNumber) {
        Task task = taskList.get(taskNumber - 1);
        task.mark();
        return task;
    }

    /**
     * Marks the task at the specified task number as not done.
     *
     * @param taskNumber Task order in the list.
     * @return Task of the corresponding task number.
     */
    public Task unmarkTask(int taskNumber) {
        Task task = taskList.get(taskNumber - 1);
        task.unmark();
        return task;
    }

    /**
     * Deletes a specified task based on its order.
     *
     * @param taskNumber Task order in the list.
     * @return Task of the corresponding task number.
     * @throws DukeException If task does not exist.
     */
    public Task deleteTask(int taskNumber) throws DukeException {
        int size = taskList.size();

        if (size == 0 || taskNumber > size) {
            throw  new DukeException("duke.task.Task number does not exist.");
        }

        Task task = taskList.get(taskNumber - 1);
        taskList.remove(taskNumber - 1);
        return task;
    }
}
