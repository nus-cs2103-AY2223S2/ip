package duke.query.task;

import duke.exception.DukeException;
import duke.query.Query;
import duke.task.TaskTracker;

/**
 * The ListQueryHandler class handles user queries for listing added tasks.
 */
public class ListQueryHandler extends TaskQueryHandler {
    public ListQueryHandler(TaskTracker tt) {
        super(tt);
    }

    /**
     * Processes a query for listing added tasks.
     *
     * @param query a user input string
     * @return response from listing added tasks
     * @throws DukeException
     */
    @Override
    public String processQuery(Query query) throws DukeException {
        if (tt.getNumTasks() < 1 ) {
            return "No items!";
        } else {
            return String.format("Here are your tasks!%s", tt.listTasks());
        }
    }
}
