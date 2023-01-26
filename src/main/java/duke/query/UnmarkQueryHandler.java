package duke.query;

import duke.DukeException;
import duke.task.Task;
import duke.task.TaskTracker;

import java.util.StringTokenizer;

public class UnmarkQueryHandler extends TaskQueryHandler {
    public UnmarkQueryHandler(TaskTracker tt) {
        super(tt);
    }

    @Override
    public String processQuery(String query) throws DukeException {
        StringTokenizer st = new StringTokenizer(query);
        st.nextToken();
        Task t = tt.markUnmarkTask(Integer.parseInt(st.nextToken()) - 1, false);
        tt.saveAllTasks();
        return "Task marked as incomplete: " + t;
    }
}
