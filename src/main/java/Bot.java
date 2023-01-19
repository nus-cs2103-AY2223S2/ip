import javax.management.Query;
import java.util.StringTokenizer;

public class Bot {
    private static final String BYE_QUERY_TYPE = "bye";
    private static final String LIST_QUERY_TYPE = "list";
    private static final String MARK_QUERY_TYPE = "mark";
    private static final String UNMARK_QUERY_TYPE = "unmark";
    private static final String DELETE_QUERY_TYPE = "delete";
    private static final String TODO_QUERY_TYPE = "todo";
    private static final String DEADLINE_QUERY_TYPE = "deadline";
    private static final String EVENT_QUERY_TYPE = "event";
    private static final String GOODBYE_RES = "GOOD BYE\n";
    private static final int HISTORY_CAPACITY = 100;

    private TaskTracker tt = new TaskTracker();

    public BotResult process(String input) {
        IFormatter formatter = new ResponseFormatter();
        BotResult.BotStatus status = BotResult.BotStatus.Successful;
        String response;
        StringTokenizer query = new StringTokenizer(input);
        String queryType = query.nextToken();

        if (queryType.equals(BYE_QUERY_TYPE)) {
            status = BotResult.BotStatus.Exit;
        }

        try {
            IQueryHandler queryHandler = GetQueryHandler(queryType);
            response = queryHandler.processQuery(input);
        } catch (DukeException e) {
            response = "I have failed you my liege! " + e.getMessage();
        }

        String res = formatter.format(response);
        return new BotResult(status, res);
    }

    private IQueryHandler GetQueryHandler(String queryType) throws UnknownCommandException {
        switch(queryType) {
            case TODO_QUERY_TYPE:
                return new TodoQueryHandler(tt);
            case DEADLINE_QUERY_TYPE:
                return new DeadlineQueryHandler(tt);
            case EVENT_QUERY_TYPE:
                return new EventQueryHandler(tt);
            case LIST_QUERY_TYPE:
                return new ListQueryHandler(tt);
            case MARK_QUERY_TYPE:
                return new MarkQueryHandler(tt);
            case UNMARK_QUERY_TYPE:
                return new UnmarkQueryHandler(tt);
            case DELETE_QUERY_TYPE:
                return new DeleteQueryHandler(tt);
            case BYE_QUERY_TYPE:
                return new SimpleResponseQueryHandler(GOODBYE_RES);
            default:
                throw new UnknownCommandException("Your command is not of the known tongue!");
        }
    }
}
