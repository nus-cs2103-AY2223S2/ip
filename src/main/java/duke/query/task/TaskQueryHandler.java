package duke.query.task;

import duke.query.QueryHandler;
import duke.task.TaskTracker;

/**
 * The TaskQueryHandler abstract class handles user queries related to tasks.
 */
public abstract class TaskQueryHandler extends QueryHandler {
    protected TaskTracker tt;

    public TaskQueryHandler(TaskTracker tt) {
        this.tt = tt;
    }
}
