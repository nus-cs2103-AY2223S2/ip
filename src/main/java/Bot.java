import java.util.StringTokenizer;

public class Bot {
    private static final String BYE_QUERY_TYPE = "bye";
    private static final String LIST_QUERY_TYPE = "list";
    private static final String MARK_QUERY_TYPE = "mark";
    private static final String UNMARK_QUERY_TYPE = "unmark";
    private static final String TODO_QUERY_TYPE = "todo";
    private static final String DEADLINE_QUERY_TYPE = "deadline";
    private static final String EVENT_QUERY_TYPE = "event";
    private static final String GOODBYE_RES = "GOOD BYE\n";
    private static final int HISTORY_CAPACITY = 100;

    private TaskTracker tt = new TaskTracker();
    private int historyCount = 0;
    private String[] history = new String[HISTORY_CAPACITY];

    public BotResult process(String input) {
        IFormatter formatter = new ResponseFormatter();
        BotResult.BotStatus status = BotResult.BotStatus.Successful;
        StringBuilder response;

        StringTokenizer query = new StringTokenizer(input);
        String queryType = query.nextToken();

        switch(queryType) {
        case TODO_QUERY_TYPE:
            response = getTaskAddedResponse(tt.AddTodo(query.nextToken("\n")));
            break;
        case DEADLINE_QUERY_TYPE:
            String deadlineDesc = query.nextToken("/");
            query.nextToken(" ");
            String deadlineEndDate = query.nextToken("\n");
            response = getTaskAddedResponse(tt.AddDeadline(deadlineDesc, deadlineEndDate));
            break;
        case EVENT_QUERY_TYPE:
            String eventDesc = query.nextToken("/");
            query.nextToken(" ");
            String eventStartDate = query.nextToken("/");
            query.nextToken(" ");
            String eventEndDate = query.nextToken("\n");
            response = getTaskAddedResponse(tt.AddEvent(eventDesc, eventStartDate, eventEndDate));
            break;
        case LIST_QUERY_TYPE:
            response = new StringBuilder(tt.GetNumTasks() < 1 ? "No items!" : tt.ListTasks());
            break;
        case MARK_QUERY_TYPE:
            try {
                Task t = tt.MarkUnmarkTask(Integer.parseInt(query.nextToken()) - 1, true);
                response = new StringBuilder("Task marked as complete: " + t);
            } catch(TaskNotFoundError err) {
                response = new StringBuilder(err.getMessage());
            }
            break;
        case UNMARK_QUERY_TYPE:
            try {
                Task t = tt.MarkUnmarkTask(Integer.parseInt(query.nextToken()) - 1, false);
                response = new StringBuilder("Task marked as incomplete: " + t);
            } catch(TaskNotFoundError err) {
                response = new StringBuilder(err.getMessage());
            }
            break;
        case BYE_QUERY_TYPE:
            status = BotResult.BotStatus.Exit;
            response = new StringBuilder(GOODBYE_RES);
            break;
        default:
            history[historyCount] = queryType;
            response = new StringBuilder("Added - " + queryType);
            historyCount++;
        }
        return new BotResult(status, formatter.format(response.toString()));
    }

    private StringBuilder getTaskAddedResponse(Task task) {
        return new StringBuilder("Added task " + task);
    }
}
