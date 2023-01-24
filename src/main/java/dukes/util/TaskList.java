package dukes.util;

import dukes.command.*;
import dukes.task.*;
import java.util.*;

/**
 * The util class containing the list of tasks.
 */
public class TaskList {
    private List<Task> taskList;

    /**
     * Constructor of TaskList class. Initialise an empty list.
     * Used when Duke is initialised in a directory for the first time.
     */
    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    /**
     * Constructor of TaskList class. Set a task list for Duke.
     * Used when Duke is started up (except for the first time).
     */
    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public List<Task> getTaskList() {
        return this.taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }
}
