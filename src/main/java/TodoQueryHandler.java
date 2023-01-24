import java.util.StringTokenizer;

public class TodoQueryHandler extends TaskQueryHandler {
    public TodoQueryHandler(TaskTracker tt) {
        super(tt);
    }

    @Override
    public String processQuery(String query) throws DukeException {
        String[] parsed = QueryParser.parseQuery(query, new String[]{});
        if (parsed[1] == null || parsed[1].isBlank()) {
            throw new InvalidCommandParamException("Please provide a description for your todo!");
        }
        Task newTask = tt.AddTodo(parsed[1]);
        tt.SaveAllTasks();
        return "Added task " + newTask;
    }
}
