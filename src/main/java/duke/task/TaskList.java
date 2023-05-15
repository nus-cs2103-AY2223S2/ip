package duke.task;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents a list that keep tracks
 * of the tasks that was added, marked, unmarked, or deleted.
 * @author Oskar Lew
 * @version 0.1
 * @since 0.1
 */
public class TaskList {
    private List<Task> taskList = new ArrayList<>();

    /**
     * Constructor of TaskList.
     */
    public TaskList() {
    }

    /**
     * Method to add a task into TaskList
     * @param task List of task.
     */
    public void add(Task task) {
        this.taskList.add(task);
    }

    /**
     * Method to return the length of the TaskList.
     * @return The length of the TaskList.
     */
    public int size() {
        return this.taskList.size();
    }

    /**
     * Method to get the task from the TaskList
     * with an index.
     * @param index Index of the task.
     * @return The tasks that was at the index.
     */
    public Task get(int index) {
        return this.taskList.get(index);
    }

    /**
     * Method to remove a task from the TaskList
     * with an index
     * @param index Index of the task being removed.
     */
    public void remove(int index) {
        this.taskList.remove(index);
    }

    /**
     * Method to update status of task when
     * loading txt file from storage.
     * @param index Index of task.
     * @param status Status of task from storage.
     */
    public void updateStatusFromStorage(int index, String status) {
        if (status.equals("[X]")) {
            this.get(index).markAsDone();
        }
    }

    /**
     * Method to update tags when loading
     * txt file from storage.
     * @param index Index of task.
     * @param tags Tags of task.
     */
    public void updateTag(int index, String[] tags) {
        Task task = this.get(index);
        for (String tag : tags) {
            task.tagTask(tag);
        }
    }
}
