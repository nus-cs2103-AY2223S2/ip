package duke;

import java.util.ArrayList;
import java.util.List;

/**
 * This class will create a list to contain all the Task
 */
public class TaskList {
    private static List<Task> taskLists;

    /**
     * This is a constructor for TaskList
     */
    public TaskList() {
        taskLists = new ArrayList<>(100);
    }

    /**
     * Adds task into tasklists
     * @param task that will be added into tasklists
     */
    public void add(Task task) {
        taskLists.add(task);
    }

    /**
     * Gets the task based on index
     * @param index index of task
     * @return the task of that index
     */
    public Task get(int index){
        return taskLists.get(index);
    }

    /**
     * Removes task from tasklists
     * @param task task that is removed
     */
    public void remove(Task task){
        taskLists.remove(task);
    }
}