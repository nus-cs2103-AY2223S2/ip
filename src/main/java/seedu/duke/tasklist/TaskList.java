package seedu.duke.tasklist;

import seedu.duke.tasks.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    /**
     * Add a task to the taskList
     * @param task The task object to be added
     * @return True if successful
     */
    public boolean add(Task task) {
        return taskList.add(task);
    }

    /**
     * Remove a task at the specified index from the taskList
     * @param index The index of the task that is to be removed
     * @return The removed task, if successful
     */
    public Task remove(int index) {
        return taskList.remove(index);
    }

    /**
     * Return the number of elements in the internal ArrayList
     * @return The number of tasks in the taskList
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Return the internal ArrayList object
     * @return The taskList
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Return the task at the specified index of the taskList
     * @param i The index of the desired item in the taskList
     * @return The desired task
     */
    public Task get(int i) {
        return taskList.get(i);
    }
}
