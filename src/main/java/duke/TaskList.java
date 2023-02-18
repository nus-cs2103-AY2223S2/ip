package duke;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * A custom class to store tasks given by the user.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs new TaskList without parameters.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Constructs new TaskList given ArrayList of tasks.
     *
     * @param taskList An arraylist of tasks.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.tasks = taskList;
    }

    /**
     * Gets the number of tasks inside the task list.
     *
     * @return Size of task list.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Gets the ArrayList of tasks stored inside the TaskList.
     *
     * @return Arraylist of tasks.
     */
    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }

    /**
     * Gets task from task list given task number.
     *
     * @param taskNumber Task number.
     * @return Task with specified task number.
     */
    public Task getTask(int taskNumber) {
        return this.tasks.get(taskNumber - 1);
    }

    /**
     * Sorts the tasks in taskList and reorders them.
     * @param taskList taskList for Duke.
     * @param comparator TaskDateComparator to compare task dates.
     */
    public void sortTasks(TaskList taskList, Comparator<Task> comparator) {
        Collections.sort(taskList.tasks, comparator);
    }

    /**
     * Adds given task into the task list.
     *
     * @param task Task to be added into the list.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Deletes task from the task list given task number.
     *
     * @param num Task number starting from 1.
     * @return Task deleted.
     * @throws DukeException If task list is empty,
     *     given task number below 1 or given task number
     *     greater than total number of tasks in task list.
     */
    public Task deleteTask(int num) throws DukeException {
        int numTasks = this.tasks.size();
        if (numTasks == 0) {
            throw new DukeException("Can't delete anything since no tasks in list! "
                    + "Add some tasks first!");
        }
        if (num < 1) {
            throw new DukeException("Tasks start from 1, please try again!");
        } else if (num > numTasks) {
            throw new DukeException(String.format("You only have %d task(s), "
                    + "please try again with a number less or equal to %d!",
                    numTasks, numTasks));
        } else {
            Task selectedTask = this.tasks.get(num - 1);
            this.tasks.remove(num - 1);
            return selectedTask;
        }
    }

    /**
     * Changes the mark status of a task given task number.
     *
     * @param num Task number starting from 1.
     * @return Task after changing mark status.
     * @throws DukeException If task list is empty or
     *     given task number below 1 or given task number
     *     greater than total number of tasks in task list.
     */
    public Task changeMarkStatus(int num) throws DukeException {
        int numTasks = this.tasks.size();
        if (numTasks == 0) {
            throw new DukeException("Can't mark anything since no tasks in list! "
                    + "Add some tasks first!");
        }
        if (num < 1) {
            throw new DukeException("Tasks start from 1, please try again!");
        } else if (num > numTasks) {
            throw new DukeException(String.format("You only have %d task(s), "
                            + "please try again with a number less or equal to %d!",
                    numTasks, numTasks));
        } else {
            Task selectedTask = this.tasks.get(num - 1).mark();
            this.tasks.set(num - 1, selectedTask);
            return selectedTask;
        }
    }

    @Override
    public String toString() {
        return this.tasks.toString();
    }
}
