package duke.query.task;

import duke.exception.DukeException;
import duke.query.Query;
import duke.task.Task;
import duke.task.TaskTracker;

/**
 * The UnmarkQueryHandler class handles user queries for unmarking tasks.
 */
public class UnmarkQueryHandler extends TaskQueryHandler {
    public UnmarkQueryHandler(TaskTracker tt) {
        super(tt);
    }

    /**
     * Processes a query for unmarking a task.
     *
     * @param query a user input string
     * @return response from unmarking a task
     * @throws DukeException
     */
    @Override
    public String processQuery(Query query) throws DukeException {
        Task t = tt.markUnmarkTask(getIntegerParam(query,getErrorMessage("task index")), false);
        assert t.getStatusIndicator().equals("[ ]") : "task should be unmarked!";

        tt.saveAllTasks();
        return "Task marked as incomplete: " + t;
    }

    @Override
    public String getQueryDescription() {
        return "unmark \n- Unmarks a task as complete.";
    }

    @Override
    public String getQuerySyntax() {
        return "unmark <index of task to unmark>";
    }
}
