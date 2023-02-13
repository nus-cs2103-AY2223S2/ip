package aqua.manager;

import aqua.usertask.UserTask;


/**
 * A data class to store information about a size change in
 * {@code TaskManager}.
 */
public class TaskChangeReport {
    /** Task that changed. */
    public final UserTask task;
    /** New size of {@code TaskManager}. */
    public final int numTask;


    /**
     * Constructs a {@code TaskChangeReport}.
     *
     * @param task - the task that changed.
     * @param numTask - the number of tasks.
     */
    public TaskChangeReport(UserTask task, int numTask) {
        this.task = task;
        this.numTask = numTask;
    }
}
