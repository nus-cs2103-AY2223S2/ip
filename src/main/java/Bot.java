import java.util.StringTokenizer;

public class Bot {
    private static final String BYE_QUERY = "bye";
    private static final String LIST_QUERY = "list";
    private static final String MARK_QUERY = "mark";
    private static final String UNMARK_QUERY = "unmark";
    private static final String TODO_QUERY = "todo";
    private static final String DEADLINE_QUERY = "deadline";
    private static final String EVENT_QUERY = "event";
    private static final String GOODBYE_RES = "GOOD BYE\n";
    private static final int HISTORY_CAPACITY = 100;

    private TaskTracker tt = new TaskTracker();
    private int historyCount = 0;
    private String[] history = new String[HISTORY_CAPACITY];

    public BotResult process(String input) {
        IFormatter formatter = new ResponseFormatter();
        BotResult.BotStatus status;
        StringBuilder response;

        StringTokenizer query = new StringTokenizer(input);
        String queryType = query.nextToken();

        switch(queryType) {
        case TODO_QUERY:
            status = BotResult.BotStatus.Successful;
            response = new StringBuilder("Added task " + tt.AddTodo(query.nextToken("\n")));
            break;
        case LIST_QUERY:
            response = new StringBuilder(tt.GetNumTasks() < 1 ? "No items!" : tt.ListTasks());
            status = BotResult.BotStatus.Successful;
            break;
        case MARK_QUERY:
            try {
                Task t = tt.MarkUnmarkTask(Integer.parseInt(query.nextToken()) - 1, true);
                response = new StringBuilder("Task marked as complete: " + t);
            } catch(TaskNotFoundError err) {
                response = new StringBuilder(err.getMessage());
            }
            status = BotResult.BotStatus.Successful;
            break;
        case UNMARK_QUERY:
            try {
                Task t = tt.MarkUnmarkTask(Integer.parseInt(query.nextToken()) - 1, false);
                response = new StringBuilder("Task marked as incomplete: " + t);
            } catch(TaskNotFoundError err) {
                response = new StringBuilder(err.getMessage());
            }
            status = BotResult.BotStatus.Successful;
            break;
        case BYE_QUERY:
            status = BotResult.BotStatus.Exit;
            response = new StringBuilder(GOODBYE_RES);
            break;
        default:
            status = BotResult.BotStatus.Successful;
            history[historyCount] = queryType;
            response = new StringBuilder("Added - " + queryType);
            historyCount++;
        }
        return new BotResult(status, formatter.format(response.toString()));
    }
}
