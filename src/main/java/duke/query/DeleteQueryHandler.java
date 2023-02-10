package duke.query;

import duke.DukeException;
import duke.task.Task;
import duke.task.TaskTracker;

/**
 * The DeleteQueryHandler class handles user queries for deleting tasks.
 */
public class DeleteQueryHandler extends TaskQueryHandler {
    public DeleteQueryHandler(TaskTracker tt) {
        super(tt);
    }

    /**
     * Processes a query for deleting a task.
     *
     * @param query a user input string
     * @return response from deleting a task
     * @throws DukeException
     */
    @Override
    public String processQuery(Query query) throws DukeException {
        Task t = tt.deleteTask(Integer.parseInt(query.getParam()) - 1);
        tt.saveAllTasks();
        return "Task deleted: " + t;
    }
}
