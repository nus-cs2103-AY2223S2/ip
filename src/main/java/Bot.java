import javax.management.Query;
import java.util.StringTokenizer;

public class Bot {
    private static final String GOODBYE_RES = "GOOD BYE\n";
    private static final String UNKNOWN_COMMAND_RES = "Your command is not of the known tongue!";

    private TaskTracker tt = new TaskTracker();

    public BotResult process(String input) {
        IFormatter formatter = new ResponseFormatter();
        BotResult.BotStatus status = BotResult.BotStatus.Successful;
        String response;
        StringTokenizer query = new StringTokenizer(input);
        QueryType queryType = QueryTypeUtil.GetQueryTypeFromString(query.nextToken());

        if (queryType == QueryType.EXIT) {
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

    private IQueryHandler GetQueryHandler(QueryType queryType) throws UnknownCommandException {
        switch(queryType) {
            case TODO:
                return new TodoQueryHandler(tt);
            case DEADLINE:
                return new DeadlineQueryHandler(tt);
            case EVENT:
                return new EventQueryHandler(tt);
            case LIST   :
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
