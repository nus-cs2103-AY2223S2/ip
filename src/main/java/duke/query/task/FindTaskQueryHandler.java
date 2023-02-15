package duke.query.task;

import duke.DukeException;
import duke.query.InvalidCommandParamException;
import duke.query.Query;
import duke.task.TaskTracker;

/**
 * The FindTaskQueryHandler class handles queries for finding tasks.
 */
public class FindTaskQueryHandler extends TaskQueryHandler {

    public FindTaskQueryHandler(TaskTracker tt) {
        super(tt);
    }

    @Override
    public String processQuery(Query query) throws DukeException {
        String keyword = getNotBlankParam(query, "Please provide a keyword!");
        String matchStr = tt.listTasksByKeyword(keyword);
        return matchStr.isBlank() ? "No matches found for " + keyword : matchStr;
    }
}
