package duke.query.task;

import duke.DukeException;
import duke.query.IQueryHandler;
import duke.query.QueryModule;
import duke.query.QueryType;
import duke.task.TaskTracker;

import java.util.HashMap;

public class TaskQueryModule extends QueryModule {
    private final TaskTracker tt = new TaskTracker();

    /**
     * @throws DukeException
     */
    @Override
    public void init() throws DukeException {
        this.tt.loadTasks();
    }

    /**
     * @param queryTypeToQueryHandler
     */
    @Override
    public void installQueryHandlers(HashMap<QueryType, IQueryHandler> queryTypeToQueryHandler) {
        queryTypeToQueryHandler.put(QueryType.TODO, new TodoQueryHandler(tt));
        queryTypeToQueryHandler.put(QueryType.DEADLINE, new DeadlineQueryHandler(tt));
        queryTypeToQueryHandler.put(QueryType.EVENT, new EventQueryHandler(tt));
        queryTypeToQueryHandler.put(QueryType.LIST, new ListQueryHandler(tt));
        queryTypeToQueryHandler.put(QueryType.FIND, new FindTaskQueryHandler(tt));
        queryTypeToQueryHandler.put(QueryType.MARK, new MarkQueryHandler(tt));
        queryTypeToQueryHandler.put(QueryType.UNMARK, new UnmarkQueryHandler(tt));
        queryTypeToQueryHandler.put(QueryType.DELETE, new DeleteQueryHandler(tt));
    }
}
