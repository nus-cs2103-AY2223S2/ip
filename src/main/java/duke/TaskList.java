package duke;

import java.util.ArrayList;

/**
 * Class that contains the task list
 */
public class TaskList {

    /** ArrayList of tasks*/
    private ArrayList<Task> taskList;

    /**
     * Default constructor for TaskList object
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Adds specified task to TaskList
     *
     * @param currentTask Task to be added to TaskList
     */
    public void addTask(Task currentTask) {
        this.taskList.add(currentTask);
    }

    /**
     * Deletes the task at a specified position in TaskList
     *
     * @param taskPosition Position of the task to be deleted
     * @return deleted Task
     */
    public Task deleteTask(int taskPosition) {
        return this.taskList.remove(taskPosition - 1);
    }

    /**
     *  Returns the task at a specified position in TaskList
     * @param pos Position of the task to be retrieved
     * @return Task object
     */
    public Task getTask(int pos) {
        return this.taskList.get(pos);
    }

    /**
     * Returns the number of Tasks in the TaskList
     *
     * @return number of Tasks in the TaskList
     */
    public int size() {
        return this.taskList.size();
    }
}
