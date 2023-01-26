package duke.task;

import java.util.LinkedList;
import java.util.List;

/**
 * Class that represents a list that keep tracks
 * of the tasks that was added, marked, unmarked, or deleted.
 * @author Oskar Lew
 * @version 0.1
 * @since 0.1
 */
public class TaskList {
    private List<Task> TASK_LIST = new LinkedList<>();

    /**
     * Constructor of TaskList.
     */
    public TaskList() {}

    /**
     * Method to add a task into TaskList
     * @param task List of task.
     */
    public void add(Task task) {
        this.TASK_LIST.add(task);
    }

    /**
     * Method to return the length of the TaskList.
     * @return The length of the TaskList.
     */
    public int size() {
        return this.TASK_LIST.size();
    }

    /**
     * Method to get the task from the TaskList
     * with an index.
     * @param index Index of the task.
     * @return The tasks that was at the index.
     */
    public Task get(int index) {
        return this.TASK_LIST.get(index);
    }

    /**
     * Method to remove a task from the TaskList
     * with an index
     * @param index Index of the task being removed.
     */
    public void remove(int index) {
        this.TASK_LIST.remove(index);
    }
    
}
