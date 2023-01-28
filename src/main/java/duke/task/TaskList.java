package duke.task;

import java.util.ArrayList;

import duke.exception.DukeException;

/**
 * Represents a list of Task objects.
 * Supports add, delete, mark, unmark operations.
 *
 * @author Lian Kok Hai
 */

public class TaskList {
    protected ArrayList<Task> taskList;
    protected int taskCount;

    /**
     * Constructs new TaskList.
     */
    public TaskList() {
        this.taskCount = 0;
        this.taskList = new ArrayList<>();
    }

    /**
     * Returns number of tasks in TaskList.
     *
     * @return Integer number of tasks in TaskList.
     */
    public int getCount() {
        return taskCount;
    }

    /**
     * Adds a task into TaskList.
     *
     * @param task New task to be added.
     */
    public void addTask(Task task) {
        this.taskList.add(task);
        taskCount++;
    }


    /**
     * Deletes a task.
     *
     * @param taskNumber 1-based index of task.
     * @return Deleted Task
     * @throws DukeException Thrown when index given is out of bounds.
     */
    public Task deleteTask(int taskNumber) throws DukeException {
        try {
            Task deletedTask = this.taskList.get(taskNumber - 1);
            this.taskList.remove(taskNumber - 1);
            taskCount--;
            return deletedTask;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("No task with given task number of " + taskNumber);
        }
    }

    /**
     * Marks a task as done.
     *
     * @param taskNumber 1-based index of task.
     * @return Marked Task.
     * @throws DukeException Thrown when index given is out of bounds.
     */
    public Task markTask(int taskNumber) throws DukeException {
        try {
            Task task = this.taskList.get(taskNumber - 1);
            task.markDone();
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("No task with given task number of " + taskNumber);
        }
    }

    /**
     * Marks a task as undone.
     *
     * @param taskNumber 1-based index of task.
     * @return Unmarked Task.
     * @throws DukeException Thrown when index given is out of bounds.
     */
    public Task unmarkTask(int taskNumber) throws DukeException {
        try {
            Task task = this.taskList.get(taskNumber - 1);
            task.unmarkDone();
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("No task with given task number of " + taskNumber);
        }
    }

    /**
     * Lists all tasks in TaskList
     *
     * @return String representation of all tasks in list.
     */
    public String listTasks() {
        String result = "";
        result += "Here are the tasks in your list:\n";
        for (int i = 0; i < this.taskCount; i++) {
            result += String.format("%d. %s \n", i + 1, this.taskList.get(i));
        }
        return result;
    }

    /**
     * Encodes TaskList into a String representation to save into text file.
     * Uses each Tasks own encode method.
     *
     * @return Encoded String representation of TaskList.
     */
    public String encode() {
        String result = "";
        for (int i = 0; i < this.taskCount; i++) {
            result += String.format("%s\n", this.taskList.get(i).encode());
        }
        return result;
    }

    /**
     * Loads an ArrayList of Tasks.
     * Used to initialise TaskList from saved text file.
     *
     * @param taskList ArrayList of Tasks.
     */
    public void loadTasks(ArrayList<Task> taskList) {
        this.taskList = taskList;
        this.taskCount = taskList.size();
    }

    public String findTasks(String str) {
        String result = "";
        result += "Here are the matching tasks in your list:\n";
        int counter = 0;
        for (int i = 0; i < this.taskCount; i++) {
            if (this.taskList.get(i).getTaskName().contains(str)) {
                counter++;
                result += String.format("%d. %s \n", counter, this.taskList.get(i));
            }
        }
        return result;
    }
}
