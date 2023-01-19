public class DeadlineQueryHandler extends TaskQueryHandler {
    public DeadlineQueryHandler(TaskTracker tt) {
        super(tt);
    }

    @Override
    public String processQuery(String query) throws DukeException {
        String[] parsed = QueryParser.parseQuery(query, new String[]{"/by"});
        String deadlineDesc = parsed[1];
        if (deadlineDesc == null || deadlineDesc.isBlank()) {
            throw new InvalidCommandParamException("Please provide a description for your deadline!");
        }
        String deadlineEndDate = parsed[2];
        if (deadlineEndDate == null || deadlineEndDate.isBlank()) {
            throw new InvalidCommandParamException("Please provide an end date for your deadline!");
        }
        Task newTask = tt.AddDeadline(deadlineDesc, deadlineEndDate);
        return "Added task " + newTask;
    }
}
