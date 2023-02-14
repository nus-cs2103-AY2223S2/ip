package wtd;

import java.util.ArrayList;

import wtd.task.Task;

/**
 * TaskList is a wrapper class for ArrayList<Task> that contains the list of tasks.
 */
public class TaskList extends ArrayList<Task> {
    /**
     * Constructor for TaskList.
     * 
     * @param list the ArrayList of tasks.
     */
    public TaskList(ArrayList<Task> list) {
        super(list);
    }

    /**
     * Constructor for empty TaskList.
     */
    public TaskList() {
        super();
    }
}