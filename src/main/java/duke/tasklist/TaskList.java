package duke.tasklist;


import duke.exception.TaskNumberNotFoundException;
import duke.task.Task;

import java.util.ArrayList;

/**
 * Represents the task list in Duke.
 */
public class TaskList {

    ArrayList<Task> taskList;

    /**
     * Represents the task list in Duke.
     *
     * @param tasks an ArrayList of tasks to be stored in TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.taskList = tasks;
    }

    /**
     * Represents the task list in Duke. This constructor is used when first initializing Duke with an empty database.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * @return the number of tasks in the TaskList.
     */
    public int length() {
        return this.taskList.size();
    }

    /**
     * Returns the task according to the identifier.
     * @param taskNumber identifier of the task.
     * @return the task corresponding to the identifier.
     * @throws TaskNumberNotFoundException thrown when the identifier does not exist in the taskList.
     */
    public Task getTask(int taskNumber) throws TaskNumberNotFoundException {
        try {
            return this.taskList.get(taskNumber - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNumberNotFoundException();
        }
    }

    /**
     * Deletes the task according to the identifier.
     *
     * @param taskNumber identifier of the task.
     * @throws TaskNumberNotFoundException thrown when the identifier does not exist in the taskList.
     */
    public void deleteTask(int taskNumber) throws TaskNumberNotFoundException {
        try {
            this.taskList.remove(taskNumber - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNumberNotFoundException();
        }
    }

    /**
     * Adds the task to the task list.
     *
     * @param task task to be added.
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Getter for the ArrayList of tasks stored in TaskList. Typically only used when storing the tasks in the database
     * @return Arraylist of tasks in the Tasklist.
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

}
