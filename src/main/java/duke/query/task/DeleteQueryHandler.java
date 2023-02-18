package duke.query.task;

import duke.exception.DukeException;
import duke.query.Query;
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
        int taskIndex = getIntegerParam(query, getErrorMessage("task index")) - 1;
        Task t = tt.deleteTask(taskIndex);
        tt.saveAllTasks();
        return "Task deleted: " + t;
    }

    @Override
    public String getQueryDescription() {
        return "delete \n- Deletes a task.";
    }

    @Override
    public String getQuerySyntax() {
        return String.format("delete <index of task to delete>");
    }
}
