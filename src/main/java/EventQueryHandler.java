public class EventQueryHandler extends TaskQueryHandler {
    public EventQueryHandler(TaskTracker tt) {
        super(tt);
    }

    @Override
    public String processQuery(String query) throws DukeException {
        String[] parsed = QueryParser.parseQuery(query, new String[]{ "/from", "/to"});
        String desc = parsed[1];
        if (desc == null || desc.isBlank()) {
            throw new InvalidCommandParamException("Please provide a description for your event!");
        }

        String startDate = parsed[2];
        if (startDate == null || startDate.isBlank()) {
            throw new InvalidCommandParamException("Please provide a start date for the event!");
        }

        String endDate = parsed[3];
        if (endDate == null || endDate.isBlank()) {
            throw new InvalidCommandParamException("Please provide an end date for the event!");
        }
        Task newTask = tt.AddEvent(desc, startDate, endDate);
        tt.SaveAllTasks();
        return "Added task " + newTask;
    }
}
