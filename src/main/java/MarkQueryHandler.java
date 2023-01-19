import java.util.StringTokenizer;

public class MarkQueryHandler extends TaskQueryHandler {
    MarkQueryHandler(TaskTracker tt) {
        super(tt);
    }

    @Override
    public String processQuery(String query) throws DukeException {
        StringTokenizer st = new StringTokenizer(query);
        st.nextToken();
        Task t = tt.MarkUnmarkTask(Integer.parseInt(st.nextToken()) - 1, true);
        return "Task marked as complete: " + t;
    }
}
