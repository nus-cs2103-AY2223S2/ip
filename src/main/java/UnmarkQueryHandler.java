import java.util.StringTokenizer;

public class UnmarkQueryHandler extends TaskQueryHandler {
    UnmarkQueryHandler(TaskTracker tt) {
        super(tt);
    }

    @Override
    public String processQuery(String query) throws DukeException {
        StringTokenizer st = new StringTokenizer(query);
        st.nextToken();
        Task t = tt.MarkUnmarkTask(Integer.parseInt(st.nextToken()) - 1, false);
        tt.SaveAllTasks();
        return "Task marked as incomplete: " + t;
    }
}
