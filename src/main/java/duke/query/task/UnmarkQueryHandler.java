package duke.query.task;

import duke.DukeException;
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
        Task t = tt.markUnmarkTask(Integer.parseInt(query.getParam()) - 1, false);
        assert t.getStatusIndicator().equals("[ ]") : "task should be unmarked!";

        tt.saveAllTasks();
        return "Task marked as incomplete: " + t;
    }
}
