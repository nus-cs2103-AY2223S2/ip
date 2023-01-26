package duke.query;

import duke.DukeException;
import duke.task.Task;
import duke.task.TaskTracker;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The EventQueryHandler class handles user queries for adding new events.
 */
public class EventQueryHandler extends DeadlineQueryHandler {
    public EventQueryHandler(TaskTracker tt) {
        super(tt);
    }

    /**
     * Processes a query for adding an event.
     * @param query a user input string
     * @return response from adding an event
     * @throws DukeException
     */
    @Override
    public String processQuery(String query) throws DukeException {
        String[] parsed = QueryParser.parseQuery(query, new String[]{ "/from", "/to"});

        String desc = parsed[1];
        if (desc == null || desc.isBlank()) {
            throw new InvalidCommandParamException("Please provide a description for your event!");
        }

        String startDateStr = parsed[2];
        if (startDateStr == null || startDateStr.isBlank()) {
            throw new InvalidCommandParamException("Please provide a start date for the event!");
        }

        LocalDateTime startDate;
        try {
            startDate = LocalDateTime.parse(startDateStr, DateTimeFormatter.ofPattern(DATETIME_PATTERN));
        } catch (DateTimeParseException e) {
            throw new InvalidCommandParamException(String.format("Please provide a valid start date for your event! (%s)", DATETIME_PATTERN));
        }

        String endDateStr = parsed[3];
        if (endDateStr == null || endDateStr.isBlank()) {
            throw new InvalidCommandParamException("Please provide an end date for the event!");
        }

        LocalDateTime endDate;
        try {
            endDate = LocalDateTime.parse(endDateStr, DateTimeFormatter.ofPattern(DATETIME_PATTERN));
        } catch (DateTimeParseException e) {
            throw new InvalidCommandParamException(String.format("Please provide a valid end date for your event! (%s)", DATETIME_PATTERN));
        }

        Task newTask = tt.addEvent(desc, startDate, endDate);
        tt.saveAllTasks();
        return "Added task " + newTask;
    }
}
