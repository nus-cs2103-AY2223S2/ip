import java.util.StringTokenizer;

public class DeleteQueryHandler extends TaskQueryHandler {
    DeleteQueryHandler(TaskTracker tt) {
        super(tt);
    }

    @Override
    public String processQuery(String query) throws DukeException {
        StringTokenizer st = new StringTokenizer(query);
        st.nextToken();
        Task t = tt.deleteTask(Integer.parseInt(st.nextToken()) - 1);
        tt.saveAllTasks();
        return "Task deleted: " + t;
    }
}
