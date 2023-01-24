package duke;

import java.util.StringTokenizer;

import duke.query.*;
import duke.task.TaskTracker;

public class Bot {
    private static final boolean SHOULD_LOAD_TASK_SAVE = true;
    private static final String GOODBYE_RES = "GOOD BYE!";
    private static final String UNKNOWN_COMMAND_RES = "Your command is not of the known tongue!";

    private TaskTracker tt = new TaskTracker();

    public void init() throws DukeException {
        if (SHOULD_LOAD_TASK_SAVE) {
            tt.loadTasks();
        }
    }

    public BotResult process(String input) {
        IFormatter formatter = new ResponseFormatter();
        BotResult.BotStatus status = BotResult.BotStatus.Successful;
        String response;
        StringTokenizer query = new StringTokenizer(input);
        QueryType queryType = QueryType.UNKNOWN;

        if (query.hasMoreTokens()) {
            queryType = QueryTypeUtil.GetQueryTypeFromString(query.nextToken());
        }

        if (queryType == QueryType.EXIT) {
            status = BotResult.BotStatus.Exit;
        }

        try {
            IQueryHandler queryHandler = getQueryHandler(queryType);
            response = queryHandler.processQuery(input);
        } catch (DukeException e) {
            response = "I have failed you my liege! " + e.getMessage();
        }

        return new BotResult(status, response);
    }

    private IQueryHandler getQueryHandler(QueryType queryType) throws UnknownCommandException {
        switch(queryType) {
            case TODO:
                return new TodoQueryHandler(tt);
            case DEADLINE:
                return new DeadlineQueryHandler(tt);
            case EVENT:
                return new EventQueryHandler(tt);
            case LIST:
                return new ListQueryHandler(tt);
            case MARK:
                return new MarkQueryHandler(tt);
            case UNMARK:
                return new UnmarkQueryHandler(tt);
            case DELETE:
                return new DeleteQueryHandler(tt);
            case EXIT:
                return new SimpleResponseQueryHandler(GOODBYE_RES);
            default:
                throw new UnknownCommandException(UNKNOWN_COMMAND_RES);
        }
    }
}
