import java.util.StringTokenizer;

public class DeleteQueryHandler extends TaskQueryHandler {
    DeleteQueryHandler(TaskTracker tt) {
        super(tt);
    }

    @Override
    public String processQuery(String query) throws DukeException {
        StringTokenizer st = new StringTokenizer(query);
        st.nextToken();
        Task t = tt.DeleteTask(Integer.parseInt(st.nextToken()) - 1);
        tt.SaveAllTasks();
        return "Task deleted: " + t;
    }
}
