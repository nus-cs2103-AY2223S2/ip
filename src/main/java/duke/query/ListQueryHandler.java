package duke.query;

import duke.DukeException;
import duke.task.TaskTracker;

public class ListQueryHandler extends TaskQueryHandler {
    public ListQueryHandler(TaskTracker tt) {
        super(tt);
    }

    @Override
    public String processQuery(String query) throws DukeException {
        return tt.getNumTasks() < 1 ? "No items!" : tt.listTasks();
    }
}
