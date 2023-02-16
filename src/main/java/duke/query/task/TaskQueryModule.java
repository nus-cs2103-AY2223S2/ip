package duke.query.task;

import java.util.HashMap;

import duke.exception.DukeException;
import duke.query.QueryHandler;
import duke.query.QueryModule;
import duke.task.TaskTracker;

public class TaskQueryModule extends QueryModule {
    public static final String LIST_QUERY_TYPE = "list";
    public static final String MARK_QUERY_TYPE = "mark";
    public static final String UNMARK_QUERY_TYPE = "unmark";
    public static final String DELETE_QUERY_TYPE = "delete";
    public static final String TODO_QUERY_TYPE = "todo";
    public static final String DEADLINE_QUERY_TYPE = "deadline";
    public static final String EVENT_QUERY_TYPE = "event";
    public static final String FIND_QUERY_TYPE = "find";
    private final TaskTracker tt = new TaskTracker();

    /**
     * @throws DukeException
     */
    @Override
    public void init() throws DukeException {
        this.tt.loadTasks();
    }

    /**
     * @param commandToQueryHandler
     */
    @Override
    public void installQueryHandlers(HashMap<String, QueryHandler> commandToQueryHandler) {
        commandToQueryHandler.put(TODO_QUERY_TYPE, new TodoQueryHandler(tt));
        commandToQueryHandler.put(DEADLINE_QUERY_TYPE, new DeadlineQueryHandler(tt));
        commandToQueryHandler.put(EVENT_QUERY_TYPE, new EventQueryHandler(tt));
        commandToQueryHandler.put(LIST_QUERY_TYPE, new ListQueryHandler(tt));
        commandToQueryHandler.put(FIND_QUERY_TYPE, new FindTaskQueryHandler(tt));
        commandToQueryHandler.put(MARK_QUERY_TYPE, new MarkQueryHandler(tt));
        commandToQueryHandler.put(UNMARK_QUERY_TYPE, new UnmarkQueryHandler(tt));
        commandToQueryHandler.put(DELETE_QUERY_TYPE, new DeleteQueryHandler(tt));
    }
}
