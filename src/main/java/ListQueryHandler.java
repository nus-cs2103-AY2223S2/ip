public class ListQueryHandler extends TaskQueryHandler {
    ListQueryHandler(TaskTracker tt) {
        super(tt);
    }

    @Override
    public String processQuery(String query) throws DukeException {
        return tt.getNumTasks() < 1 ? "No items!" : tt.listTasks();
    }
}
