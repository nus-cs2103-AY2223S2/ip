package duke.query;

import duke.DukeException;
import duke.task.Task;
import duke.task.TaskTracker;

/**
 * The MarkQueryHandler class handles user queries for marking tasks.
 */
public class MarkQueryHandler extends TaskQueryHandler {
    public MarkQueryHandler(TaskTracker tt) {
        super(tt);
    }

    /**
     * Processes a query for marking a task.
     *
     * @param query a user input string
     * @return response from marking a task
     * @throws DukeException
     */
    @Override
    public String processQuery(Query query) throws DukeException {
        Task t = tt.markUnmarkTask(Integer.parseInt(query.getParam()) - 1, true);
        tt.saveAllTasks();
        return "Task marked as complete: " + t;
    }
}
