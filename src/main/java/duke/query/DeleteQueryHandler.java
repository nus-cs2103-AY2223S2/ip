package duke.query;

import duke.DukeException;
import duke.task.Task;
import duke.task.TaskTracker;

import java.util.StringTokenizer;

/**
 * The DeleteQueryHandler class handles user queries for deleting tasks.
 */
public class DeleteQueryHandler extends TaskQueryHandler {
    public DeleteQueryHandler(TaskTracker tt) {
        super(tt);
    }

    /**
     * Processes a query for deleting a task.
     * @param query a user input string
     * @return response from deleting a task
     * @throws DukeException
     */
    @Override
    public String processQuery(String query) throws DukeException {
        StringTokenizer st = new StringTokenizer(query);
        st.nextToken();
        Task t = tt.deleteTask(Integer.parseInt(st.nextToken()) - 1);
        tt.saveAllTasks();
        return "Task deleted: " + t;
    }
}
