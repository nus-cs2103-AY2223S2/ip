package duke.query.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.DukeException;
import duke.query.InvalidCommandParamException;
import duke.query.Query;
import duke.task.Task;
import duke.task.TaskTracker;

/**
 * The EventQueryHandler class handles user queries for adding new events.
 */
public class EventQueryHandler extends DeadlineQueryHandler {
    public EventQueryHandler(TaskTracker tt) {
        super(tt);
    }

    /**
     * Processes a query for adding an event.
     *
     * @param query user query
     * @return response from adding an event
     * @throws DukeException
     */
    @Override
    public String processQuery(Query query) throws DukeException {
        String desc = getNotBlankParam(query, getInvalidCommandResponse("a description"));
        LocalDateTime startDate = getLocalDateTimeFromQuery(query, "/from", "start date");
        LocalDateTime endDate = getLocalDateTimeFromQuery(query, "/to", "end date");

        Task newTask = tt.addEvent(desc, startDate, endDate);
        tt.saveAllTasks();
        return "Added task " + newTask;
    }
}
