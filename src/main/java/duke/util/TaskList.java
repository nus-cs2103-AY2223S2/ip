package duke.util;

import duke.task.Task;

import java.util.ArrayList;

/*
 * Represents a list of tasks.
 */
public class TaskList {

    private final ArrayList<Task> taskList;

    /*
     * Constructor for the TaskList object.
     * 
     * @param taskList ArrayList of tasks.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /*
     * Constructor for the TaskList object if there is no initial task list to load.
     */
    public TaskList() {
        this.taskList = new ArrayList<Task>(100);
    }

    /*
     * Returns the task at the specified index.
     * 
     * @param index Index of the task to be returned.
     * @return Task at the specified index.
     */
    public Task get(int index) {
        return this.taskList.get(index);
    }

    /*
     * Method to check if the task list is empty.
     * 
     * @return true if the task list is empty.
     */
    public boolean isEmpty() {
        return this.taskList.isEmpty();
    }

    /*
     * Method to see the size of the task list.
     * 
     * @return Size of the task list as an integer.
     */
    public int size() {
        return this.taskList.size();
    }

    /*
     * Method to add a task to the task list.
     * 
     * @param task Task to be added to the task list.
     * @return Task that was added to the task list.
     */
    public void add(Task task) {
        this.taskList.add(task);
    }

    /*
     * Method to delete a task from the task list.
     * 
     * @param index Index of the task to be deleted.
     */
    public void deleteTask(int index) {
        taskList.remove(index);
    }

    /*
     * Method to mark a task as done.
     * 
     * @param index Index of the task to be marked as done.
     * @return String representation of the task that was marked as done.
     */
    public String markTask(int index) {
        return taskList.get(index).mark();
    }

    /*
     * Method to mark a task as undone.
     * 
     * @param index Index of the task to be marked as undone.
     * @return String representation of the task that was marked as undone.
     */
    public String unmarkTask(int index) {
        return taskList.get(index).unmark();
    }

}