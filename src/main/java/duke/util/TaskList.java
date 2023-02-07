package duke.util;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Retrieves a list of current tasks.
     * @return An ArrayList of current tasks.
     */
    public ArrayList<Task> retrieveList() {
        return this.taskList;
    }

    /**
     * Returns number of tasks.
     * @return The number of tasks currently.
     */
    public int getSize() {
        return this.taskList.size();
    }

    /**
     * Retrieves a particular task.
     * @param index Index of task in the ArrayList.
     * @return The requested task.
     */
    public Task getTask(int index) {
        return this.taskList.get(index);
    }

    /**
     * Adds a new task to the ArrayList.
     * @param newTask Task to be added.
     */
    public void addTask(Task newTask) {
        this.taskList.add(newTask);
    }

    /**
     * Removes a particular task.
     * @param index Index of task in the ArrayList.
     */
    public void deleteTask(int index) {
        this.taskList.remove(index);
    }

    /**
     * To mark a particular task as done.
     * @param index Index of task in the ArrayList.
     */
    public void markTask(int index) {
        this.taskList.get(index).markAsDone();
    }

    /**
     * To mark a particular task as undone.
     * @param index Index of task in the ArrayList.
     */
    public void unmarkTask(int index) {
        this.taskList.get(index).markAsUndone();
    }
}
