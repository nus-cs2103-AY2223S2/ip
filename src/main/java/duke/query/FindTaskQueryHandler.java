package duke.query;

import duke.DukeException;
import duke.task.TaskTracker;

/**
 * The FindTaskQueryHandler class handles queries for finding tasks.
 */
public class FindTaskQueryHandler extends TaskQueryHandler {

    public FindTaskQueryHandler(TaskTracker tt) {
        super(tt);
    }

    @Override
    public String processQuery(String query) throws DukeException {
        String[] parsed = QueryParser.parseQuery(query, new String[]{});
        if (parsed[1] == null || parsed[1].isBlank()) {
            throw new InvalidCommandParamException("Please provide a keyword!");
        }
        String matchStr = tt.listTasksByKeyword(parsed[1]);
        return matchStr.isBlank() ? "No matches found for " + parsed[1] : matchStr;
    }
}
