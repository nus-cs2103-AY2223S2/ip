package duke.tasks;

import java.util.ArrayList;

/**
 * Creates a list of tasks to be stored in the Duke program
 */
public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Creates a new TaskList with an empty list of tasks 
     * or loaded list of tasks from the hard disk
     * 
     * @param loadList Contains the loaded list of tasks from the hard disk
     */
    public TaskList(ArrayList<Task> loadList) {
        this.tasks.addAll(loadList);
    }

    /**
     * Returns the length of the TaskList
     * 
     * @return
     */
    public int listLength() {
        return this.tasks.size();
    }

    /**
     * Adds a task to the TaskList
     * 
     * @param task Task
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Deletes a task from the TaskList
     * 
     * @param taskIndex The index of the task
     */
    public void deleteTask(int taskIndex) {
        this.tasks.remove(taskIndex - 1);
    }

    /**
     * Returns a task at a given index
     * 
     * @param taskIndex
     * @return 
     */
    public Task getTask(int taskIndex) {
        return this.tasks.get(taskIndex - 1);
    }

    /**
     * Checks if the TaskList is empty
     * 
     * @return the boolean representing whether the list is empty
     */
    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }


}
