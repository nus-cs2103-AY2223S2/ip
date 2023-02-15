package jeo.database;

import java.util.ArrayList;

import jeo.task.Task;

/**
 * Represents the tasks stored as a list.
 * @author Goh Jun How
 * @version 0.3
 */
public class TaskList {
    protected ArrayList<Task> taskList;

    /**
     * Creates an empty database for tasks.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Creates a database for tasks with the specified task list representing loaded tasks.
     * @param taskList ArrayList representing loaded tasks.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Gets the list of tasks.
     * @return ArrayList representing the list of tasks.
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Gets the task at the specified index.
     * @param index An integer representing the position in the list.
     * @return Task at the specified index.
     */
    public Task getTaskAtIndex(int index) {
        return taskList.get(index);
    }

    /**
     * Gets the current total number of tasks in the list.
     * @return An integer representing the current total number of tasks.
     */
    public int getNumberOfTasks() {
        return taskList.size();
    }

    /**
     * Adds the specified task to the list.
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Deletes the specified task from the list.
     * @param i An integer representing the index at which the task is to be deleted.
     */
    public void deleteTask(int i) {
        taskList.remove(i);
    }

    /**
     * Marks the task at the specified index position as done.
     * @param i An integer representing the index at which the task is to be marked.
     */
    public void markTask(int i) {
        Task task = getTaskAtIndex(i);
        if (task.getStatusIcon().equals(" ")) {
            task.markAsDone();
        }
    }

    /**
     * Marks the task at the specified index position as not done.
     * @param i An integer representing the index at which the task is to be unmarked.
     */
    public void unmarkTask(int i) {
        Task task = getTaskAtIndex(i);
        if (task.getStatusIcon().equals("X")) {
            task.unmarkFromDone();
        }
    }
}
