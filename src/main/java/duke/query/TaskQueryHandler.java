package duke.query;

import duke.task.TaskTracker;

public abstract class TaskQueryHandler implements IQueryHandler {
    protected TaskTracker tt;

    public TaskQueryHandler(TaskTracker tt) {
        this.tt = tt;
    }
}
