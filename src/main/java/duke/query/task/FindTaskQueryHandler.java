package duke.query.task;

import duke.exception.DukeException;
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
        String keyword = getNotBlankParam(query, getErrorMessage("keyword"));
        String matchStr = tt.listTasksByKeyword(keyword);
        return matchStr.isBlank() ? "No matches found for " + keyword : matchStr;
    }

    @Override
    public String getQueryDescription() {
        return "find \n- Finds all tasks that match the provided keyword.";
    }

    @Override
    public String getQuerySyntax() {
        return "find <keyword>";
    }
}
