package duke;

import duke.query.task.DeadlineQueryHandler;
import duke.query.task.DeleteQueryHandler;
import duke.query.task.EventQueryHandler;
import duke.query.task.FindTaskQueryHandler;
import duke.query.IQueryHandler;
import duke.query.task.ListQueryHandler;
import duke.query.task.MarkQueryHandler;
import duke.query.Query;
import duke.query.QueryParser;
import duke.query.QueryType;
import duke.query.QueryTypeUtil;
import duke.query.SimpleResponseQueryHandler;
import duke.query.task.TodoQueryHandler;
import duke.query.UnknownCommandException;
import duke.query.task.UnmarkQueryHandler;
import duke.task.TaskTracker;

/**
 * The Bot class represents a bot that can process and respond to user queries.
 */
public class Bot {
    private static final boolean SHOULD_LOAD_TASK_SAVE = true;
    private static final String GOODBYE_RES = "GOOD BYE!";
    private static final String UNKNOWN_COMMAND_RES = "Your command is not of the known tongue!";

    private final TaskTracker tt = new TaskTracker();

    /**
     * Initializes the bot.
     *
     * @throws DukeException
     */
    public void init() throws DukeException {
        if (SHOULD_LOAD_TASK_SAVE) {
            tt.loadTasks();
        }
    }

    /**
     * Processes an input string as a query and returns a BotResult object that contains a response string
     * and the current status of the bot.
     *
     * @param input user input to be processed as a query
     * @return the result of processing the user input
     */
    public BotResult process(String input) {
        IFormatter formatter = new ResponseFormatter();
        BotResult.BotStatus status = BotResult.BotStatus.Successful;
        Query query = QueryParser.parseQuery(input);

        QueryType queryType = QueryType.UNKNOWN;
        queryType = QueryTypeUtil.getQueryTypeFromString(query.getCommand());
        if (queryType == QueryType.EXIT) {
            status = BotResult.BotStatus.Exit;
        }

        String response = formatter.format(processQuery(query, queryType));
        return new BotResult(status, response);
    }

    private String processQuery(Query query, QueryType queryType) {
        String response;
        try {
            IQueryHandler queryHandler = getQueryHandler(queryType);
            response = queryHandler.processQuery(query);
        } catch (DukeException e) {
            response = "I have failed you my liege! " + e.getMessage();
        }
        return response;
    }

    private IQueryHandler getQueryHandler(QueryType queryType) throws UnknownCommandException {
        switch (queryType) {
        case TODO:
            return new TodoQueryHandler(tt);
        case DEADLINE:
            return new DeadlineQueryHandler(tt);
        case EVENT:
            return new EventQueryHandler(tt);
        case LIST:
            return new ListQueryHandler(tt);
        case FIND:
            return new FindTaskQueryHandler(tt);
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
