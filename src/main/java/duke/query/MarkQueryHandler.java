package duke.query;

import duke.DukeException;
import duke.task.Task;
import duke.task.TaskTracker;

import java.util.StringTokenizer;

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
    public String processQuery(String query) throws DukeException {
        StringTokenizer st = new StringTokenizer(query);
        st.nextToken();
        Task t = tt.markUnmarkTask(Integer.parseInt(st.nextToken()) - 1, true);

        assert t.getStatusIndicator().equals("[X]") : "task should be marked!";

        tt.saveAllTasks();
        return "Task marked as complete: " + t;
    }
}
